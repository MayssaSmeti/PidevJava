/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import Pidev.Entities.Assureur;
import Pidev.Entities.CurrentUser;
import Pidev.Entities.Reclamation;
import Pidev.Services.ReponseCRUD;
import Pidev.Entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ReponseController implements Initializable {

   
    @FXML
    private TextField tfdesc;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private Label tfDesc;
    
    private int currentRec;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
        public void setReclamation(int d) throws SQLException {
        currentRec = d;

     
    }

    @FXML
    private void saveReponse(ActionEvent event) {
        String description = tfdesc.getText();
        
            // Vérifier si les champs sont vides
    if (description.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs!", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue
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
        
       // Assureur a=new Assureur(1);
        Reclamation r=new Reclamation(5);
        
        
        Reponse re = new Reponse(currentRec,description,CurrentUser.getLoggedInUser());
        ReponseCRUD rec = new ReponseCRUD();
        rec.ajouterReponse2(re);
        
         // Créez une instance de la classe Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Définissez l'en-tête de l'alerte
        alert.setHeaderText(null);
        // Définissez le texte de l'alerte
        alert.setContentText("Votre réponse a été ajoutée avec succès.");

        // Affichez l'alerte
        alert.showAndWait();
        
        // Création de la boîte de dialogue de confirmation
Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
confirmationAlert.setTitle("Confirmation");
confirmationAlert.setHeaderText("Voulez-vous vraiment continuer pour voir le détails de votre réponse ?");
confirmationAlert.setContentText("Cliquez sur OK pour continuer ou sur Annuler pour annuler.");

// Affichage de la boîte de dialogue de confirmation
Optional<ButtonType> result = confirmationAlert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK){
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ListerReponse.fxml"));
        
    try {
        Parent root = loader.load();
            
        ListerReponseController dwc = loader.getController();
        dwc.setTextDescription(r.getDescription());
                  
        ((Pane)tfdesc.getParent()).getChildren().setAll(root);
            
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
                "e tiens tout d'abord à vous remercier de votre fidélité envers notre agence pendant ces deux dernières années. Nous sommes toujours ravis de travailler avec des clients fidèles comme vous et de vous aider à atteindre vos objectifs financiers.\n" +
                "\n" +
                "Nous sommes désolés d'apprendre que vous avez rencontré un problème récemment et nous souhaitons vous assurer que nous sommes prêts à travailler avec vous pour le résoudre. Votre satisfaction est notre priorité absolue et nous sommes déterminés à faire tout ce qui est en notre pouvoir pour résoudre cette situation.\n" +
                "\n" +
                "Nous apprécions votre patience et votre compréhension pendant que nous examinons votre réclamation et travaillons à trouver une solution. Nous vous tiendrons informé(e) de nos progrès et nous sommes convaincus que nous pourrons trouver une solution satisfaisante pour vous.\n" +
                "\n" +
                "Encore une fois, je vous remercie de votre confiance envers notre agence et j'espère que nous pourrons continuer à maintenir une relation positive à long terme.")
            .create();

        System.out.println(message.getSid());
    }
}
