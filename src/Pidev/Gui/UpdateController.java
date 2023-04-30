/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.CurrentUser;
import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import Pidev.Utilis.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class UpdateController implements Initializable {

    @FXML
    private Button btnModifier;
    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxprenom;
    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxnum;
    @FXML
    private TextField fxadresse;

    /**
     * Initializes the controller class.
     */
    User u = new User (); 
    UserCrud us = new UserCrud();
    int id ; 
    Connection DS;  
    @FXML
    private TextField fxemail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) {
           User loggedInUser = CurrentUser.getLoggedInUser();

        // Récupérer les nouvelles informations de l'utilisateur à partir des champs de texte
        String nom = fxnom.getText();
        String prenom = fxprenom.getText();
        String email = fxemail.getText();
      

        // Afficher un dialogue de confirmation avant de mettre à jour les informations de l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment mettre à jour les informations de votre compte ?");
        alert.setContentText("Appuyez sur OK pour confirmer la mise à jour ou sur Annuler pour annuler.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Mettre à jour les informations de l'utilisateur dans la base de données
                DS = MyConnection.getInstance().getCnx();
                String query = "UPDATE user SET nom=?, prenom=?, email=? WHERE id=?";
                PreparedStatement stmt = DS.prepareStatement(query);
                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                stmt.setString(3, email);
               
                stmt.setInt(4, loggedInUser.getId());
                stmt.executeUpdate();
                stmt.close();
                DS.close();

                // Mettre à jour les informations de l'utilisateur dans la session
                loggedInUser.setNom(nom);
                loggedInUser.setPrenom(prenom);
                loggedInUser.setEmail(email);
               

                // Afficher un message de confirmation
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Modification réussie");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Les informations de votre compte ont été modifiées avec succès.");
                successAlert.showAndWait();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Afficher un message d'erreur
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Une erreur s'est produite lors de la modification de votre compte.");
                errorAlert.showAndWait();
            }
        }
        
      
    }

    @FXML
    private void close(MouseEvent event) {
    }
    
}
