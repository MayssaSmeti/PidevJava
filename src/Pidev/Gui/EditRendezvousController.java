/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Rapport;
import Pidev.Entities.Rendezvous;
import Pidev.Services.ServiceRendezvous;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author rayen
 */
public class EditRendezvousController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField tfLieu;
     private ServiceRendezvous rendezvousService;
  private Rendezvous rendezvous;
    private Stage dialogStage;
        private Rendezvous selectedRdv;

    
    private boolean okClicked = false;
    @FXML
    private Button btnValider;
    @FXML
    private TextField tfId;

    
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
     public boolean isOkClicked() {
        return okClicked;
    }
public void setRendezvous(Rendezvous rendezvous) {
        this.rendezvous = rendezvous;
        datePicker.setValue(rendezvous.getDate().toLocalDate());
        tfLieu.setText(rendezvous.getLieu());
    }

    private void handleOk() {
        if (isInputValid()) {
            rendezvous.setDate(java.sql.Date.valueOf(datePicker.getValue()));
            rendezvous.setLieu(tfLieu.getText());

            rendezvousService.modifier1(rendezvous);
            okClicked = true;
            dialogStage.close();
        }
    }

    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (datePicker.getValue() == null) {
            errorMessage += "Date invalide!\n";
        }

        if (tfLieu.getText() == null || tfLieu.getText().length() == 0) {
            errorMessage += "Lieu invalide!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
     public void initData(Rendezvous rendezvous) {
        this.rendezvous = rendezvous;
        datePicker.setValue(rendezvous.getDate().toLocalDate());
        tfLieu.setText(rendezvous.getLieu());
        tfId.setText(Integer.toString(rendezvous.getId()));
        
    }
     
     /**
     
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validerModifications(ActionEvent event) {
    /*     LocalDate localDate = datePicker.getValue();
    Date date = Date.valueOf(localDate);
    String lieu = tfLieu.getText();

    if (lieu.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
    } else {
        Rendezvous rendezvous = new Rendezvous(date, lieu);
        rendezvous.setId(selectedRdv.getId());
        rendezvousService.modifier(rendezvous);
        table_rdv.refresh();

        Stage stage = (Stage) btnValider.getScene().getWindow();
        stage.close();
    }
        
    */
    
      
             LocalDate selectedDate = datePicker.getValue();
    LocalDate now = LocalDate.now();
    LocalDate oneYearFromNow = now.plusYears(1);
 
   
        Date date = Date.valueOf(selectedDate);
        String lieu = tfLieu.getText();
        int id=Integer.parseInt(tfId.getText());        //Rendezvous selectedRdv = table_rdv.getSelectionModel().getSelectedItem();
      // Rendezvous selectedRdv = table_rdv.getItems().get(0);
        System.out.println(id);
       
        Rendezvous rendezvous = new Rendezvous(id,lieu, date);
        System.out.println(rendezvous);
        rendezvousService.modifier1(rendezvous);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("RendezVous modifié");
        alert.showAndWait();
        
        
    
        
    
    }
    
}

