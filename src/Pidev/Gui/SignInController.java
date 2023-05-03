/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.CurrentUser;
import Pidev.Entities.User;
import Pidev.Utilis.MyConnection;
import Pidev.Utilis.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class SignInController implements Initializable {

    @FXML
    private TextField fxemail;
    @FXML
    private TextField fxpassword;
    @FXML
    private Button btnconnexion;
    @FXML
    private Button btninscrit;

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private AnchorPane fxFront;
    @FXML
    private Hyperlink fxforgotpassword;
 Connection DS;

    Statement ste;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) throws SQLException {
        String email = fxemail.getText();
        String pass = hashMotDePasse(fxpassword.getText());
        DS = MyConnection.getInstance().getCnx();

        String req = "SELECT * FROM `user` WHERE email = '" + email + "' AND password = '" + pass + "'";
        ste = DS.createStatement();
        ResultSet result = ste.executeQuery(req);

        if (result.next()) {

            String status = result.getString("status");
            if (status.equals("inactif")) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Votre compte a été désactivé par l'administrateur. Veuillez contacter l'administrateur pour plus d'informations.");
                alert.showAndWait(); 
            } else {
                int userId = result.getInt("id");
                String userName = result.getString("nom");
                String userprenom = result.getString("prenom");
                String useremail = result.getString("email");
                String userPassword = result.getString("password");
                String userRole = result.getString("roles");
                String userStatus = result.getString("status");

                User user = new User(userId, email, req, userprenom, userPassword, req,status);
                CurrentUser.setLoggedInUser(user);
                User loggedInUser = CurrentUser.getLoggedInUser();
                int loggedInUserId = loggedInUser.getId();
                System.out.println("Utilisateur connecté : " + loggedInUserId);
                if (userRole.equals("admin")) {
                    System.out.println("bien!");
                    Stage home = new Stage();

                    try {
                        Parent fxml = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                        Scene scene = new Scene(fxml);
                        home.setScene(scene);
                        home.show();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (userRole.equals("user")&&status.equals("actif")) {

                    Stage home = new Stage();
                    try {
                        Parent fxml = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
                        Scene scene = new Scene(fxml);
                        home.setScene(scene);
                        home.show();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
            alert.showAndWait();
        }
    }

    @FXML
    private void inscription(ActionEvent event) {
           try {
            Parent parent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    public String hashMotDePasse(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void Forgotpass(ActionEvent event) {
        
         FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("ForgotPassword.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        ForgotPasswordController muc = loader.getController(); //recuperation deu controller de modification 
        //mrc.setUpdate(true);

        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
     
}

    