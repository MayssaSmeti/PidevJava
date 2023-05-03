/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Client;
import edu.esprit.entities.Vehicule;
import edu.esprit.services.VehiculeCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class VehiculeController implements Initializable {

    @FXML
    private TextField tfmatricule;
    @FXML
    private ComboBox tftype;
    @FXML
    private ComboBox tfmarque;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField tfnb_ch;
    @FXML
    private Text messagemat;
    @FXML
    private Text messagenb;
    @FXML
    private Text messagetype;
    @FXML
    private Text messagemarque;

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
    }    

    @FXML
    private void EnregistrerVehicule(ActionEvent event) throws SQLException, IOException {
      
            String matricule = tfmatricule.getText();
            String type = tftype.getSelectionModel().getSelectedItem().toString();
            String marque = tfmarque.getSelectionModel().getSelectedItem().toString();
            String nb_ch = tfnb_ch.getText();
            
            if(!matricule.matches("[0-9]+") || matricule.isEmpty()){
                 messagemat.setText("Saisie invalide! Veuillez saisir uniquement des chiffres.");
                 return;
             }
             if(type.isEmpty() ){
                 messagetype.setText("Veuillez choisir le type de votre véhicule!");
                 return;
             }
             if(marque.isEmpty()){
                  messagemarque.setText("Veuillez choisir la marque de votre véhicule!");
                  return;
             }
             if(!nb_ch.matches("[0-9]+") || nb_ch.isEmpty()){
                 messagenb.setText("Saisie invalide! Veuillez saisir uniquement des chiffres.");
                 return;
             }
          
            Client client = new Client(1);
            
            Vehicule v = new Vehicule(matricule,type,marque,nb_ch,client);
            VehiculeCRUD vcd = new VehiculeCRUD();
            vcd.ajouterVehicule2(v);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Véhicule ajouter avec succées!",ButtonType.OK);
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
    
    
}
 