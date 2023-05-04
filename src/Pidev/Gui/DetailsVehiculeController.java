/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Client;
import Pidev.Entities.Vehicule;
import Pidev.Services.VehiculeCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailsVehiculeController implements Initializable {

    @FXML
    private TextField tfmatricule;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfmarque;
    @FXML
    private TextField tfnb_ch;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    public void setTfMatricule(String message){
        this.tfmatricule.setText(message);
    }
    
    public void setTfType(String message){
        this.tftype.setText(message);
    }
      
    public void setTfMarque(String message){
        this.tfmarque.setText(message);
    }
    
    public void setTfNb_Ch(String message){
        this.tfnb_ch.setText(message);
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        String matricule = tfmatricule.getText();
            String type = tftype.getText();
            String marque = tfmarque.getText();
            String nb_ch = tfnb_ch.getText();
            
                        
            if(!matricule.matches("[0-9]+") || matricule.isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saisie invalide! Veuillez saisir uniquement des chiffres",ButtonType.OK);
                 alert.showAndWait();
             }
             if(type.isEmpty() ){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION,"Veuillez choisir le type de votre véhicule!",ButtonType.OK);
                 alert.showAndWait();
             }
             if(marque.isEmpty()){
                  Alert alert = new Alert(Alert.AlertType.INFORMATION,"Veuillez choisir la marque de votre véhicule!",ButtonType.OK);
                  alert.showAndWait();
             }
             if(!nb_ch.matches("[0-9]+") || nb_ch.isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saisie invalide! Veuillez saisir uniquement des chiffres.",ButtonType.OK);
                 alert.showAndWait();
             }
           
            Client client = new Client(1);
            
            Vehicule v = new Vehicule(matricule,type,marque,nb_ch,client);
            VehiculeCRUD vcd = new VehiculeCRUD();
            vcd.modifier(v);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Véhicule modifier avec succées!",ButtonType.OK);
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsVehicule.fxml"));
        
        try {    
            Parent root = loader.load();
            DetailsVehiculeController dvc = loader.getController();
            dvc.setTfMatricule(v.getMatricule());
            dvc.setTfType(v.getType());
            dvc.setTfMarque(v.getMarque());
            dvc.setTfNb_Ch(v.getNb_ch());
            
            tfmatricule.getScene().setRoot(root);
            
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
            Client client = new Client(1);
            
            Vehicule v = new Vehicule();
            VehiculeCRUD vcd = new VehiculeCRUD();
            vcd.supprimerVehicule(v.getId());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Véhicule supprimer avec succées!",ButtonType.OK);
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsVehicule.fxml"));
        
        try {    
            Parent root = loader.load();
            DetailsVehiculeController dvc = loader.getController();
            dvc.setTfMatricule(null);
            dvc.setTfType(null);
            dvc.setTfMarque(null);
            dvc.setTfNb_Ch(null);
            
            tfmatricule.getScene().setRoot(root);
            
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
    }
        
      
    
    
    
}
