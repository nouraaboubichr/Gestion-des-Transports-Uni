/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package test;
import beans.Bus;
import beans.Etudiant;
import beans.AbonnementTransport;
import services.AbonnementTransportServices;
import services.BusServices;
import services.EtudiantServices;
import java.sql.Date;

/**
 *
 * @author hp
 */

public class Test {

    public static void main(String[] args) {
        BusServices busDao = new BusServices();
        EtudiantServices etudiantDao = new EtudiantServices();
        AbonnementTransportServices abonnementDao = new AbonnementTransportServices();

        //  creation d'un bus
        System.out.println(" Test d'ajout d'un bus :");
        Bus bus = new Bus("THJ-123", 40);  
        if (busDao.create(bus)) {
            System.out.println(" Bus ajouté avec succès !");
        } else {
            System.out.println("Échec de l'ajout du bus.");
        }

        //  creation d'un étudiant 
        System.out.println(" Test d'ajout d'un étudiant :");
        Etudiant etudiant = new Etudiant("Aboubichr", "Noura", "nouraaboubichr@gmail.com");
        if (etudiantDao.create(etudiant)) {
            System.out.println("Étudiant ajouté avec succès !");
        } else {
            System.out.println(" Échec de l'ajout de l'étudiant.");
        }

        int busId = busDao.findAll().get(busDao.findAll().size() - 1).getId();
        int etudiantId = etudiantDao.findAll().get(etudiantDao.findAll().size() - 1).getId();

        // creation d'un abonnement 
        System.out.println(" Test d'ajout d'un abonnement :");
        AbonnementTransport abonnement = new AbonnementTransport(busId, etudiantId, Date.valueOf("2025-03-03"));
        if (abonnementDao.create(abonnement)) {
            System.out.println(" Abonnement ajouté avec succès !");
        } else {
            System.out.println(" Échec de l'ajout de l'abonnement.");
        }

        //  Afficher tous les bus
        System.out.println("\n Liste des bus :");
        for (Bus b : busDao.findAll()) {
            System.out.println(" - " + b.getId() + " | " + b.getImmatriculation() + " | " + b.getNombrePlaces() + " places");
        }

        // Afficher tous les étudiants 
        System.out.println("\n Liste des étudiants :");
        for (Etudiant e : etudiantDao.findAll()) {
            System.out.println(" - " + e.getId() + " | " + e.getNom() + " " + e.getPrenom() + " | " + e.getEmail());
        }

        //  Afficher tous les abonnements 
        System.out.println("\n Liste des abonnements :");
        for (AbonnementTransport a : abonnementDao.findAll()) {
            System.out.println(" - " + a.getId() + " | Bus ID: " + a.getBusId() + " | Étudiant ID: " + a.getEtudiantId() + " | Date: " + a.getDateAbonnement());
        }

       /* delete 
       int abonnementId = abonnementDao.findAll().get(abonnementDao.findAll().size() - 1).getId();
        abonnementDao.delete(abonnementId);

         busDao.delete(busId);
 
        etudiantDao.delete(etudiantId);
     
       */
    }
}
