/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Services.ServiceRapport;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Pidev.Entities.Rapport;
import Pidev.Entities.Rendezvous;
import Pidev.Services.ServiceRendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AjouterRendezvousController implements Initializable {
  @FXML
    private DatePicker myDatePicker;
    @FXML
    private Label dateLabel;
    @FXML
    private Label lieuLabel;
    @FXML
    private TextField tf_lieu;
    @FXML
    private Label lieuLabel1;
    @FXML
    private ComboBox<Rapport> type_id;

    private ServiceRendezvous rendezvousService;
    private ServiceRapport rapportService;
    @FXML
    private Button afficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

  rendezvousService = new ServiceRendezvous();
  List<Rapport> rapports = rapportService.getAll();
        type_id.getItems().addAll(rapports);
        
        
    }
    
    public AjouterRendezvousController() {
        rendezvousService = new ServiceRendezvous();
        rapportService = new ServiceRapport();
        
    }

    @FXML
    private void valier(ActionEvent event) {
        
     LocalDate selectedDate = myDatePicker.getValue();
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
        alert.setContentText("Veuillez remplir tous les champs et sélectionner un fichier image");
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
    }
}

    @FXML
    private void afficher(ActionEvent event) {
        
         Stage nouveauStage;
    try {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherRendezvous.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception however you want
    }
    }
    
    
    
    
}


