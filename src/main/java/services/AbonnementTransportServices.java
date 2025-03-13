/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.IDao;
import beans.AbonnementTransport;
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

public class AbonnementTransportServices implements IDao<AbonnementTransport> {
    private Connection connection = Connexion.getInstance().getCn();

    @Override
    public boolean create(AbonnementTransport abonnement) {
        try {
            String query = "INSERT INTO abonnement_transport (bus_id, etudiant_id, date_abonnement) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, abonnement.getBusId());
            ps.setInt(2, abonnement.getEtudiantId());
            ps.setDate(3, abonnement.getDateAbonnement());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(AbonnementTransport abonnement) {
        try {
            String query = "UPDATE abonnement_transport SET bus_id = ?, etudiant_id = ?, date_abonnement = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, abonnement.getBusId());
            ps.setInt(2, abonnement.getEtudiantId());
            ps.setDate(3, abonnement.getDateAbonnement());
            ps.setInt(4, abonnement.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = "DELETE FROM abonnement_transport WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public AbonnementTransport findById(int id) {
        try {
            String query = "SELECT * FROM abonnement_transport WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AbonnementTransport(rs.getInt("id"), rs.getInt("bus_id"), rs.getInt("etudiant_id"), rs.getDate("date_abonnement"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<AbonnementTransport> findAll() {
        List<AbonnementTransport> abonnements = new ArrayList<>();
        try {
            String query = "SELECT * FROM abonnement_transport";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                abonnements.add(new AbonnementTransport(rs.getInt("id"), rs.getInt("bus_id"), rs.getInt("etudiant_id"), rs.getDate("date_abonnement")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return abonnements;
    }
}
