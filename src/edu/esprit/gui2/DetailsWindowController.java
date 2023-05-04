/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfobjet;
    @FXML
    private TextField tfnote;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnmo;
    @FXML
    private Button btnsupp;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setTextDescription(String message){
        this.tfdescription.setText(message);
    }
    
    public void setTextObjet(String message){
        this.tfobjet.setText(message);
    }
     
    public void setTextNote(String message){
        this.tfnote.setText(message);
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Reclamation.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(((Node)event.getSource()).getScene().getWindow());
        popupStage.setScene(new Scene(root));
        popupStage.showAndWait();
    } catch (IOException ex) {
        Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
    }
         
    }

    @FXML
    private void modifier(ActionEvent event) {
        // Récupérer les nouvelles valeurs des champs de texte
        String description = tfdescription.getText();
        String objet = tfobjet.getText();
        String note = tfnote.getText();
       // Mettre à jour les données dans la base de données ou dans une autre source de données

       // Retourner à la fenêtre de liste des réclamations
       retour(event);

    }

    @FXML
    private void supprimer(ActionEvent event) {
        // Afficher une boîte de dialogue de confirmation pour la suppression
       Alert alert = new Alert(AlertType.CONFIRMATION);
       alert.setTitle("Confirmation de suppression");
       alert.setHeaderText("Voulez-vous vraiment supprimer cette réclamation ?");
       alert.setContentText("Cliquez sur OK pour confirmer la suppression.");
       Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK){
       // Supprimer les données dans la base de données ou dans une autre source de données
       
      // Retourner à la fenêtre de liste des réclamations
      retour(event);
     }
    }
    
}
