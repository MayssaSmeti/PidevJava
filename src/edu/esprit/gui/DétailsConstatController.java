/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DétailsConstatController implements Initializable {

    static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfmarque;
    @FXML
    private TextField tfassurance;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfemplacement;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfphoto;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfobservations;
    @FXML
    private TextField tfnum;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void setTfNom(String message){
        this.tfnom.setText(message);
    }
      public void setTfPrenom(String message){
        this.tfprenom.setText(message);
    }
       public void setTfType(String message){
        this.tftype.setText(message);
    }
        public void setTfMarque(String message){
        this.tfmarque.setText(message);
    }
         public void setTfAssurance(String message){
        this.tfassurance.setText(message);
    }
          public void setTfAdresse(String message){
        this.tfadresse.setText(message);
    }
           public void setTfEmplacement(String message){
        this.tfemplacement.setText(message);
    }
            public void setTfPhoto(String message){
        this.tfphoto.setText(message);
    }
             public void setTfDescription(String message){
        this.tfdescription.setText(message);
    }
              public void setTfObservations(String message){
        this.tfobservations.setText(message);
    } 
              public void setTfNum(String message){
        this.tfnum.setText(message);
    }
             public void setTfMail(String message){
        this.tfmail.setText(message);
    }
           public void setTfDate(String message){
        this.tfdate.setText(message);
    }
  
           
          
      
      
     
    
    
}
