/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.User;
import Pidev.Utilis.MyConnection;
import Pidev.Utilis.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class FrontDashboardControler implements Initializable {

    private Pane content_area;
    
     @FXML
    private AnchorPane fxFront;
    @FXML
    private TextField fxemail;
    @FXML
    private TextField fxpassword;
    @FXML
    private Button btnconnexion;
    @FXML
    private Button btninscrit;
    @FXML
    private Hyperlink mtpoublie;
    
    
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // try {
            // TODO
            //Parent fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            //content_area.getChildren().removeAll();
            //ontent_area.getChildren().setAll(fxml);
        //} catch (IOException ex) {
          //  Logger.getLogger(FrontDashboardControler.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }    
     public boolean verifierEmailNonDuplique(String email) {
        String requete = "SELECT * FROM utilisateur WHERE email = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        Connection Ds = MyConnection.getInstance().getCnx();
        boolean emailExiste = false;

        try {
            statement = Ds.prepareStatement(requete);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                emailExiste = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return !emailExiste;
    }

    @FXML
    private void login(ActionEvent event) throws SQLException {
        if (fxemail.getText().equals("mayssa@gmail.com") && fxpassword.getText().equals("06102020")) {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("4roues assurrances : Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Bienvenu Admin");
                alert.showAndWait();
                
                Parent root = FXMLLoader.load(getClass().getResource("AdminDasshboard.fxml"));
                Scene scene;
                scene = new Scene(root);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                Parent fxml = FXMLLoader.load(getClass().getResource("AdminDasshboard.fxml"));
                 fxFront.getChildren().removeAll();
                 fxFront.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(FrontDashboardControler.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        } else {

            String query2 = "select * from user where email=?  and password=?";
            cnx = MyConnection.getInstance().getCnx();

            PreparedStatement smt = cnx.prepareStatement(query2);

            smt.setString(1, fxemail.getText());
            smt.setString(2, fxpassword.getText());
            ResultSet rs = smt.executeQuery();
            User p;
            if (rs.next()) {
                try {
                    p = new User(rs.getString("email"), rs.getString("nom"), rs.getString("prenom"), rs.getString("password"), rs.getInt("cin"), rs.getString("adresse"), rs.getInt("num_tel"));
                    User.setCurrent_User(p);
                    SessionManager.getInstace(rs.getInt("id"), rs.getInt("cin"), rs.getString("email"), rs.getInt("num_tel"), rs.getString("nom"), rs.getString("adresse"), rs.getString("prenom"), rs.getString("roles"));
                    System.out.println(User.Current_User.getEmail());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("4 roues assurances:: Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous etes connecté");
                    alert.showAndWait();
                    
                    Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                    Scene scene;
                    scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.close();
                    
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(FrontDashboardControler.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("4 roues assurances :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password !!");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void inscription(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("SignUp.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
       
    }

    @FXML
    private void ResetPassword(ActionEvent event) {
    }
    
}
