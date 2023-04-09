/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import edu.pidev.entities.Rapport;
import edu.pidev.entities.Rendezvous;
import edu.pidev.utils.DataSource;
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
 * @author abdelazizmezri
 */
public class ServiceRendezvous implements IService<Rendezvous> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Rendezvous r) {
        try {
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
            System.out.println("Rendezvous deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Rendezvous r) {
        try {
            String req = "UPDATE `rendez_vous` SET `date` = '" + r.getDate() + "', `lieu` = '" + r.getLieu() + "',`type_id` ='" + r.getType_id().getId() +"' WHERE `rendez_vous`.`id` = " + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendezvous updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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

}
