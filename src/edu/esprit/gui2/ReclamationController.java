/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import edu.esprit.entities.Client;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.User;
import edu.esprit.services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ReclamationController implements Initializable {

  
    
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfobjet;
    @FXML
    private TextField tfnote;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private Label tfDescription;
    @FXML
    private Label tfObjet;
    @FXML
    private Label tfNote;
  
     ReclamationCRUD rc = new ReclamationCRUD();
    @FXML
    private Button btnen;
    @FXML
    private ListView<Reclamation> listReclamations;
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    

    @FXML
    private void saveReclamation(ActionEvent event) {
        String description = tfdescription.getText();
        String objet = tfobjet.getText();
       String noteStr = tfnote.getText();

     // Vérifier si les champs sont vides
    if (description.isEmpty() || objet.isEmpty() || noteStr.isEmpty()) {
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
    
  
   
        User u=new User(1);
        Client c=new Client(1);
          
        Reclamation r = new Reclamation(description,objet,c,note);
        ReclamationCRUD rc = new ReclamationCRUD();
        rc.ajouterReclamation2(r);
        
        // Créez une instance de la classe Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Définissez l'en-tête de l'alerte
        alert.setHeaderText(null);
        // Définissez le texte de l'alerte
        alert.setContentText("Votre réclamation a été ajoutée avec succès.");

        // Affichez l'alerte
        alert.showAndWait();
          
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow.fxml"));
        
        try {
            Parent root = loader.load();
            DetailsWindowController dwc = loader.getController();
            dwc.setTextDescription(r.getDescription());
            dwc.setTextObjet(r.getObjet());
            dwc.setTextNote(""+r.getNote());
            
            tfdescription.getScene().setRoot(root);
        } catch (IOException ex) {
             System.out.println("Error: "+ex.getMessage());
        }     
    }

  

    @FXML
    private void envoyer(ActionEvent event) {
          try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./Sms.fxml"));
        Parent view_2=loader.load();

        Stage stage;
             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(SmsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

   
    
    
    

  
    
  
 

    
}
