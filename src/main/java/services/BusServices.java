/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.IDao;
import beans.Bus;
import connexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */

public class BusServices implements IDao<Bus> {
    private Connection connection = Connexion.getInstance().getCn();

    @Override
    public boolean create(Bus bus) {
        String query = "INSERT INTO bus (immatriculation, nombre_places) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, bus.getImmatriculation());
            ps.setInt(2, bus.getNombrePlaces());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Bus bus) {
        String query = "DELETE FROM bus WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, bus.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Bus bus) {
        String query = "UPDATE bus SET immatriculation = ?, nombre_places = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, bus.getImmatriculation());
            ps.setInt(2, bus.getNombrePlaces());
            ps.setInt(3, bus.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Bus findById(int id) {
        String query = "SELECT * FROM bus WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bus(rs.getInt("id"), rs.getString("immatriculation"), rs.getInt("nombre_places"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Bus> findAll() {
        List<Bus> buses = new ArrayList<>();
        String query = "SELECT * FROM bus";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                buses.add(new Bus(rs.getInt("id"), rs.getString("immatriculation"), rs.getInt("nombre_places")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return buses;
    }
}