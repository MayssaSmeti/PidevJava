/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import Pidev.Entities.Rapport;
import Pidev.Entities.Rendezvous;
import Pidev.Services.ServiceRapport;
import Pidev.Services.ServiceRendezvous;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AddRendezvousCardController implements Initializable {


      private static final String ACCOUNT_SID = "ACeb4f42e01aed761f46989f2f9ddbdc08";
    private static final String AUTH_TOKEN = "02e7513394c1c4e5f44e0f3765892a8f";
    private static final String FROM_PHONE_NUMBER = "+15674302677";
    private static final String TO_PHONE_NUMBER = "+21654207503";

    
    
    @FXML
    private DatePicker datePicker;

   
 
    @FXML
    private ComboBox<Rapport> type_id;
    @FXML
    private Button add_new_RendezvousBtn;
 
    
      private ServiceRendezvous rendezvousService;
    private ServiceRapport rapportService;
    @FXML
    private TextField tf_lieu;
    @FXML
    private Text productName;
    @FXML
    private HBox cinInputErrorHbox;
    @FXML
    private HBox dateduErrorInputHbox;
    @FXML
    private HBox dateauErrorInputHbox;
    @FXML
    private HBox photoInputErrorHbox;

    /**
     * Initializes the controller class.
     */
  @Override
public void initialize(URL url, ResourceBundle rb) {
    rendezvousService = new ServiceRendezvous();
    rapportService = new ServiceRapport();
    List<Rapport> rapports = rapportService.getAll();
    type_id.getItems().addAll(rapports);
}




    @FXML
    private void addNewRendezvous(MouseEvent event) {
        
        
   
    }

    @FXML
    private void addnewrdvBtn(ActionEvent event) {
        
        
             LocalDate selectedDate = datePicker.getValue();
    LocalDate now = LocalDate.now();
    LocalDate oneYearFromNow = now.plusYears(1);
    
    if (selectedDate == null || selectedDate.isBefore(now) || selectedDate.isAfter(oneYearFromNow)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une date entre aujourd'hui et un an à partir d'aujourd'hui");
        alert.showAndWait();
    } else if (tf_lieu.getText().isEmpty() || type_id.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs et sélectionner ");
        alert.showAndWait();
    } else {
        Date date = Date.valueOf(selectedDate);
        String lieu = tf_lieu.getText();
        Rapport rapport = type_id.getValue();
        Rendezvous rendezvous = new Rendezvous(date, lieu, rapport);
        rendezvousService.ajouter(rendezvous);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("RendezVous ajouté");
        alert.showAndWait();
          // Send confirmation message
          String messageText = "Votre rendez-vous du " + date.toString() + " à " + lieu + " a été ajouté avec succès !";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(TO_PHONE_NUMBER),
                new PhoneNumber(FROM_PHONE_NUMBER),
               messageText
        ).create();
        System.out.println(message.getSid());

        
    }
        
        
        
    }
    
   
}
