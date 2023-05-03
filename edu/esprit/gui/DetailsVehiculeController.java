/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

<<<<<<< HEAD
import edu.esprit.entities.Vehicule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
=======
import edu.esprit.entities.Client;
import edu.esprit.entities.Vehicule;
import edu.esprit.services.VehiculeCRUD;
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
>>>>>>> 0326fe5 (api)
import javafx.scene.control.TableColumn;
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
<<<<<<< HEAD
=======

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        String matricule = tfmatricule.getText();
            String type = tftype.getText();
            String marque = tfmarque.getText();
            String nb_ch = tfnb_ch.getText();
           
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
            vcd.supprimer(v.getId());
            
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
>>>>>>> 0326fe5 (api)
        
      
    
    
    
}
