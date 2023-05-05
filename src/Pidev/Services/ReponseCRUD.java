/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.Assureur;
import Pidev.Entities.CurrentUser;
import Pidev.Entities.Reclamation;
import Pidev.Entities.Reponse;
import Pidev.Utilis.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msi
 */
public class ReponseCRUD {
    
    Connection cnx2;
    
    public ReponseCRUD(){
       cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterReponse(){
        try {
            String requete = "INSERT INTO reponse (description,id_reclamation_id,id_assureur_id)"
                    + "VALUES ('descriptionA','5',1)";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Reponse ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterReponse2(Reponse re){
        try {
            String requete2 = "INSERT INTO reponse (description,id_reclamation_id,id_assureur_id)"
                   + "VALUES (?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, re.getDescription());
            pst.setInt(2, re.getId());
            pst.setInt(3, CurrentUser.getLoggedInUser().getId());
            pst.executeUpdate();
            System.out.println("Votre reponse est ajoutée avec succès");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
    }
    
    public void ajouter(Reponse re) {
    try {
        String req = "INSERT INTO `Reponse` (`description`, `id_reclamation_id`, `id_assureur_id`) VALUES (?, ?, ?)";
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setString(1, re.getDescription());
        ps.setInt(2, re.getReclamation().getId());
        ps.setInt(3, CurrentUser.getLoggedInUser().getId());
        
        // Check if the id_reclamation_id value exists in the reclamation table
        String checkReq = "SELECT id FROM `reclamation` WHERE id = ?";
        PreparedStatement checkPs = cnx2.prepareStatement(checkReq);
        checkPs.setInt(1, re.getReclamation().getId());
        ResultSet rs = checkPs.executeQuery();
        if (!rs.next()) {
            System.out.println("Reclamation with id " + re.getReclamation().getId() + " does not exist");
            return;
        }
        
        ps.executeUpdate();
        System.out.println("Votre reponse est ajoutée avec succès");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    
    public List<Reponse> afficherReponses(){
        List<Reponse> myList = new ArrayList<>();
        Assureur a=new Assureur(1);
        Reclamation r=new Reclamation();
        try {
        String requete3 = "SELECT * FROM reponse";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(requete3);
        while(rs.next()){
            Reponse re = new Reponse();
            re.setId(rs.getInt(1));
            re.setDescription(rs.getString("description"));
            re.setReclamation((Reclamation) r);
            re.setAssureur(CurrentUser.getLoggedInUser());
            myList.add(re);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
      public List<Reponse> afficherReponsesByUser(int id){
        List<Reponse> myList = new ArrayList<>();
        Assureur a=new Assureur(1);
        Reclamation r=new Reclamation();
        try {
        String requete3 = "SELECT * FROM reponse WHERE id_reclamation_id"+id;
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(requete3);
        while(rs.next()){
            Reponse re = new Reponse();
            re.setId(rs.getInt(1));
            re.setDescription(rs.getString("description"));
            re.setReclamation((Reclamation) r);
            re.setAssureur(CurrentUser.getLoggedInUser());
            myList.add(re);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    public void supprimerRep(int id) {
        try {
            String req = "DELETE FROM `reponse` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Votre reponse est supprimée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifier(Reponse re) {
        try {
            String req = "UPDATE `Reponse` SET `description` = '" + re.getDescription() 
                    + "', `id_reclamation_id` = '" + re.getReclamation().getId() 
                    +  "', `id_assureur_id` = '" + CurrentUser.getLoggedInUser().getId()
                    +  "' WHERE `reponse`.`id` = " + re.getId();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Votre reponse est modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
      

  
}
