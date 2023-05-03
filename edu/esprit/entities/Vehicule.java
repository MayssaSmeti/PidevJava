/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author USER
 */
public class Vehicule {
    private int id;
    private User client;
    private String matricule,type,marque,nb_ch;

    public Vehicule() {
    }

    public Vehicule(int id, String matricule, String type, String marque, String nb_ch,User client) {
        this.id = id;
        this.matricule = matricule;
        this.type = type;
        this.marque = marque;
        this.nb_ch = nb_ch;
        this.client = client;
    }

    public Vehicule(String matricule, String type, String marque, String nb_ch,User client) {
        this.matricule = matricule;
        this.type = type;
        this.marque = marque;
        this.nb_ch = nb_ch;
        this.client = client;
    }
    
    public Vehicule(String matricule, String type, String marque, String nb_ch) {
        this.matricule = matricule;
        this.type = type;
        this.marque = marque;
        this.nb_ch = nb_ch;
    }

    public Vehicule(int i) {
        this.id=i;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNb_ch() {
        return nb_ch;
    }

    public void setNb_ch(String nb_ch) {
        this.nb_ch = nb_ch;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id + ", client=" + client + ", matricule=" + matricule + ", type=" + type + ", marque=" + marque + ", nb_ch=" + nb_ch + '}';
    }
        
}
