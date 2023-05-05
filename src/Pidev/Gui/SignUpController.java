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
import java.util.Properties;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
    private String fromEmail = "4.roues.assurances@gmail.com";
    private String password = "eauvsyslukyzjceq";
    @FXML
    private AnchorPane fxFront;
    @FXML
    private Button btncnx;

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
    private void inscrit(ActionEvent event) throws IOException, MessagingException {
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("4 Roues Assurrances :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("EMAIL NON VALIDE  !!");
            alert.showAndWait();

        } else {

            String Email = fxemail.getText();
            String nom = fxnom.getText();
            String prenom = fxprenom.getText();
            String adresse = fxadresse.getText();

            UserCrud us = new UserCrud();
            Integer cin = Integer.parseInt(fxcin.getText()); //conversion
            Integer num_tel = Integer.parseInt(fxnum.getText());
            User p = new User(Email, nom, prenom, fxpassword.getText(), cin, adresse, num_tel, "user", activation_token, "inactif");
            us.ajouterUtilisateur2(p);
//            String message = "Bonjour " + nom + " " + prenom + "\n"
//                    + "Merci de vous être inscrit! Votre code de confirmation est : " + p.getActivation_token();
//
//            Mail emailsend = new Mail("4.roues.assurances@gmail.com", "eauvsyslukyzjceq", Email, "Confirmation d'inscription", message);

            try {
// Configuration des propriétés SMTP session mail 
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.ssl.trust", "*");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                // Configuration de l'authentification
                Session session = Session.getInstance(props,
                        new Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });
                String toEmail = fxemail.getText(); 
             
                    // Création du message
                    Message e_message = new MimeMessage(session);
                    e_message.setFrom(new InternetAddress(fromEmail));
                    e_message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                    e_message.setSubject("Confirmation d'inscription");
                    MimeMultipart multipart = new MimeMultipart("related");
                    String htmlBody = "<html>"
                            + "<head>"
                            + "<style>"
                            + "/* Google Fonts */"
                            + "@import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap');"
                            + "/* Global Styles */"
                            + "body {"
                            + "  font-family: 'Montserrat', sans-serif;"
                            + "  font-size: 16px;"
                            + "  line-height: 1.6;"
                            + "  color: #333;"
                            + "  background-color: #f6f6f6;"
                            + "}"
                            + "/* Container Styles */"
                            + ".container {"
                            + "  max-width: 600px;"
                            + "  margin: 0 auto;"
                            + "  padding: 20px;"
                            + "}"
                            + "/* Card Styles */"
                            + ".card {"
                            + "  background-color: #000;"
                            + "  border-radius: 10px;"
                            + "  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);"
                            + "  margin: 20px auto;"
                            + "  padding: 30px;"
                            + "  color: #fff;"
                            + "}"
                            + "/* Button Styles */"
                            + ".button {"
                            + "  display: inline-block;"
                            + "  font-size: 18px;"
                            + "  font-weight: 600;"
                            + "  text-align: center;"
                            + "  text-decoration: none;"
                            + "  color: #f6f6f6;"
                            + "  background-color: #ff8c00;"
                            + "  border-radius: 30px;"
                            + "  padding: 12px 30px;"
                            + "  margin-top: 30px;"
                            + "}"
                            + "/* Image Styles */"
                            + ".image {"
                            + "  display: block;"
                            + "  margin: 0 auto;"
                            + "  max-width: 100%;"
                            + "}"
                            + "</style>"
                            + "</head>"
                            + "<body>"
                            + "<div class='container'>"
                            + "<div class='card'>"
                            + "<h1>Bonjour " + nom+prenom +",</h1>"
                            + "<p>Merci d'avoir choisi nos services. Veuillez trouver ci-joint le code Confirmation d'inscription " + p.getActivation_token()+ ".</p>"
                            + "<p>Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter.</p>"
                            + "<a href='https://www.youtube.com/' class='button'>Visitez Notre Site Web</a>"
                            + "<p>Cordialement,</p>"
                            + "<p>" + "</p>"
                            + "</div>"
                            + "<p style='text-align:center;font-size:12px;color:gray;'>"
                            + "Cet e-mail a été envoyé par 4RouesAssurances. &copy; 2023"
                            + "</p>"
                            + "</div>"
                            + "</body>"
                            + "</html>";

                    // ajouter le contenu HTML et le logo au message électronique
                    MimeBodyPart htmlPart = new MimeBodyPart();
                    htmlPart.setContent(htmlBody, "text/html; charset=utf-8");

                    // créer une partie de message pour le pied de page
                    MimeBodyPart footerPart = new MimeBodyPart();
                    footerPart.setText("\n"
                            + "Cet e-mail et ses pièces jointes sont confidentiels et destinés exclusivement à la personne ou à l'entité à qui ils sont adressés. "
                            + "Si vous avez reçu cet e-mail par erreur, veuillez en informer immédiatement l'expéditeur et supprimer l'e-mail de votre système. "
                            + "La divulgation ou l'utilisation non autorisée des informations contenues dans cet e-mail est strictement interdite. "
                            + "(C) 2023 Mecanicien. Tous droits réservés.");

                    // create a multipart object and add the parts
                    multipart.addBodyPart(htmlPart);

                    multipart.addBodyPart(footerPart);

                    // set the message content
                    e_message.setContent(multipart);

                    // send the email message
                    Transport.send(e_message);

                    System.out.println("Email sent successfully.");}
            catch (MessagingException e){
                throw new RuntimeException("Failed to send email: " + e.getMessage()); 
            }
             // emailsend.sendEmail();
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
            
            System.out.println("ajout avec succee");
            if (saisieValide = true) {
                try {
                    Parent fxml = FXMLLoader.load(getClass().getResource("Front.fxml"));
                    home.setScene(scene);
                    home.show();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
               
//        }catch (MessagingException ex) {
//                Alert alerte = new Alert(AlertType.ERROR);
//                alerte.setTitle("Erreur lors de l'envoi de l'email");
//                alerte.setHeaderText("Erreur lors de l'envoi de l'email de confirmation");
//                alerte.setContentText("Veuillez réessayer plus tard.");
//                alerte.showAndWait();
//                System.out.println(ex.getMessage());
//            }


    }
    }

  


    @FXML
    private void connexion(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/SignIn.fxml"));
        fxFront.getChildren().removeAll();
        fxFront.getChildren().setAll(fxml);
}
}