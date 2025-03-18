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

        System.out.println("===== Test d'ajout d'un bus =====");
        Bus bus = new Bus("THJ-123", 40);
        if (busDao.create(bus)) {
            System.out.println(" Bus ajouté avec succès !");
        } else {
            System.out.println(" Échec de l'ajout du bus.");
            return;  // Arrêter le programme si l'ajout échoue
        }

        System.out.println("\n===== Test d'ajout d'un étudiant =====");
        Etudiant etudiant = new Etudiant("Aboubichr", "Noura", "nouraaboubichr@gmail.com");
        if (etudiantDao.create(etudiant)) {
            System.out.println(" Étudiant ajouté avec succès !");
        } else {
            System.out.println(" Échec de l'ajout de l'étudiant.");
            return;
        }

        // Récupérer les bus et étudiants
        List<Bus> busList = busDao.findAll();
        List<Etudiant> etudiantList = etudiantDao.findAll();

        if (!busList.isEmpty() && !etudiantList.isEmpty()) {
            Bus lastBus = busList.get(busList.size() - 1);
            Etudiant lastEtudiant = etudiantList.get(etudiantList.size() - 1);

            System.out.println("\n===== Bus et Étudiant récupérés =====");
            System.out.println("Bus ID: " + lastBus.getId() + " | Immatriculation: " + lastBus.getImmatriculation());
            System.out.println("Étudiant ID: " + lastEtudiant.getId() + " | Nom: " + lastEtudiant.getNom());

            System.out.println("\n===== Test d'ajout d'un abonnement =====");
            AbonnementTransport abonnement = new AbonnementTransport(lastBus, lastEtudiant, Date.valueOf("2025-03-03"));
            if (abonnementDao.create(abonnement)) {
                System.out.println("Abonnement ajouté avec succès !");
            } else {
                System.out.println(" Échec de l'ajout de l'abonnement.");
                return;
            }

            // Liste des abonnements
            List<AbonnementTransport> abonnementList = abonnementDao.findAll();
            System.out.println("\n===== Liste des abonnements  =====");
            for (AbonnementTransport a : abonnementList) {
                System.out.println(" - Bus ID: " + a.getBus().getId() + " | Étudiant ID: " + a.getEtudiant().getId() + " | Date: " + a.getDateAbonnement());
            }

            // delete abonnement
            System.out.println("\n===== Suppression du dernier abonnement =====");
            if (abonnementDao.delete(abonnement)) {
                System.out.println("Abonnement supprimé avec succès !");
            } else {
                System.out.println(" Échec de la suppression de l'abonnement. Vérifie si les IDs sont corrects.");
            }

            // Vérification après suppression
            abonnementList = abonnementDao.findAll();
            System.out.println("\n===== Liste des abonnements après suppression =====");
            if (abonnementList.isEmpty()) {
                System.out.println("✅ Aucun abonnement trouvé en base.");
            } else {
                for (AbonnementTransport a : abonnementList) {
                    System.out.println(" - Bus ID: " + a.getBus().getId() + " | Étudiant ID: " + a.getEtudiant().getId() + " | Date: " + a.getDateAbonnement());
                }
            }
        } else {
            System.out.println("\n⚠️ Erreur : Impossible de créer un abonnement sans bus ou étudiant.");
        }
    }
}