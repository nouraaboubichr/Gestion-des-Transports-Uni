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
import java.util.List;

/**
 *
 * @author hp
 */
public class Test {

    public static void main(String[] args) {
        BusServices busDao = new BusServices();
        EtudiantServices etudiantDao = new EtudiantServices();
        AbonnementTransportServices abonnementDao = new AbonnementTransportServices();

        // Create d'un bus
        System.out.println("Test d'ajout d'un bus :");
        Bus bus = new Bus(0, "THJ-123", 40);  // 0 car l'ID est généré par la BDD
        if (busDao.create(bus)) {
            System.out.println("Bus ajouté avec succès !");
        } else {
            System.out.println("Échec de l'ajout du bus.");
        }

        // Create d'un étudiant
        System.out.println("\nTest d'ajout d'un étudiant :");
        Etudiant etudiant = new Etudiant(0, "Aboubichr", "Noura", "nouraaboubichr@gmail.com");
        if (etudiantDao.create(etudiant)) {
            System.out.println("Étudiant ajouté avec succès !");
        } else {
            System.out.println("Échec de l'ajout de l'étudiant.");
        }

        // les listes de bus et étudiants
        List<Bus> busList = busDao.findAll();
        List<Etudiant> etudiantList = etudiantDao.findAll();

        if (!busList.isEmpty() && !etudiantList.isEmpty()) {
            Bus lastBus = busList.get(busList.size() - 1);
            Etudiant lastEtudiant = etudiantList.get(etudiantList.size() - 1);

             // Create d'un abonnement
            System.out.println("\nTest d'ajout d'un abonnement :");
            AbonnementTransport abonnement = new AbonnementTransport(lastBus, lastEtudiant, Date.valueOf("2025-03-03"));
            if (abonnementDao.create(abonnement)) {
                System.out.println("Abonnement ajouté avec succès !");
            } else {
                System.out.println("Échec de l'ajout de l'abonnement.");
            }
        } else {
            System.out.println("\nErreur : Impossible de créer un abonnement sans bus ou étudiant.");
        }

        System.out.println("\nListe des bus :");
        for (Bus b : busList) {
            System.out.println(" - " + b.getId() + " | " + b.getImmatriculation() + " | " + b.getNombrePlaces() + " places");
        }

        System.out.println("\nListe des étudiants :");
        for (Etudiant e : etudiantList) {
            System.out.println(" - " + e.getId() + " | " + e.getNom() + " " + e.getPrenom() + " | " + e.getEmail());
        }

        List<AbonnementTransport> abonnementList = abonnementDao.findAll();
        System.out.println("\nListe des abonnements :");
        for (AbonnementTransport a : abonnementList) {
            System.out.println(" - Bus ID: " + a.getBus().getId() + " | Étudiant ID: " + a.getEtudiant().getId() + " | Date: " + a.getDateAbonnement());
        }

        // delete  enregistrements
        /*
        if (!abonnementList.isEmpty()) {
            int abonnementId = abonnementList.get(abonnementList.size() - 1).getId();
            abonnementDao.delete(abonnementId);
        }

        if (!busList.isEmpty()) {
            busDao.delete(busList.get(busList.size() - 1).getId());
        }

        if (!etudiantList.isEmpty()) {
            etudiantDao.delete(etudiantList.get(etudiantList.size() - 1).getId());
        }
        */
    }
}