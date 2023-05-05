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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailsVehiculeController implements Initializable {

    @FXML
    private TextField tfmatricule;
    @FXML
    private ComboBox tftype;
    @FXML
    private ComboBox tfmarque;
    @FXML
    private Spinner<Integer> tfnb_ch;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> listtype = FXCollections.observableArrayList("Voiture","Moto","Camion");
         tftype.setItems(listtype);
         ObservableList<String> listmarque = FXCollections.observableArrayList("Kia","Symbole","Clio","BMW","Range Rover","Ibiza","Mercedes","Toyota","Ford","Honda");
         tfmarque.setItems(listmarque);
         SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
         valueFactory.setValue(1);
         tfnb_ch.setValueFactory(valueFactory);
    }    
     
    public void setTfMatricule(String message){
        this.tfmatricule.setText(message);
    }
    
    public void setTfType(String message){
        this.tftype.setValue(message);
    }
      
    public void setTfMarque(String message){
        this.tfmarque.setValue(message);
    }
    
    public void setTfNb_Ch(String message){
//        this.tfnb_ch.setText(message);
      int intMessage = Integer.parseInt(message); 
      this.tfnb_ch.getValueFactory().setValue(intMessage);
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        String matricule = tfmatricule.getText();
            String type = tftype.getSelectionModel().getSelectedItem().toString();
            String marque = tfmarque.getSelectionModel().getSelectedItem().toString();
            String nb_ch = tfnb_ch.getValue().toString();
            
                        
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
           
            //Client client = new Client(1);
            //Vehicule v = new Vehicule(matricule,type,marque,nb_ch,client);
            
            Vehicule v = new Vehicule(matricule,type,marque,nb_ch);
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
            
            ((Pane)tfmatricule.getParent()).getChildren().setAll(root);
            
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
            
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
            
            ((Pane)tfmatricule.getParent()).getChildren().setAll(root);
            
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
    }
        
      
    
    
    
}
