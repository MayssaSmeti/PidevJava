/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class AjouterController implements Initializable {

    @FXML
    private TextField fxprenom;
    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxemail;
    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxnum;
    @FXML
    private TextField fxadresse;
   
    @FXML
    private Button btnaj;
    @FXML
    private PasswordField fxpassword;
   
    @FXML
    private TextField fxroles;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          // Définir la valeur initiale du champ de texte du code pays
        fxnum.setText("+216");

        // Ajouter un écouteur pour le champ de texte du numéro de téléphone
        fxnum.textProperty().addListener((observable, oldValue, newValue) -> {
            // Si le champ de texte du numéro de téléphone est vide, réinitialiser la valeur du champ de texte du code pays
            if (newValue.isEmpty()) {
                fxnum.setText("+216");
            }
        });

    }

    @FXML
    private void ajouter(ActionEvent event) {

         
        if (fxnom.getText().isEmpty()
                || fxprenom.getText().isEmpty() 
                || fxemail.getText().isEmpty() 
                || fxcin.getText().isEmpty()
                || fxnum.getText().isEmpty() 
                || fxadresse.getText().isEmpty()
                ||fxroles.getText().isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs invalides ! ", ButtonType.OK);
            a.showAndWait();

        } else if (fxpassword.getText().length() < 8) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Mot de passe doit etre >8 caractéres !)", ButtonType.OK);
            a.showAndWait();
        } else if (fxcin.getText().length() < 8 || fxnum.getText().length() < 8) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Votre numero de telephone doit etre superieur à 8chiffres ansi que votre CIN !", ButtonType.OK);
            a.showAndWait();
        } else if (validationEmail() == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Email non valide  !", ButtonType.OK);
            a.showAndWait();

        } else {
            

            UserCrud us = new UserCrud();
            Integer cin = Integer.parseInt(fxcin.getText()); //conversion
            Integer num_tel = Integer.parseInt(fxnum.getText());
            User p = new User(fxemail.getText(), fxnom.getText(), fxprenom.getText(), fxpassword.getText(), cin, fxadresse.getText(), num_tel,fxroles.getText());
            us.ajouterUtilisateur2(p);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Utilisateur ajouté(e) avec succes !", ButtonType.OK);
            a.showAndWait();

        }
    }

    public boolean validationEmail() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");

        Matcher match = pattern.matcher(fxemail.getText());

        if (match.find() && match.group().equals(fxemail.getText())) {

            return true;

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();

            return false;

        }
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
