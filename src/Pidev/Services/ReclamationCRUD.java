/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.Client;
import Pidev.Entities.CurrentUser;
import Pidev.Entities.Reclamation;
import Pidev.Entities.User;
import Pidev.Utilis.MyConnection;
import Pidev.Utilis.SessionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author msi
 */
public class ReclamationCRUD {
    
    Connection cnx2;
    
    public ReclamationCRUD(){
       cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterReclamation(){
        try {
            String requete = "INSERT INTO reclamation (description,objet,id_client_id,note)"
                    + "VALUES ('descriptionA','objetB',1,5)";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Reclamation ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterReclamation2(Reclamation r){
        try {
            String requete2 = "INSERT INTO reclamation (description,objet,id_client_id,note)"
                   + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, r.getDescription());
            pst.setString(2, r.getObjet());
            System.out.println(SessionManager.getId());
            pst.setInt(3, CurrentUser.getLoggedInUser().getId());
            pst.setInt(4, r.getNote());
            pst.executeUpdate();
            System.out.println("Votre reclamation est ajoutée avec succès");
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
    }
    

    
    public List<Reclamation> afficherReclamations(){
        List<Reclamation> myList = new ArrayList<>();
       //Client c=new Client(1);
       User c=CurrentUser.getLoggedInUser();
       
        try {
        String requete3 = "SELECT * FROM reclamation";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(requete3);
        while(rs.next()){
            Reclamation r = new Reclamation();
            r.setId(rs.getInt(1));
            r.setDescription(rs.getString("description"));
            r.setObjet(rs.getString("objet"));
            r.setClient(c);
            r.setNote(rs.getInt(5));
            myList.add(r);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    public void supprimerRec(int id) {
        try {
            String req = "DELETE FROM `reclamation` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Votre reclamation est supprimée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifier(Reclamation r) {
        try {
            String req = "UPDATE `Reclamation` SET `description` = '" + r.getDescription() + "', `objet` = '" + r.getObjet() 
                    +  "', `id_client_id` = '" + r.getClient().getId() +  "', `note` = '" + r.getNote() + "' WHERE `reclamation`.`id` = " + r.getId();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Votre reclamation est modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
