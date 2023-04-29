/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import edu.esprit.entities.Client;
import edu.esprit.entities.CustomerSatisfactionAPI;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.User;
import edu.esprit.services.ReclamationCRUD;
import static edu.esprit.tests.mainfx.stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Duration;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.controlsfx.control.Rating;

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
    private Label msg;
    @FXML
    private ImageView rating1;
    @FXML
    private ImageView rating2;
    @FXML
    private ImageView rating3;
    @FXML
    private ImageView rating4;
    @FXML
    private ImageView rating5;
  
    private int ratingValue = 0;
  
    private Label statusLabel;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
         // Ajouter un écouteur d'événements de clic à chaque image
        rating1.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating1.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
        rating2.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating2.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
        rating3.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating3.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
        rating4.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating4.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
        rating5.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating5.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });  
    }  
    
     private void incrementerNote() {
    // Vérifier si la note est inférieure à 5 avant de l'incrémenter
    if (ratingValue < 5) {
        ratingValue++;
    }
 
    tfnote.setText(Integer.toString(ratingValue));
}


    @FXML
    private void saveReclamation(ActionEvent event) {
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
    
    // créer un objet Reclamation avec les informations saisies
    Reclamation reclamation = new Reclamation();
    reclamation.setDescription(description);
    reclamation.setObjet(objet);
    reclamation.setNote(note);
    
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
         // Afficher la valeur de la note dans la console
        System.out.println("La note est: " + ratingValue);
        // Créez une instance de la classe Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Définissez l'en-tête de l'alerte
        alert.setHeaderText(null);
        // Définissez le texte de l'alerte
        alert.setContentText("Votre réclamation a été ajoutée avec succès.");

        // Affichez l'alerte
        alert.showAndWait();
          
  
// Création de la boîte de dialogue de confirmation
Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
confirmationAlert.setTitle("Confirmation");
confirmationAlert.setHeaderText("Voulez-vous vraiment continuer pour voir le détails de votre reclamation ?");
confirmationAlert.setContentText("Cliquez sur OK pour continuer ou sur Annuler pour annuler.");

// Affichage de la boîte de dialogue de confirmation
Optional<ButtonType> result = confirmationAlert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK){
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
} else {
    // L'utilisateur a cliqué sur Annuler ou fermé l'alerte
    // Ne rien faire
}

        
      

    }

  

  

    
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC062a98dbd60baecf3aef6b189b39fc78";
    public static final String AUTH_TOKEN = "9c9ae92dc594428b53a42c42d252b8a4";
    @FXML
    private void sms(ActionEvent event) {
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21629132895"),
                new com.twilio.type.PhoneNumber("+16814024311"), 
                "Je suis un(e) client(e) fidèle de votre agence depuis 2ans, et j'espère que nous pourrons travailler ensemble pour résoudre ce problème et continuer à maintenir une relation positive à long terme.\n" +
                "\n" +
                "Je vous remercie de prendre le temps de lire cette lettre de réclamation et j'attends votre réponse avec impatience.")
            .create();

        System.out.println(message.getSid());
    }

  @FXML
private void feedback(ActionEvent event) {
    
 
}

    
    




    
}