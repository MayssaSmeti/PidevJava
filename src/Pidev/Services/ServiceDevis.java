/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.Constat;
import Pidev.Entities.CurrentUser;
import Pidev.Entities.Devis;
import Pidev.Entities.DevisItem;
import Pidev.Entities.Locale;
import Pidev.Entities.User;
import Pidev.Entities.Mecanicien;
import Pidev.Entities.Expert;
import Pidev.Entities.Locale;
import Pidev.Entities.Rapport;
import Pidev.Entities.Reclamation;
import Pidev.Entities.Rendezvous;
import Pidev.Entities.Reponse;
import Pidev.Entities.Vehicule;
import Pidev.Utilis.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author SCORPIO-12
 */
public class ServiceDevis implements IService<Devis> {

    Connection cnx;

    public ServiceDevis() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterDevis(Devis p) throws SQLException {
        String req = "INSERT INTO `devis` (`id_mecanicien_id`,`id_expert`,`total_ht`,`date`) VALUES (?,?,?,CURRENT_TIMESTAMP)";
        PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, p.getMecanicien().getId());
        st.setInt(2, p.getExpert().getId());
        st.setFloat(3, p.getTotal_ht());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        int idDevis = 0;
        if (rs.next()) {
            idDevis = rs.getInt(1);
        }

        // Insert DevisItems into the DevisItem table
        for (DevisItem item : p.getDevisItems()) {
            req = "INSERT INTO devis_item (`description`, `quantite`, `unitprice`, `totalprice`, `devis_id`) VALUES (?, ?, ?, ?, ?)";
            st = cnx.prepareStatement(req);

            st.setString(1, item.getDescription());
            st.setInt(2, item.getQte());
            st.setDouble(3, item.getUnitprice());
            st.setDouble(4, item.getTotalprice());
            st.setInt(5, idDevis);
            st.executeUpdate();
        }
    }

    public void modifierDevis(Devis p) throws SQLException {
        String req = "UPDATE `devis` SET `id_mecanicien_id` = '" + p.getMecanicien().getId() + "', `id_expert` = '" + p.getExpert().getId() + "',`total_ht` = '" + p.getTotal_ht() + "', `date` = CURRENT_TIMESTAMP WHERE `devis`.`id` = " + p.getId();

        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimeDevis(int id) throws SQLException {
        String req = "DELETE FROM `devis_item` WHERE devis_id =" + id;
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        req = "DELETE FROM `devis` WHERE id =" + id;
        st.executeUpdate(req);
    }

    @Override
    public List<Devis> getAllDevis() throws SQLException {
        List<Devis> lm = new ArrayList<>();
        //Mecanicien l = new Mecanicien(20, "Akrem Zaghdoudi", "akrem.zaghdoudi@esprit.tn");
        Expert ee = new Expert(21, "Houssem Abidi", "akrem.zaghdoudi@esprit.tn");
        try {
            String req = "SELECT * FROM `devis` ";
            Statement ste = cnx.createStatement();
            ResultSet res = ste.executeQuery(req);

            while (res.next()) {

                Devis m = new Devis();
                m.setId(res.getInt("id"));
                m.setMecanicien( CurrentUser.getLoggedInUser());
                m.setTotal_ht(res.getFloat("total_ht"));
                m.setDate(res.getDate("date"));
                String req2 = "SELECT * FROM `user` WHERE id=" + res.getInt("id_expert");
                Statement ste2 = cnx.createStatement();
                ResultSet res2 = ste2.executeQuery(req2);
                while (res2.next()) {
                    System.out.println(res2);
                    m.setNomE(res2.getString("nom"));
                    m.setPrenomE(res2.getString("prenom"));
                    m.setEmailE(res2.getString("email"));
                }
                lm.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return (ArrayList<Devis>) lm;
    }

    @Override
    public List<Devis> getAllDevisMec() throws SQLException {
        List<Devis> lm = new ArrayList<>();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
        //Mecanicien l = new Mecanicien(20, "Akrem Zaghdoudi", "akrem.zaghdoudi@esprit.tn");
        // Expert ee = new Expert(21, "Houssem Abidi", "akrem.zaghdoudi@esprit.tn");
        try {
            System.out.println(CurrentUser.getLoggedInUser().getId());
            String req = "SELECT * FROM `devis` WHERE `id_mecanicien_id`="+CurrentUser.getLoggedInUser().getId();
            Statement ste = cnx.createStatement();
            ResultSet res = ste.executeQuery(req);
            System.out.println(res);

            while (res.next()) {

                Devis m = new Devis();
                m.setId(res.getInt("id"));
                m.setMecanicien( CurrentUser.getLoggedInUser());
                m.setTotal_ht(res.getFloat("total_ht"));
                m.setDate(res.getDate("date"));
                String req2 = "SELECT * FROM `user` WHERE id=" + res.getInt("id_expert");
                Statement ste2 = cnx.createStatement();
                ResultSet res2 = ste2.executeQuery(req2);
                while (res2.next()) {
                   
                    m.setNomE(res2.getString("nom"));
                    m.setPrenomE(res2.getString("prenom"));
                    m.setEmailE(res2.getString("email"));
                    System.out.println(m);
                }
                System.out.println("ééééééééééééééééééé");
                
                lm.add(m);
            }
            System.out.println(lm);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return (ArrayList<Devis>) lm;
    }
    @Override
    public List<DevisItem> getDevisItems(int id) throws SQLException {

        List<DevisItem> lm = new ArrayList<>();

        try {
            String req = "SELECT * FROM `devis_item` WHERE devis_id=" + id;
            Statement ste = cnx.createStatement();
            ResultSet res = ste.executeQuery(req);

            while (res.next()) {

                DevisItem m = new DevisItem();
                m.setId(res.getInt("id"));
                m.setDescription(res.getString("description"));
                m.setQte(res.getInt("quantite"));
                m.setUnitprice(res.getFloat("unitprice"));
                m.setTotalprice(res.getFloat("totalprice"));
                lm.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return (ArrayList<DevisItem>) lm;

    }

    public void updateDevisItem(DevisItem devisItem) throws SQLException {
   
        String query = "UPDATE devis_item SET description = ?, quantite = ?, unitprice = ?, totalprice = ? WHERE id = ?";
        PreparedStatement pstmt = cnx.prepareStatement(query);
        pstmt.setString(1, devisItem.getDescription());
        pstmt.setInt(2, devisItem.getQte());
        pstmt.setDouble(3, devisItem.getUnitprice());
        pstmt.setDouble(4, devisItem.getUnitprice()*devisItem.getQte());
        System.out.println(devisItem.getId());
        pstmt.setInt(5, devisItem.getId());
        pstmt.executeUpdate();
    }

    @Override
    public void ajouterLocale(Locale p) throws SQLException {
        String req = "INSERT INTO `locale` (`adresse`, `id_mecanicien_id`, `nom_entrep`) VALUES (?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, p.getAdresse());
        st.setInt(2, p.getMec().getId());
        st.setString(3, p.getNom_entrep());
        st.executeUpdate();
    }

    @Override
    public void modifierLocale(Locale p) throws SQLException {
        String req = "UPDATE `devis` SET `adresse` = '" + p.getAdresse() + "', `id_mecanicien_id` = '" + p.getMec().getId() + "',`nom_entrep` = '" + p.getNom_entrep();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimeLocale(int id) throws SQLException {
        String req = "DELETE FROM `Locale` WHERE id =" + id;
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public List<Locale> getLocale() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Devis> searchProduit(String query) throws SQLException {
        Mecanicien l = new Mecanicien(1, "Wadii Sdouga", "wadii.sdouga2000@gmail.com");
        Expert e = new Expert(2, "Akrem Zaghdoudi", "akrem.zaghdoudi@esprit.tn");
        List<Devis> lm = new ArrayList<>();
        String sql = "SELECT * FROM devis WHERE name LIKE ?  ORDER BY date DESC";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setString(1, "%" + query + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Devis m = new Devis();
                m.setId(resultSet.getInt("id"));
                m.setMecanicien((Mecanicien) CurrentUser.getLoggedInUser());
                m.setExpert(e);
                m.setTotal_ht(resultSet.getFloat("total_ht"));
                m.setDate(resultSet.getDate("date"));
                lm.add(m);

            }
        }

        return lm;
    }

    @Override
    public void modifier(Devis p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
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
    public List<Devis> getAll() throws SQLException {
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
    public void ajouter(Devis r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer1(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier1(Devis r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Devis> getAll1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rapport getOneOffre(int idOffre) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rendezvous getOneContrat(int idc) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
