/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import API.Mail;

import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import Pidev.Utilis.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;

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
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private PasswordField confirmerPassword;

    String activationCode;
    @FXML
    private Button btninscrit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public boolean verifierEmailNonDuplique(String email) {
        String requete = "SELECT * FROM user WHERE email = ?";
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

    private boolean isValidEmail(String email) {
        // TODO: Ajouter une validation d'email plus avancée
        return email.matches(".+@.+\\..+");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public String hashMotDePasse(String motDePasse) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(motDePasse.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void inscrit(ActionEvent event) throws IOException {

        boolean saisieValide = true;
        String messageErreur = "";
        String activation_token = null;

        String email = fxemail.getText();
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

        } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") || verifierEmailNonDuplique(email) == false) {
            saisieValide = false;
            messageErreur += "L'email n'est pas valide.\n";
        } else {

            String Email = fxemail.getText();
            String nom = fxnom.getText();
            String prenom = fxprenom.getText();
            String adresse = fxadresse.getText();

            UserCrud us = new UserCrud();
            Integer cin = Integer.parseInt(fxcin.getText()); //conversion
            Integer num_tel = Integer.parseInt(fxnum.getText());
            User p = new User(Email, nom, prenom, fxpassword.getText(), cin, adresse, num_tel, activation_token);
            us.ajouterUtilisateur2(p);
            String message = "Bonjour " + nom + " " + prenom + "\n"
                    + "Merci de vous être inscrit! Votre code de confirmation est : " + p.getActivation_token();

            Mail emailsend = new Mail("4.roues.assurances@gmail.com", "eauvsyslukyzjceq", Email, "Confirmation d'inscription", message);

            try {
                emailsend.sendEmail();
                Alert alerte = new Alert(AlertType.INFORMATION);
                alerte.setTitle("Confirmation d'inscription");
                alerte.setHeaderText("Inscription réussie!");
                alerte.setContentText("Un email de confirmation a été envoyé à l'adresse " + Email + ".");
                alerte.showAndWait();
                Stage home = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VerifEmail.fxml"));
                Parent root = loader.load();
                VerifEmailController cc = loader.getController();
                cc.setData(email);
                Scene scene = new Scene(root);

                home.setScene(scene);
                home.show();
            } catch (MessagingException ex) {
                Alert alerte = new Alert(AlertType.ERROR);
                alerte.setTitle("Erreur lors de l'envoi de l'email");
                alerte.setHeaderText("Erreur lors de l'envoi de l'email de confirmation");
                alerte.setContentText("Veuillez réessayer plus tard.");
                alerte.showAndWait();
                System.out.println(ex.getMessage());
            }
            //System.out.println("ajout avec succee");
            //Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("4 Roues Assurrances :: BIENVENNUE");
            //alert.setHeaderText(null);
            //alert.setContentText("Vous Etes Inscrit !!");
            //alert.showAndWait();

            //Stage home = new Stage();

            //try {
              //  Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
                //Scene scene = new Scene(fxml);
                //home.setScene(scene);
                //home.show();
                //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.close();
           // } catch (IOException ex) {
             //   System.out.println(ex.getMessage());
            //}

        }

    }

}
