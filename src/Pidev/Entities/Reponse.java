/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Entities;

/**
 *
 * @author msi
 */
public class Reponse {
    
    private int id;
    private String description;
    private Reclamation reclamation;
    private User assureur;


    public Reponse() {
    }

    public Reponse(String description) {
        this.description = description;
    }

    public Reponse(int id) {
        this.id = id;
    }

    
   
    

    public Reponse(int id, String description,  Reclamation reclamation, User assureur) {
        this.id = id;
        this.description = description;
        this.reclamation = reclamation;
        this.assureur = assureur;
    }

    public Reponse(String description, Reclamation reclamation, User assureur) {
        this.description = description;
        this.reclamation = reclamation;
        this.assureur = assureur;
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

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public User getAssureur() {
        return assureur;
    }

    public void setAssureur(User assureur) {
        this.assureur = assureur;
    }

 
    

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", description=" + description + ", reclamation=" + reclamation + ", assureur=" + assureur + '}';
    }
    
    
}
