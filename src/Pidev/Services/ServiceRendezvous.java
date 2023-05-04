/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.Rapport;
import Pidev.Entities.Rendezvous;
import Pidev.Utilis.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceRendezvous implements IService<Rendezvous> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Rendezvous r) {
         
        try {
            if (r.getLieu().length() < 4) {
            System.out.println("ce champ doit contenir au  4 characters.");
            return;
        }
            
             Calendar now = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        date.setTime(r.getDate());
        int diffYear = date.get(Calendar.YEAR) - now.get(Calendar.YEAR);
        if (diffYear != 0 || date.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR) > 365) {
            throw new IllegalArgumentException("La date doit se situer dans l'année qui suit");
        }
            
            String req = "INSERT INTO `rendez_vous` (`date`, `lieu`, `type_id`) VALUES ('" + r.getDate() + "', '" + r.getLieu() + "', '" + r.getType_id().getId() + "')"; 
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendezvous created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Rendezvous r) {
        try {
            String req = "INSERT INTO `rendez_vous` (`date`, `lieu`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDate(2, r.getDate());
            ps.setString(1, r.getLieu());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `rendez_vous` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendezvous supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Rendezvous r) {
        System.out.println(r);
        try {
            
                    if (r.getType_id() == null) {
                          System.out.println(r);
            
            String req = "UPDATE `rendez_vous` SET `date` = '" + r.getDate() + "', `lieu` = '" + r.getLieu() +"' WHERE `rendez_vous`.`id` = " + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendezvous modifier !");
                    }
            System.out.println("error");
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     public void delete(Rendezvous r) {
        String requete="delete from rendez_vous where id = "+r.getId();
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRendezvous.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    
    @Override
    public List<Rendezvous> getAll() {
        List<Rendezvous> list = new ArrayList<>();
        try {
            String req = "Select * from rendez_vous";
            Statement st = cnx.createStatement();
            
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Rapport ra=new Rapport();
                 ra.setId(rs.getInt("type_id"));
                Rendezvous r = new Rendezvous(rs.getInt("id"), rs.getDate("date"), rs.getString("lieu"),ra);
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
     public Rapport getOneOffre(int idOffre) throws SQLException {
        return null;
        
    }
     
     public Rendezvous getOneContrat(int idc) throws SQLException {
    String req = "SELECT * FROM `rendez_vous` where id = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, idc);

    ResultSet rs = ps.executeQuery();
    Rendezvous o =new Rendezvous();
    
    while (rs.next()) {
    
                o.setId(rs.getInt(1));
                java.sql.Date dateVal = rs.getDate("date");
                String strVal = dateVal.toString();
                o.setDate(strVal);
               

                
                o.setLieu(rs.getString("lieu"));
    }
    ps.close();
    return o;
}

}
