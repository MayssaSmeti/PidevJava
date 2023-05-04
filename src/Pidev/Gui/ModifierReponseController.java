/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Services.ReponseCRUD;
import Pidev.Entities.Reponse;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ModifierReponseController implements Initializable {

    @FXML
    private TextArea taDescription;
    @FXML
    private Button btnModifier;
    @FXML
    private Label lblIdReponse;

    private Reponse reponse;
    private ReponseCRUD rc = new ReponseCRUD ();

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
        lblIdReponse.setText(String.valueOf(reponse.getId()));

      
        taDescription.setText(reponse.getDescription());
     
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierReponse(ActionEvent event) {
    
        String description = taDescription.getText();
 

        // Vérifier si les champs sont vides
        if ( description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

      

        // Mettre à jour la réclamation
      
        reponse.setDescription(description);
      
        rc.modifier(reponse);

        // Afficher une notification
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Réclamation modifiée avec succès!", ButtonType.OK);
        alert.showAndWait();

        // Fermer la fenêtre de modification
        taDescription.getScene().getWindow().hide();
    }
    
}
