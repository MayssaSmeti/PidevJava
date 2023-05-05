/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.Constat;
import Pidev.Entities.CurrentUser;
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
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceRendezvous implements IService<Rendezvous> {

    Connection cnx = MyConnection.getInstance().getCnx();

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
            
            String req = "INSERT INTO `rendez_vous` (`date`, `lieu`, `type_id`,`id_client_id`,`id_expert`) VALUES ('" + r.getDate() + "', '" + r.getLieu() + "', '" + r.getType_id().getId() + "', '" + r.getId_client_id()  +"', '" + CurrentUser.getLoggedInUser().getId()+    "')"; 
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
    public void supprimer1(int id) {
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
    public void modifier1(Rendezvous r) {
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
    public List<Rendezvous> getAll1() {
        List<Rendezvous> list = new ArrayList<>();
        try {
            String req = "Select * from rendez_vous";
            Statement st = cnx.createStatement();
            
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Rapport ra=new Rapport();
                 ra.setId(rs.getInt("type_id"));
                 ra.setId(rs.getInt("id_client_id"));
                 ra.setId(rs.getInt("id_expert"));
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
                o.setId_client_id(rs.getInt("id_client_id"));
                o.setId_expert(User.Current_User);
    }
    ps.close();
    return o;
}

    @Override
    public void modifier(Rendezvous p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rendezvous> getAll() throws SQLException {
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
    public void ajouterDevis(Rendezvous p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierDevis(Rendezvous p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimeDevis(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rendezvous> getAllDevis() throws SQLException {
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
    public List<Rendezvous> searchProduit(String query) throws SQLException {
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
    public List<Rendezvous> getAllDevisMec() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
