/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import API.Mail;
import Pidev.Services.UserCrud;
import Pidev.Utilis.SessionManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
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
public class ForgotPasswordController implements Initializable {

    @FXML
    private AnchorPane btnCodeSend;
    @FXML
    TextField fxemail;
    @FXML
    private FontAwesomeIconView Fxback;
    @FXML
    private Button btnsend;

    public String getEmail() {
        String email = fxemail.getText();
        return email;

    }
    UserCrud fs = new UserCrud();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backfont(MouseEvent event) throws IOException {
        // récupère la scène actuelle à partir de l'événement
        Scene currentScene = ((Node) event.getSource()).getScene();
        // récupère la fenêtre actuelle à partir de la scène
        Stage currentStage = (Stage) currentScene.getWindow();
        // charge la nouvelle vue depuis un fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent newViewParent = loader.load();
        // crée une nouvelle scène à partir de la vue chargée
        Scene newScene = new Scene(newViewParent);
        // récupère la nouvelle fenêtre à partir de la scène
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        // affiche la nouvelle fenêtre
        newStage.show();
        // ferme l'ancienne fenêtre
        currentStage.close();
    }

    private boolean isValidEmail(String email) {

        return email.matches(".+@.+\\..+");
    }

    private int generateVerificationCode() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

     private String fromEmail = "4.roues.assurances@gmail.com";
    private String password = "eauvsyslukyzjceq";
    private boolean sendVerificationCodeByEmail(String email, int code) {
        // TODO: Implement email sending logic to send code to user's email
        
        try {
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
                    e_message.setSubject(" Code de récuperation de Mot Passe ");
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
                            + "<h1>Bonjour " +fxemail.getText()+ ",</h1>"
                            + "<p>Merci d'avoir choisi nos services. Veuillez trouver ci-joint le code de rénisialisation  " + code+ ".</p>"
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Code Enovoyé ");
        alert.setHeaderText("Un Code de vérification est envoyé vers votre email.");
        alert.showAndWait();
        return true;

    }

    @FXML
    private void SendCodeVerification(ActionEvent event) throws IOException {
        String email = fxemail.getText();

        if (!isValidEmail(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Email Invalid");
            alert.setHeaderText("Entrez votre email s'il vous plait .");
            alert.showAndWait();
            return; 
        }
    
        if (fs.SearchByMail(email)!= null) {
            SessionManager.setEmail(email);

            int code = generateVerificationCode();
            boolean emailSent = sendVerificationCodeByEmail(email, code);
            if (emailSent) {
                // Update database to store the verification code with the user's account
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Code Envoyé ");
                alert.setHeaderText("Un Code de vérification est envoyé vers votre email.");
                alert.showAndWait();
                Stage home = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangerMotdePasse.fxml"));
                Parent root = loader.load();
                ChangerMotdePasseController cc = loader.getController();
                cc.setVerificationCode(code);
                Scene scene = new Scene(root);

                home.setScene(scene);
                home.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Un erreur s'est produit.");
                alert.showAndWait();
            }

        }
    }
}