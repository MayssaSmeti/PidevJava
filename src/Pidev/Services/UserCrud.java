/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Services;

import Pidev.Entities.User;
import Pidev.Utilis.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mayssa
 */
public class UserCrud {

    Connection cnx2;
    List<User> mylist = new ArrayList<>();

    public UserCrud() {
        cnx2 = MyConnection.getInstance().getCnx();

    }

    public void supprimerUtilisateur(User user) {
        try {
            String requete = "delete from user where id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.executeUpdate();

            System.out.println("Utlisateur est supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterUtilisateur()//insertion des objets d'une maniere statique 
    {
        try {
            //creation requette 
            String requete = "INSERT INTO User (email , nom , prenom, password , cin , adresse, num_tel , roles)" + " VALUES ('mimi2@gmail.com','mimi','lolo','123456','0989654','hdggdhh','1234567','user') ";
            //ramener la requette 
            Statement st = cnx2.createStatement(); //j'ai obtenu l'objet connexion par cerre ligne 
            st.executeUpdate(requete); //change la base de donnes 
            System.out.println("Utilisateur ajouté avec succes ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterUtilisateur2(User u) //ajout dynamiqque 
    {
        try //ajout dynamiqque
        {
            String requete2 =   "INSERT INTO user (`email`,`password`,`nom`,`prenom`,`adresse`,`cin`,`num_tel`,`roles`,`activation_token`,`status`) VALUES (?,?,?,?,?,?,?,?,?,'inactif')" ;
            PreparedStatement pst = cnx2.prepareStatement(requete2); //objet dedie pour les objet dynamique //statement est long  //PreparedStatemt : envoie une requête sans
            //paramètres à la base de données

            pst.setString(1, u.getEmail());
            pst.setString(2, u.getPassword());
            pst.setString(3, u.getNom());
            pst.setString(4, u.getPrenom());
            pst.setString(5, u.getAdresse());
            pst.setInt(6, u.getCin());
            pst.setInt(7, u.getNum_tel());
            pst.setString(8, u.getRoles());
            pst.setString(9,u.getActivation_token()) ;

            pst.executeUpdate();
            System.out.println("Votre utilisateur est ajouté ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<User> afficherUtilisateurs() {

        List<User> mylist = new ArrayList<>();

        try {
            String requete3 = "SELECT * FROM user";
            Statement st = cnx2.createStatement(); //L'interface Statement possède les méthodes
            //nécessaires pour réaliser les requêtes sur la base
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                User u = new User();

                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setPassword(rs.getString("password"));
                u.setCin(rs.getInt("cin"));
                u.setNum_tel(rs.getInt("num_tel"));
                u.setRoles(rs.getString("roles"));
                u.setAdresse(rs.getString("adresse"));
                u.setStatus(rs.getString("status")) ; 

                mylist.add(u);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist;

    }

    public void SupprimerUtilisateurid(int id) {
        try {
            Statement st = cnx2.createStatement(); //j'ai obtenu l'objet connexion par cerre ligne 
            String req = "DELETE FROM user WHERE id = " + id + "";
            st.executeUpdate(req);
            System.out.println("L'utilisateur  avec l'id = " + id + " a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    

    public void modifierUtilisateur(User u) {
        try {
            String requete5 = "UPDATE  user SET  email=?,nom=?,prenom=?,cin=?,adresse=?,num_tel=?,roles=? WHERE id=? ; ";
            PreparedStatement pst = cnx2.prepareStatement(requete5); //objet dedie pour les objet dynamique //statement est long 
            pst.setString(1, u.getEmail());
            pst.setString(2, u.getNom());
            pst.setString(3, u.getPrenom());
            pst.setInt(4, u.getCin());
            pst.setString(5, u.getAdresse());
            pst.setInt(6, u.getNum_tel());
            pst.setString(7, u.getRoles());
            pst.setInt(8, u.getId());
            pst.executeUpdate();

            System.out.println("Utlisateur est modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int chercherUser(String nom) throws SQLException {
        int id = 0;
        String requetee = "SELECT id FROM user where nom ='" + nom + "';";
        PreparedStatement pst = cnx2.prepareStatement(requetee);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public ObservableList<User> chercherUserR(String chaine) {
        String sql = "select user.id,user.nom ,user.prenom,user.cin,user.num_tel,user.adresse,user.email,user.roles  from user ";

        Connection cnx = MyConnection.getInstance().getCnx();
        String ch = "" + chaine + "%";
        System.out.println(sql);
        ObservableList<User> myList = FXCollections.observableArrayList();
        try {

            Statement ste = cnx.createStatement();
            // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee = cnx.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);

            System.out.println(stee);

            ResultSet rs = stee.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setCin(rs.getInt("cin"));
                u.setNum_tel(rs.getInt("num_tel"));
                u.setRoles(rs.getString("roles"));
                u.setAdresse(rs.getString("adresse"));
                myList.add(u);
                System.out.println(" utilisateur  trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<User> RechercheUser(String email) {
        List<User> user = new ArrayList<>();
        try {
            String req = "select * from user WHERE email = '" + email + "'";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setCin(rs.getInt("cin"));
                u.setNum_tel(rs.getInt("num_tel"));
                u.setRoles(rs.getString("roles"));
                u.setAdresse(rs.getString("adresse"));

                user.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return user  ;
    }
    public void updateUserPassword(User user) throws SQLException{
           Connection cnx = MyConnection.getInstance().getCnx();
             String req ="UPDATE user SET `password`=? WHERE email= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.executeUpdate(); 
        }
        
        catch(SQLException ex) {
                System.out.println(ex.getMessage());
        }
            
            
           
    }
    
   
    
    
}
