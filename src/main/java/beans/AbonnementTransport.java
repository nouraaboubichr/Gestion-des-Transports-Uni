/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;
import java.sql.Date;
/**
 *
 * @author hp
 */


public class AbonnementTransport {
    private Bus bus;
    private Etudiant etudiant;
    private Date dateAbonnement;

    public AbonnementTransport(Bus bus, Etudiant etudiant, Date dateAbonnement) {
        this.bus = bus;
        this.etudiant = etudiant;
        this.dateAbonnement = dateAbonnement;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Date getDateAbonnement() {
        return dateAbonnement;
    }

    public void setDateAbonnement(Date dateAbonnement) {
        this.dateAbonnement = dateAbonnement;
    }
}
