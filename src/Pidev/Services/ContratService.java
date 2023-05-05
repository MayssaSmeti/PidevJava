package Pidev.Services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//import utils.MyConnection;


import java.sql.Date;
//import Projet.entities.User;
import Pidev.Entities.Contrat;
import Pidev.Entities.Offre;
import Pidev.Utilis.MyConnection;
import java.sql.Statement;

public class ContratService implements IContratService{
    Connection cnx = MyConnection.getInstance().getCnx();
    

    public void ajouter(Contrat o) {
        
        try {
            String requete = "INSERT INTO contrat (validitedu, validiteau, id_client_id, id_vehicule_id, photo_cin) " +
                "VALUES ('" + o.getValiditedu() + "', '" + o.getValiditeau() + "', " + o.getId_client() + ", " + 
                 o.getId_vehicule() + ", '" + o.getPhoto_cin() + "')";PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Votre contrat est  ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void modifier(Contrat o) {
        //o = new Offre(8031, "updated", 150, "titre", "valid" ,"sac.png");
        try {
            String req = "UPDATE `contrat` SET `validitedu` = '" +o.getValiditedu() + "', `validiteau` = '" + o.getValiditeau() + "', `id_vehicule_id` = '" + o.getId_vehicule() + "', `id_client_id` = '" + o.getId_client() + "', `photo_cin` = '" + o.getPhoto_cin() + "' WHERE `contrat`.`id` = " + o.getId();
            //pst.setInt(6, (int) o.getId());
             Statement pst = cnx.createStatement();
            pst.executeUpdate(req);
            System.out.println("Votre contrat est  modifié avec succe");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void supprimer(int id) throws SQLException {
try {
            String req = "DELETE FROM `contrat` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Contrat est supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

        
    public List<Contrat> getAll()  {
 List<Contrat> myList = new ArrayList<>();  
       try {
             String req3="SELECT * FROM contrat";
             Statement st = cnx.createStatement(); 
             ResultSet rs = st.executeQuery(req3);
             while(rs.next()){
                Contrat o =new Contrat();
                 o.setId(rs.getInt(1));
                 o.setValiditedu(rs.getString("validitedu"));
                 o.setValiditeau(rs.getString("validiteau"));
                 o.setId_client(rs.getInt("id_client_id"));
                 o.setId_vehicule(rs.getInt("id_vehicule_id"));
                 o.setPhoto_cin(rs.getString("photo_cin"));;
                 myList.add(o);
             }
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());
         }
         return myList;
}

public Contrat getOneContrat(int idc) throws SQLException {
    String req = "SELECT * FROM `contrat` where id = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, idc);

    ResultSet rs = ps.executeQuery();
    Contrat o =new Contrat();
    
    while (rs.next()) {
    
                o.setId(rs.getInt(1));
                o.setId_client(rs.getInt("id_client_id"));
                java.sql.Date dateVal = rs.getDate("validitedu");
                String strVal = dateVal.toString();
                o.setValiditedu(strVal);
                
                java.sql.Date dateVal2 = rs.getDate("validiteau");
                String strVal2 = dateVal2.toString();
                o.setValiditeau(strVal2);

                o.setId_vehicule(rs.getInt("id_vehicule_id"));
                o.setPhoto_cin(rs.getString("photo_cin"));
    }
    ps.close();
    return o;
}

}
