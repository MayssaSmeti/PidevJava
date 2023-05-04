/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;
import Pidev.Entities.Constat;
import Pidev.Entities.DevisItem;
import Pidev.Entities.Locale;
import Pidev.Entities.Rapport;
import Pidev.Entities.Reclamation;
import Pidev.Entities.Rendezvous;
import Pidev.Entities.Reponse;
import Pidev.Entities.User;
import Pidev.Entities.Vehicule;
import Pidev.Utilis.MyConnection;
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
import javafx.collections.ObservableList;

/**
 *
 * @author rayen
 */
public class ServiceRapport implements IService<Rapport> {

    Connection cnx = MyConnection.getInstance().getCnx();

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
    public void supprimer1(int id) {
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
    public void modifier1(Rapport ra) {
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
    public List<Rapport> getAll1() {
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
    List<Rapport> allRapports = getAll1();

    // Filter rapports by description
    List<Rapport> filteredRapports = allRapports.stream()
            .filter(rapport -> rapport.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
            .collect(Collectors.toList());

    return filteredRapports;
}

    @Override
    public void modifier(Rapport p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rapport> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterReclamation2(Reclamation r) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reclamation r) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerRec(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> afficherReclamations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterReponse2(Reponse re) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reponse re) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerRep(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reponse> afficherReponses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterDevis(Rapport p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierDevis(Rapport p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimeDevis(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rapport> getAllDevis() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DevisItem> getDevisItems(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterLocale(Locale p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierLocale(Locale p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimeLocale(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Locale> getLocale() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rapport> searchProduit(String query) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterVehicule() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterVehicule2(Vehicule v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vehicule> afficherVehicules() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Vehicule v) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerVehicule(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Vehicule> getvehiculeList() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterConstat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterConstat2(Constat c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Constat> afficherConstats() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Constat c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerConstat(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Constat> getconstatList() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Constat getOneConstat(int idc) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllContrat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rapport> getAllDevisMec() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     }

