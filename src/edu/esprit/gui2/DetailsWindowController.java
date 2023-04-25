/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfobjet;
    @FXML
    private TextField tfnote;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setTextDescription(String message){
        this.tfdescription.setText(message);
    }
    
    public void setTextObjet(String message){
        this.tfobjet.setText(message);
    }
     
    public void setTextNote(String message){
        this.tfnote.setText(message);
    }

    @FXML
    private void retour(ActionEvent event) {
        
           try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./Reclamation.fxml"));
        Parent view_2=loader.load();

        Stage stage;
             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
