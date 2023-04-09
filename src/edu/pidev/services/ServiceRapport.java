/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;
import edu.pidev.entities.Rapport;
import edu.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rayen
 */
public class ServiceRapport implements IService<Rapport> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
  public void ajouter(Rapport ra) {
        try {
            String req = "INSERT INTO `rapport` (`description`, `rapportpreliminaire`,`rapportexpertise`,`image`) VALUES ('" + ra.getDescription() + "', '" + ra.getRapportpreliminaire() + "',  '"+ra.getRapportexpertise() +"',  '" +ra.getImage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rapport créé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Rapport ra) {
        try {
            String req = "INSERT INTO `rapport` (`description`, `rapportpreliminaire`,`rapportexpertise`,`image`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, ra.getDescription());
            ps.setString(1, ra.getRapportpreliminaire());
             ps.setString(3, ra.getRapportexpertise());
              ps.setString(4, ra.getImage());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `rapport` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rapport supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Rapport ra) {
        try {
            String req = "UPDATE `rapport` SET `description` = '" + ra.getDescription() + "', `rapportpreliminaire` = '" + ra.getRapportpreliminaire() + "', `image` = '" + ra.getImage()+"'  WHERE `rapport`.`id` = " + ra.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rapport modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Rapport> getAll() {
        List<Rapport> list = new ArrayList<>();
        try {
            String req = "Select * from rapport";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Rapport ra =  new Rapport(rs.getInt("id"), rs.getString("description"),rs.getString("rapportpreliminaire"),rs.getString("rapportexpertise") ,rs.getString("image"));
            list.add(ra);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
