/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Constat;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USER
 */
public class ConstatCRUD {
    
    Connection cnx2;
    
    public ConstatCRUD(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterConstat(){
        String requete = "INSERT INTO constat (nomclient_e,prenomclient_e,typevehicule_e,marquevehicule_e,assuranceclient_e,adresseclient_e,emplacementaccid,photoaccid,descriptiondegat,observations,numcontrat_e,mail,date_creation,id_expert_id,id_vehicule_id)"
                       +   "VALUES ('eya','bkh','voiture','kia','gat','nabeul','tunis','eya.png','no description','no observations','123456789','aya@esprit.tn','2013-05-02 20:05:11',1,1)";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("constat ajoutée avec succés");
            
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());      
        }
        
    }
    
    public void ajouterConstat2(Constat c){
            String requete2 = "INSERT INTO constat (nomclient_e,prenomclient_e,typevehicule_e,marquevehicule_e,assuranceclient_e,adresseclient_e,emplacementaccid,photoaccid,descriptiondegat,observations,numcontrat_e,mail,date_creation,id_expert_id,id_vehicule_id)"
                              +   "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, c.getNomclient_e());
            pst.setString(2, c.getPrenomclient_e());
            pst.setString(3, c.getTypevehicule_e());
            pst.setString(4, c.getMarquevehicule_e());
            pst.setString(5, c.getAssuranceclient_e());
            pst.setString(6, c.getAdresseclient_e());
            pst.setString(7, c.getEmplacementaccid());
            pst.setString(8, c.getPhotoaccid());
            pst.setString(9, c.getDescriptiondegat());
            pst.setString(10, c.getObservations());
            pst.setString(11, c.getNumcontrat_e());
            pst.setString(12, c.getMail());
            pst.setString(13, c.getDate_creation());
            pst.setInt(14, c.getExpert().getId());
            pst.setInt(15, c.getVehicule().getId());
            pst.executeUpdate();
            System.out.println("Votre Constat est ajoutée");


            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());      

        }
            

        
    }
    
    public List<Constat> afficherConstats() {
        
        List<Constat> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM constat";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Constat c = new Constat();
                c.setId(rs.getInt(1));
                c.setNomclient_e(rs.getString("nomclient_e"));
                c.setPrenomclient_e(rs.getString("prenomclient_e"));
                c.setTypevehicule_e(rs.getString("typevehicule_e"));
                c.setMarquevehicule_e(rs.getString("marquevehicule_e"));
                c.setAdresseclient_e(rs.getString("adresseclient_e"));
                c.setEmplacementaccid(rs.getString("emplacementaccid"));
                c.setPhotoaccid(rs.getString("photoaccid"));
                c.setDescriptiondegat(rs.getString("descriptiondegat"));
                c.setObservations(rs.getString("observations"));
                c.setMail(rs.getString("mail"));
                c.setDate_creation(rs.getString("date_creation"));
                myList.add(c);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        return myList;
  
    }
    
    
    public void modifier(Constat c) throws SQLException{
        String requete4 = "UPDATE `constat` SET `nomclient_e` = '"+c.getNomclient_e()+"', `prenomclient_e` = '"+c.getPrenomclient_e()+"', `typevehicule_e` = '"+c.getTypevehicule_e()+"', `marquevehicule_e` = '"+c.getMarquevehicule_e()+"', `assuranceclient_e` = '"+c.getAssuranceclient_e()+"', `adresseclient_e` = '"+c.getAdresseclient_e()+"', `emplacementaccid` = '"+c.getEmplacementaccid()+"', `photoaccid` = '"+c.getPhotoaccid()+
                "', `descriptiondegat` = '"+c.getDescriptiondegat()+"', `observations` = '"+c.getObservations()+"', `numcontrat_e` = '"+c.getNumcontrat_e()+"', `id_expert_id` = '"+c.getExpert().getId()+"', `id_vehicule_id` = '"+c.getVehicule().getId()+"', `mail` = '"+c.getMail()+"', `date_creation` = '"+c.getDate_creation()+"' WHERE `constat`.`id` = "+c.getId();
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete4);
        System.out.println("Votre Constat est modifiée avec succés");            
    }

    public void supprimer(int id) throws SQLException{
        String requete5 = "DELETE FROM `constat` WHERE id ="+id;
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete5);
        System.out.println("Votre Constat est supprimée avec succés");            
    }
    
     public ObservableList<Constat> getconstatList() throws SQLException
    {
        ObservableList<Constat> constatlist = FXCollections.observableArrayList();
        
        Statement ps=cnx2.createStatement();
        String sql = "select * from constat";
        ResultSet rs;
        rs = ps.executeQuery(sql);
        Constat constat;
        while (rs.next()) {
           constat = new Constat(rs.getString("nomclient_e"),rs.getString("prenomclient_e"),rs.getString("typevehicule_e"),rs.getString("marquevehicule_e"),rs.getString("assuranceclient_e"),rs.getString("adresseclient_e"),rs.getString("emplacementaccid"),rs.getString("photoaccid"),rs.getString("descriptiondegat"),rs.getString("observations"),rs.getString("numcontrat_e"),rs.getString("mail"),rs.getString("date_creation"));  
           constatlist.add(constat);

        }
         return constatlist;    
    }
    
    
}
