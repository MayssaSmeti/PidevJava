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
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mayssa
 */
public class Mail {
    private String fromEmail ="4.roues.assurances@gmail.com";
    private String password="eauvsyslukyzjceq";
    private String toEmail ; 
    private String subject ; 
    private String message ; 
    
    

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
        e_message.setFrom(new InternetAddress("4.roues.assurances@gmail.com"));
        e_message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mayssa.smeti@esprit.tn"));
        e_message.setSubject(subject);
        e_message.setText("ceci est un message test ");

        // Envoi du message
        Transport.send(e_message);
        System.out.println("message envoyé ");
        }
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