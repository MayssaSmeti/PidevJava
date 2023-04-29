/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author msi
 */
public class Reclamation {
    private int id;
    private String description;
    private String objet;
    private User client;
    private int note;

    public Reclamation() {
    }

    public Reclamation(int id) {
        this.id = id;
    }
    
    

    public Reclamation(int id, String description, String objet, User client, int note) {
        this.id = id;
        this.description = description;
        this.objet = objet;
        this.client = client;
        this.note = note;
    }

    public Reclamation(String description, String objet, User client, int note) {
        this.description = description;
        this.objet = objet;
        this.client = client;
        this.note = note;
    }

    public Reclamation(String description, String objet, int note) {
        this.description = description;
        this.objet = objet;
        this.note = note;
    }

    public Reclamation(int id, String description, String objet, int note) {
        this.id = id;
        this.description = description;
        this.objet = objet;
        this.note = note;
    }

    public Reclamation(String description, String objet, User client) {
        this.description = description;
        this.objet = objet;
        this.client = client;
    }

    public Reclamation(int id, String description, String objet, User client) {
        this.id = id;
        this.description = description;
        this.objet = objet;
        this.client = client;
    }

  

    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", description=" + description + ", objet=" + objet + ", client=" + client + ", note=" + note + '}';
    }   

  
}
