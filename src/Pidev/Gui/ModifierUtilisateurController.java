/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;


import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class ModifierUtilisateurController implements Initializable {

    @FXML
    private Button btnModifier;
    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxprenom;
    @FXML
    private TextField fxemail;
    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxnum;
    @FXML
    private TextField fxadresse;
    @FXML
    private TextField fxrole;
    @FXML
    private TextField fxid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Modifieruser(ActionEvent event) {
          if (fxnom.getText().isEmpty() || fxprenom.getText().isEmpty()||fxemail.getText().isEmpty() || fxcin.getText().isEmpty()|| fxnum.getText().isEmpty() || fxadresse.getText().isEmpty()||fxrole.getText().isEmpty() ){
            Alert a = new Alert(Alert.AlertType.ERROR, "il faut remplir tous les champs ! ", ButtonType.OK);
            a.showAndWait();
          }else{
          UserCrud rec= new UserCrud();
        
        // Integer id=Integer.parseInt(fxid.getText());
         Integer cin=Integer.parseInt(fxcin.getText()); //conversion 
         Integer num_tel=Integer.parseInt(fxnum.getText());
         String nom= fxnom.getText();
         String prenom= fxprenom.getText();
         String roles = fxrole.getText();
         String adresse= fxadresse.getText();
         String email= fxemail.getText();
         
         User R;
          R = new User( email, nom, prenom, cin, adresse, num_tel, roles);
           rec.modifierUtilisateur(R);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("4 roues assurances :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Utilsateur modifié");
                alert.showAndWait(); 
          }
             

    }
     public void setText(User user) //modifier les donnes eli hatynehom 
    {
     
       // String id =String.valueOf(user.getId()); //transformation : convertion de la valeur  du id vers un string 
       // fxcin.setText(id);
        String cin =String.valueOf(user.getCin());
        fxcin.setText(cin);
        fxnom.setText(user.getNom());
        fxprenom.setText(user.getPrenom());

        fxadresse.setText(user.getAdresse());
        String num =String.valueOf(user.getNum_tel());
        fxnum.setText(num);
        fxemail.setText(user.getEmail());
     
    }

    void setTextFields(User user) ///taabili le fenetre bel les donnes 
    {
      // fxid.setText(String.valueOf(user.getId()));
       fxnom.setText(user.getNom());
       fxprenom.setText(user.getPrenom());
       fxemail.setText(user.getEmail());
       fxcin.setText(String.valueOf(user.getCin()));
        fxnum.setText(String.valueOf(user.getNum_tel()));
        fxadresse.setText(user.getAdresse());
        fxrole.setText(user.getRoles());
    }

    @FXML
    private void close(MouseEvent event) { 
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    
    
}
