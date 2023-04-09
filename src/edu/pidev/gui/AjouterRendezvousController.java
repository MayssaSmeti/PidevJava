/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import edu.pidev.entities.Rendezvous;
import edu.pidev.services.ServiceRendezvous;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AjouterRendezvousController implements Initializable {

    /**
     * Initializes the controller class.
     */
     
 @FXML
 private DatePicker myDatePicker;
 @FXML
 private Label myLabel;
 
   @FXML
    private TextField tf_lieu;
   
 public void getDate(ActionEvent event) {
  
  LocalDate myDate = myDatePicker.getValue();
  String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
  myLabel.setText(myFormattedDate);
 }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

 private void ajouterRendezvous(javafx.event.ActionEvent event) throws IOException {
     
        if ( tf_lieu.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, " lieu invalide(s)", ButtonType.OK);
            a.showAndWait();
        } else {
            try {
                ServiceRendezvous sr = new ServiceRendezvous();
                Rendezvous r = new Rendezvous(myDatePicker.getValue(), tf_lieu.getText());
                sr.ajouter(r);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Rendezvous ajouté !", ButtonType.OK);
                a.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRendezvous.fxml"));
                Parent root = loader.load();
                myDatePicker.getScene().setRoot(root);
                
                AfficherRendezvousController apc = loader.getController();
                apc.setDate(myDatePicker.getDate());
                apc.setLieu(tf_lieu.getText());
                
            } catch (SQLException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                a.showAndWait();
            }
        }

    }
}
