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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author rayen
 */
public class ServiceRapport implements IService<Rapport> {

    Connection cnx = DataSource.getInstance().getCnx();

 @Override
public void ajouter(Rapport ra) {
    try {
//        if (ra.getDescription().isEmpty() || ra.getRapportpreliminaire().isEmpty() || ra.getRapportexpertise().isEmpty() || ra.getImage().isEmpty() ||
//            ra.getDescription().equals(ra.getRapportpreliminaire()) || ra.getDescription().equals(ra.getRapportexpertise()) || ra.getDescription().equals(ra.getImage()) ||
//            ra.getRapportpreliminaire().equals(ra.getRapportexpertise()) || ra.getRapportpreliminaire().equals(ra.getImage()) ||
//            ra.getRapportexpertise().equals(ra.getImage())) {
//            throw new IllegalArgumentException("Tous les champs doivent etre remplis avec differentes valuers");
//        }
        
        String req = "INSERT INTO rapport (description, rapportpreliminaire, rapportexpertise, image) VALUES ('" + ra.getDescription() + "', '" + ra.getRapportpreliminaire() + "',  '"+ra.getRapportexpertise() +"',  '"+ ra.getImage() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Rapport créé !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }
}


    
    public void ajouter2(Rapport ra) {
        try {
            if(ra.getDescription() == null || ra.getRapportpreliminaire() == null || ra.getRapportexpertise() == null || ra.getImage() == null 
           || ra.getDescription().isEmpty() || ra.getRapportpreliminaire().isEmpty() || ra.getRapportexpertise().isEmpty() || ra.getImage().isEmpty()) {
            System.out.println("Un ou plusieurs champs obligatoires sont manquants.");
            return;
        }
        
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
    
     public void delete(Rapport t) {
        String requete="delete from rapport where id = "+t.getId();
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRapport.class.getName()).log(Level.SEVERE, null, ex);
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
    public Rapport getOneOffre(int idOffre) throws SQLException {
        String req = "SELECT * FROM `rapport` where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idOffre);

        ResultSet rs = ps.executeQuery();
        Rapport o =new Rapport();
        
        while (rs.next()) {
        
                    o.setId(rs.getInt(1));
                    o.setDescription(rs.getString("description"));
                    o.setRapportpreliminaire(rs.getString("rapportpreliminaire"));
                    o.setRapportexpertise(rs.getString("rapportexpertise"));
                    o.setImage(rs.getString("image"));
        }
        ps.close();
        return o;
    }
     public Rendezvous getOneContrat(int idc) throws SQLException{
         return null;
     }
     public List<Rapport> searchByDescription(String searchQuery) {
    // Retrieve all rapports
    List<Rapport> allRapports = getAll();

    // Filter rapports by description
    List<Rapport> filteredRapports = allRapports.stream()
            .filter(rapport -> rapport.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
            .collect(Collectors.toList());

    return filteredRapports;
}

     }

