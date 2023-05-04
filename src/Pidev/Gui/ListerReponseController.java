/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Assureur;
import Pidev.Entities.Client;
import Pidev.Entities.Reclamation;
import Pidev.Entities.Reponse;
import Pidev.Entities.User;
import Pidev.Services.ReponseCRUD;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
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

//    private void retour(ActionEvent event) {
//        
//              try{
//         FXMLLoader loader= new FXMLLoader(getClass().getResource("./Reponse.fxml"));
//        Parent view_2=loader.load();
//
//        Stage stage;
//             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(view_2);
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException ex) {
//        Logger.getLogger(ReponseController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    }

    @FXML
    private void modifier(ActionEvent event) {
          String description = tfdesc.getText();
      
            
            // Vérifier si les champs sont vides
    if (description.isEmpty()  ) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs!", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue
        alert.showAndWait();
        return;
    }
   
    
    // Vérifier si la description est entre 10 et 200 caractères inclusivement
    if (description.length() < 10 || description.length() > 200) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "La description doit être entre 10 et 200 caractères inclusivement.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue

        // Appliquer un style personnalisé au message d'erreur
        alert.getDialogPane().getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("my-dialog");
        alert.showAndWait();
        return;
    }

   
           
              Assureur a=new Assureur(1);
             Reclamation r=new Reclamation(5);
        
        
        Reponse re = new Reponse(description,r,a);
            ReponseCRUD rec = new ReponseCRUD ();
            rec.modifier(re);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Réponse modifier avec succées!",ButtonType.OK);
            alert.showAndWait();
            
           
    }

    @FXML
    private void supprimer(ActionEvent event) {
            Assureur a=new Assureur(1);
             Reclamation r=new Reclamation(5);
        
        
        Reponse re = new Reponse();
            ReponseCRUD rec = new ReponseCRUD ();
            rec.supprimerRep(r.getId());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Réponse supprimer avec succées!",ButtonType.OK);
            alert.showAndWait();
          
    }

 

  

   
}
