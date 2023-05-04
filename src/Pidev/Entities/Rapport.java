/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author rayen
 */
public class Rapport {
    private int id;
    private String description;
    private String rapportpreliminaire;
    private String rapportexpertise;
    private String image;
    
        public static int actionTest;
        
        private static int IdOffre;


    public static int getIdOffre() {
        return IdOffre;
    }


    public static void setIdOffre(int idOffre) {
        IdOffre = idOffre;
    }

    

    public Rapport(int id, String description, String rapportpreliminaire, String rapportexpertise, String image) {
        this.id = id;
        this.description = description;
        this.rapportpreliminaire = rapportpreliminaire;
        this.rapportexpertise = rapportexpertise;
        this.image = image;
        
    }

    public Rapport(int id) {
        this.id = id;
    }

    

    
    public Rapport() {
    }

    public Rapport(String description, String rapportpreliminaire, String rapportexpertise, String image) {
        this.description = description;
        this.rapportpreliminaire = rapportpreliminaire;
        this.rapportexpertise = rapportexpertise;
        this.image = image;
        
    }

    public Rapport(String description, String rapportPreliminaire, String rapportExpertise, byte[] imageData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Rapport(int i, String rapport_1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getRapportpreliminaire() {
        return rapportpreliminaire;
    }

    public void setRapportpreliminaire(String rapportpreliminaire) {
        this.rapportpreliminaire = rapportpreliminaire;
    }

    public String getRapportexpertise() {
        return rapportexpertise;
    }

    public void setRapportexpertise(String rapportexpertise) {
        this.rapportexpertise = rapportexpertise;
    }

    public String getImage() {
        return image;
    }
    


    
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Rapport{" + "id=" + id + ", description=" + description + ", rapportpreliminaire=" + rapportpreliminaire + ", rapportexpertise=" + rapportexpertise + ", image=" + image + '}';
    }
public static void setId(Rapport rapport, int id) {
    rapport.setId(id);
}

    

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final Rapport other = (Rapport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setImage(ImageView image_view) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    

}  
