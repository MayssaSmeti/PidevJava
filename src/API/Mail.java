/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.sql.Date;
import java.util.Properties;
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
 *
 * @author Mayssa
 */
public class Mail {
  private  String fromEmail;
    private  String password ;
    private String toEmail ; 
    private String subject ; 
    private String message ; 

    public Mail(String fromEmail, String password, String toEmail, String subject, String message) {
        this.fromEmail = fromEmail;
        this.password = password;
        this.toEmail = toEmail;
        this.subject = subject;
        this.message = message;
    }

    public Mail() {
    }
    
    

    public void sendEmail() throws MessagingException {
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
        try{
        // Création du message
        Message e_message = new MimeMessage(session);
        e_message.setFrom(new InternetAddress(fromEmail));
        e_message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        e_message.setSubject(subject);
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
                        + "<h1>Bonjour " + ",</h1>"
                        + "<p>Merci d'avoir choisi nos services. Veuillez trouver ci-joint le code Confirmation d'inscription ." + "</p>"
                        
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

    }
     public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
      public void setSubject(String subject) {
        this.subject = subject;
    }
       public void setMessage(String message) {
        this.message = message;
    }
   
    
}