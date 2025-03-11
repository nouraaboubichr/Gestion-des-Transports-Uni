/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author hp
 */


import dao.IDao;
import beans.Bus;
import connexion.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusServices implements IDao<Bus> {
    private Connection connection = Connexion.getInstance().getCn();

    @Override
    public boolean create(Bus bus) {
        try {
            String query = "INSERT INTO bus (immatriculation, nombre_places) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, bus.getImmatriculation());
            ps.setInt(2, bus.getNombrePlaces());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Bus bus) {
        try {
            String query = "UPDATE bus SET immatriculation = ?, nombre_places = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
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
    public boolean delete(int id) {
        try {
            String query = "DELETE FROM bus WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Bus findById(int id) {
        try {
            String query = "SELECT * FROM bus WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bus(
                    rs.getInt("id"),
                    rs.getString("immatriculation"),
                    rs.getInt("nombre_places")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Bus> findAll() {
        List<Bus> buses = new ArrayList<>();
        try {
            String query = "SELECT * FROM bus";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                buses.add(new Bus(
                    rs.getInt("id"),
                    rs.getString("immatriculation"),
                    rs.getInt("nombre_places")
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return buses;
    }
}

