/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import Pidev.Utilis.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean verifierEmailNonDuplique(String email) {
        String requete = "SELECT * FROM utilisateur WHERE email = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        Connection Ds = MyConnection.getInstance().getCnx();
        boolean emailExiste = false;

        try {
            statement = Ds.prepareStatement(requete);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                emailExiste = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return !emailExiste;
    }

    public boolean ValidationEmail() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9._]+([.][a-zA-Z0-9]+)+");
        Matcher match = pattern.matcher(fxemail.getText());

        if (match.find() && match.group().equals(fxemail.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();

            return false;
        }
    }

    private boolean isValidEmail(String email) {
        // TODO: Ajouter une validation d'email plus avancée
        return email.matches(".+@.+\\..+");
    }

    public void setText(User user) //modifier les donnes eli hatynehom 
    {

        // String id =String.valueOf(user.getId()); //transformation : convertion de la valeur  du id vers un string 
        // fxcin.setText(id);
        String cin = String.valueOf(user.getCin());
        fxcin.setText(cin);
        fxnom.setText(user.getNom());
        fxprenom.setText(user.getPrenom());

        fxadresse.setText(user.getAdresse());
        String num = String.valueOf(user.getNum_tel());
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Modifier(ActionEvent event) {

//        if (fxcin.getText().length() < 8) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("4 Roues Assurrances :: Error Message");
//        alert.setHeaderText(null);
//        alert.setContentText("CIN doit etre de 8 chiffres  !!");
//        alert.showAndWait();
//        } else if (fxnum.getText().length() < 8) 
//        {
//         Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("4 Roues Assurrances :: Error Message");
//        alert.setHeaderText(null);
//        alert.setContentText("Le numero de télephone  doit etre de 8 chiffres  !!");
//        alert.showAndWait();
//        } else {
        String email = fxemail.getText();
       
            UserCrud rec = new UserCrud();
           
            // Integer id=Integer.parseInt(fxid.getText());
            Integer cin = Integer.parseInt(fxcin.getText()); //conversion 
            Integer num_tel = Integer.parseInt(fxnum.getText());
            String nom = fxnom.getText();
            String prenom = fxprenom.getText();
            String roles = fxrole.getText();
            String adresse = fxadresse.getText();

            User R = new User();
            R = new User(R.getId(), email, nom, prenom, cin, adresse, num_tel, roles);
            rec.modifierUtilisateur(R);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("4 roues assurances :: Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Utilsateur modifié");
            alert.showAndWait();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
    
//}
