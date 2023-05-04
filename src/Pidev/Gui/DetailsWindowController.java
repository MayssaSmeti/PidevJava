/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Client;
import Pidev.Entities.Reclamation;
import Pidev.Services.ReclamationCRUD;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private Button btnmo;
    @FXML
    private Button btnsupp;
    @FXML
    private HBox reponse;
  

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

//    @FXML
//    private void retour(ActionEvent event) {
//        try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Reclamation.fxml"));
//        Parent root = loader.load();
//
//        Stage popupStage = new Stage();
//        popupStage.initModality(Modality.APPLICATION_MODAL);
//        popupStage.initOwner(((Node)event.getSource()).getScene().getWindow());
//        popupStage.setScene(new Scene(root));
//        popupStage.showAndWait();
//    } catch (IOException ex) {
//        Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//         
//    }

    @FXML
    private void modifier(ActionEvent event) {
         String description = tfdescription.getText();
        String objet = tfobjet.getText();
        String noteStr = tfnote.getText();
            
            // Vérifier si les champs sont vides
    if (description.isEmpty() || objet.isEmpty()|| noteStr.isEmpty() ) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs!", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue
        alert.showAndWait();
        return;
    }
    
    
    
    
    int note;
    try {
        // Convertir la note en un entier
        note = Integer.parseInt(noteStr);
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "La note doit être un nombre entier!", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue

        // Appliquer un style personnalisé au message d'erreur
        alert.getDialogPane().getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("my-dialog");

        alert.showAndWait();
        return;
    }
    
    // Vérifier que la note est entre 0 et 20
    if(note < 0 || note > 5) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "La note doit être entre 0 et 5", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue

        // Appliquer un style personnalisé au message d'erreur
        alert.getDialogPane().getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("my-dialog");
        alert.showAndWait();
        return;
    }
    
    // Vérifier si la description est entre 10 et 200 caractères inclusivement
    if (description.length() < 10 || description.length() > 200) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "La description doit être entre 10 et 200 caractères inclusivement.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue

        // Appliquer un style personnalisé au message d'erreur
        alert.getDialogPane().getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("my-dialog");
        alert.showAndWait();
        return;
    }

    // Vérifier si l'objet est entre 5 et 50 caractères inclusivement
    if (objet.length() < 5 || objet.length() > 50) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "L'objet doit être entre 5 et 50 caractères inclusivement.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue

        // Appliquer un style personnalisé au message d'erreur
        alert.getDialogPane().getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("my-dialog");
        alert.showAndWait();
        return;
    }
           
            Client c = new Client(1);
            
           
            Reclamation r = new Reclamation(description,objet,c,note);
            ReclamationCRUD rec = new ReclamationCRUD();
            rec.modifier(r);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Véhicule modifier avec succées!",ButtonType.OK);
            alert.showAndWait();
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow.fxml"));
//        
//        try {    
//            Parent root = loader.load();
//            DetailsWindowController dwc = loader.getController();
//            dwc.setTextDescription(r.getDescription());
//        dwc.setTextObjet(r.getObjet());
//        dwc.setTextNote(""+r.getNote());
//            
//            tfobjet.getScene().setRoot(root);
//            
//        } catch (IOException ex) {
//          System.err.println(ex.getMessage());
//        }

    }

    @FXML
    private void supprimer(ActionEvent event) {
           Client client = new Client(1);
            
            Reclamation r = new Reclamation();
            ReclamationCRUD vcd = new ReclamationCRUD();
            vcd.supprimerRec(r.getId());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Réclamation supprimer avec succées!",ButtonType.OK);
            alert.showAndWait();

    }
    
    


    @FXML
    private void reponse(MouseEvent event) {
      Reclamation r=new Reclamation();
         // Récupérer l'ID de la réclamation sélectionnée
    int idReclamation = r.getId();

    // Vérifier si l'ID de la réclamation est valide
    if (idReclamation <= 0) {
        Alert alert = new Alert(AlertType.ERROR, "Veuillez sélectionner une réclamation.", ButtonType.OK);
        alert.showAndWait();
        return;
    }

    // Charger l'interface d'ajout de réponse
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Reponse.fxml"));
    Parent root;
    try {
        root = loader.load();
    } catch (IOException ex) {
        Logger.getLogger(DetailsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        return;
    }

    // Passer l'ID de la réclamation à l'interface d'ajout de réponse
    ReponseController controller = loader.getController();
    controller.setReclamationId(idReclamation);

    // Afficher l'interface d'ajout de réponse
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(new Scene(root));
    stage.showAndWait();

    // Rafraîchir la fenêtre de détails de la réclamation
    initialize(null, null);
    }
    
    }
    

