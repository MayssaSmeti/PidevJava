/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Vehicule;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
<<<<<<< HEAD
=======
import static jdk.nashorn.internal.runtime.Debug.id;
>>>>>>> 0326fe5 (api)

/**
 *
 * @author USER
 */
public class VehiculeCRUD {
    
    Connection cnx2;
    
    public VehiculeCRUD(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
   
    public void ajouterVehicule(){
        String requete = "INSERT INTO vehicule (matricule,type,marque,nb_ch,id_client_id)"
                       +   "VALUES ('123456789','voiture','kia','6',1)";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("véhicule ajoutée avec succés");
            
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());      
        }
        
    }
    
    public void ajouterVehicule2(Vehicule v){
        
            String requete2 = "INSERT INTO vehicule (matricule,type,marque,nb_ch,id_client_id) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, v.getMatricule());
            pst.setString(2, v.getType());
            pst.setString(3, v.getMarque());
            pst.setString(4, v.getNb_ch());
            pst.setInt(5, v.getClient().getId());
            pst.executeUpdate();
            System.out.println("Votre Véhicule est ajoutée");            
                
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());      

        }
        
    }
       
    public List<Vehicule> afficherVehicules() {
        
        List<Vehicule> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM vehicule";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Vehicule v = new Vehicule();
                v.setId(rs.getInt(1));
                v.setMatricule(rs.getString("matricule"));
                v.setType(rs.getString("type"));
                v.setMarque(rs.getString("marque"));
                v.setNb_ch(rs.getString("nb_ch"));
                myList.add(v);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        return myList;
  
    } 
    
     public void modifier(Vehicule v) throws SQLException{
        String requete4 = "UPDATE `vehicule` SET `matricule` = '"+v.getMatricule()+"', `type` = '"+v.getType()+"', `marque` = '"+v.getMarque()+"', `nb_ch` = '"+v.getNb_ch()+"', `id_client_id` = '"+v.getClient().getId()+"' WHERE `vehicule`.`id` = "+v.getId();
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete4);
        System.out.println("Votre Véhicule est modifiée avec succés");            
    }

    public void supprimer(int id) throws SQLException{
        String requete5 = "DELETE FROM `vehicule` WHERE id ="+id;
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete5);
        System.out.println("Votre Véhicule est supprimée avec succés");            
    }
    
     public ObservableList<Vehicule> getvehiculeList() throws SQLException
    {
        ObservableList<Vehicule> vehiculelist = FXCollections.observableArrayList();
        
       Statement ps=cnx2.createStatement();
        String sql = "select * from vehicule";
        ResultSet rs;
        rs = ps.executeQuery(sql);
        Vehicule vehicule;
        while (rs.next()) {
           vehicule = new Vehicule(rs.getString("matricule"),rs.getString("type"),rs.getString("marque"),rs.getString("nb_ch"));  
            vehiculelist.add(vehicule);

        }
         return vehiculelist;    
    }
    
<<<<<<< HEAD
    public List<Vehicule> RechercheVehicule(String marque) {
=======
    /*public List<Vehicule> RechercheVehicule(String marque) {
>>>>>>> 0326fe5 (api)
     List<Vehicule> vehicule = new ArrayList<>();
        try {
            String req ="select * from vehicule WHERE marque = '"+marque+"'";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
               Vehicule v = new Vehicule();
               v.setMatricule(rs.getString("matricule"));
               v.setType(rs.getString("type"));
               v.setMarque(rs.getString("marque"));
               v.setNb_ch(rs.getString("nb_ch"));
               
               vehicule.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
            
<<<<<<< HEAD
           return vehicule;    }
=======
           return vehicule;    }*/

>>>>>>> 0326fe5 (api)
    }
    
    


    

