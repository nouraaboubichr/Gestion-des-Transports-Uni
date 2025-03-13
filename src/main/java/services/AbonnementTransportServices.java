/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.IDao;
import beans.AbonnementTransport;
import beans.Bus;
import beans.Etudiant;
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
            ps.setInt(1, abonnement.getBus().getId());  
            ps.setInt(2, abonnement.getEtudiant().getId());  
            ps.setDate(3, abonnement.getDateAbonnement());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout : " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(AbonnementTransport abonnement) {
        try {
            String query = "UPDATE abonnement_transport SET bus_id = ?, etudiant_id = ?, date_abonnement = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, abonnement.getBus().getId());
            ps.setInt(2, abonnement.getEtudiant().getId());
            ps.setDate(3, abonnement.getDateAbonnement());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour : " + ex.getMessage());
            return false;
        }
    }

    
public boolean delete(AbonnementTransport abonnement) {
    try {
        // Utilisation de bus_id et etudiant_id pour supprimer l'abonnement
        String query = "DELETE FROM abonnement_transport WHERE bus_id = ? AND etudiant_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, abonnement.getBus().getId()); // On prend l'ID du bus
        ps.setInt(2, abonnement.getEtudiant().getId()); // On prend l'ID de l'étudiant
        return ps.executeUpdate() > 0;
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression : " + ex.getMessage());
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
                Bus bus = new Bus(rs.getInt("bus_id"), "", 0); 
                Etudiant etudiant = new Etudiant(rs.getInt("etudiant_id"), "", "", ""); 
                return new AbonnementTransport(bus, etudiant, rs.getDate("date_abonnement"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche : " + ex.getMessage());
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
                Bus bus = new Bus(rs.getInt("bus_id"), "", 0);
                Etudiant etudiant = new Etudiant(rs.getInt("etudiant_id"), "", "", "");
                abonnements.add(new AbonnementTransport(bus, etudiant, rs.getDate("date_abonnement")));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération : " + ex.getMessage());
        }
        return abonnements;
    }
}