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
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField fxnom;
    @FXML
    private TextField fxprenom;
    @FXML
    private TextField fxemail;
    @FXML
    private PasswordField fxpassword;
    @FXML
    private TextField fxcin;
    @FXML
    private TextField fxnum;
    @FXML
    private TextField fxadresse;
    @FXML
    private Button btninscrit;
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private PasswordField confirmerPassword;

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
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void Inscription(ActionEvent event) throws SQLException {

        cnx = MyConnection.getInstance().getCnx();
        String query = "INSERT INTO user (email,password,nom,prenom,adresse,cin,num_tel)"
                + "VALUES (?,?,?,?,?,?,?)";

        try {

            if (fxemail.getText().isEmpty()
                    | fxnum.getText().isEmpty()
                    | fxadresse.getText().isEmpty()
                    | fxpassword.getText().isEmpty()
                    | fxcin.getText().isEmpty()
                    | fxnom.getText().isEmpty()
                    | confirmerPassword.getText().isEmpty()
                    | fxprenom.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("4 Roues Assurrances  :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tous les champs  !!");
                alert.showAndWait();

            } else if (confirmerPassword.getText().length() < 8 | confirmerPassword.getText() == fxpassword.getText()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("4 Roues Assurrances :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password doit etre sup 8 caractéres !!");
                alert.showAndWait();

            } else if (fxcin.getText().length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("4 Roues Assurrances :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("CIN doit etre de 8 chiffres  !!");
                alert.showAndWait();

            } else if (fxnum.getText().length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("4 Roues Assurrances :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le numero de telephone  doit etre de 8 chiffres  !!");
                alert.showAndWait();

            } else {
                String email = fxemail.getText();
                if (ValidationEmail() || verifierEmailNonDuplique(email) == false) {
                    PreparedStatement smt = cnx.prepareStatement(query);

                    smt.setString(1, fxemail.getText());
                    smt.setString(2, fxpassword.getText());
                    smt.setString(3, fxnom.getText());
                    smt.setString(4, fxprenom.getText());
                    smt.setString(5, fxadresse.getText());
                    smt.setString(6, fxcin.getText());
                    smt.setString(7, fxnum.getText());
                    smt.executeUpdate();
                    
                    UserCrud us = new UserCrud();
                    Integer cin = Integer.parseInt(fxcin.getText()); //conversion
                    Integer num_tel = Integer.parseInt(fxnum.getText());
                    User p = new User(fxemail.getText(), fxnom.getText(), fxprenom.getText(), fxpassword.getText(), cin, fxadresse.getText(), num_tel);
                    us.ajouterUtilisateur2(p);
                    System.out.println("ajout avec succee");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("4 Roues Assurrances :: BIENVENNUE");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous Etes Inscrit !!");
                    alert.showAndWait();

                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
