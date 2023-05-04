/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Entities;

/**
 *
 * @author SCORPIO-12
 */
public class Locale {
    private int id;
    private String adresse,nom_entrep;
    private Mecanicien mec;

    public Locale(String adresse, String nom_entrep, Mecanicien mec) {
        this.adresse = adresse;
        this.nom_entrep = nom_entrep;
        this.mec = mec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom_entrep() {
        return nom_entrep;
    }

    public void setNom_entrep(String nom_entrep) {
        this.nom_entrep = nom_entrep;
    }

    public Mecanicien getMec() {
        return mec;
    }

    public void setMec(Mecanicien mec) {
        this.mec = mec;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Locale other = (Locale) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Locale{" + "id=" + id + ", adresse=" + adresse + ", nom_entrep=" + nom_entrep + ", mec=" + mec + '}';
    }
    
    
    
}
