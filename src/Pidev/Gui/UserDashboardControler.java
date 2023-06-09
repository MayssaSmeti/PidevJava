/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.CurrentUser;
import Pidev.Entities.User;
import Pidev.Utilis.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class UserDashboardControler implements Initializable {

    @FXML
    private Pane content_area;
    private Text logout;
    @FXML
    private Label fxemail;
    @FXML
    private Label fxwelcome;
    private Label fxprenom;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         User loggedInUser = CurrentUser.getLoggedInUser();

        // Afficher les informations de l'utilisateur dans les champs du formulaire
        ///fxnom.setText(loggedInUser.getNom());
        //fxprenom.setText(loggedInUser.getEmail());
        fxemail.setText(loggedInUser.getEmail());
       
       
   }    

    
    private void logouttUser(MouseEvent event) {
         
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Déconnexion");
        alert.setHeaderText("Confirmation de déconnexion");
        alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");

        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    private void modifierUser(MouseEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("UpdateUser.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
        
    }


    private void supprimertUser(MouseEvent event) throws SQLException {
          User loggedInUser = CurrentUser.getLoggedInUser();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimé cet élément ?");
        alert.setContentText("Cette action est irréversible.");

        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
              Connection DS = MyConnection.getInstance().getCnx();
            String query = "DELETE FROM user WHERE id=? ";
            PreparedStatement stmt = DS.prepareStatement(query);
            stmt.setInt(1, loggedInUser.getId());
            stmt.executeUpdate();
            stmt.close();
            DS.close();

            // Afficher un message de confirmation
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Suppression réussie");
            successAlert.setHeaderText(null);
            successAlert.setContentText("L'utilisateur a été supprimé avec succès.");
            successAlert.showAndWait();
            System.out.println("compte supprimé");
        }
    }

    @FXML
    private void changeps(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Password.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
        
    }
    
    
    
    
    
    
    }

    
    

