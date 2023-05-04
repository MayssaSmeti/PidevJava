/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import edu.esprit.entities.Assureur;
import edu.esprit.entities.Client;
import edu.esprit.entities.Reclamation;
import edu.esprit.entities.Reponse;
import edu.esprit.entities.User;
import edu.esprit.services.ReponseCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ListerReponseController implements Initializable {

    @FXML
    private TextField tfdesc;

    @FXML
    private Label tfDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
      
        // TODO
    }    
    
    public void setTextDescription(String message){
        this.tfdesc.setText(message);
    }

    private void retour(ActionEvent event) {
        
              try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./Reponse.fxml"));
        Parent view_2=loader.load();

        Stage stage;
             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

 

  

   
}
