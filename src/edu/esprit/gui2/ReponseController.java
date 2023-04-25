/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import edu.esprit.entities.Assureur;
import edu.esprit.entities.Reclamation;
import edu.esprit.services.ReponseCRUD;
import edu.esprit.entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ReponseController implements Initializable {

   
    @FXML
    private TextField tfdesc;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private Label tfDesc;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveReponse(ActionEvent event) {
        String description = tfdesc.getText();
        
        Assureur a=new Assureur(1);
        Reclamation r=new Reclamation(5);
        
        
        Reponse re = new Reponse(description,r,a);
        ReponseCRUD rec = new ReponseCRUD();
        rec.ajouterReponse2(re);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListerReponse.fxml"));
        
        try {
            Parent root = loader.load();
            ListerReponseController dwc = loader.getController();
            dwc.setTextDescription(re.getDescription());
            
            
            tfdesc.getScene().setRoot(root);
        } catch (IOException ex) {
             System.out.println("Error: "+ex.getMessage());
        }
    }
    
}
