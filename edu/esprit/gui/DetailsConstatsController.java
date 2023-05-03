/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

<<<<<<< HEAD
=======
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
>>>>>>> 0326fe5 (api)
import edu.esprit.entities.Constat;
import edu.esprit.entities.Expert;
import edu.esprit.entities.Vehicule;
import edu.esprit.services.ConstatCRUD;
<<<<<<< HEAD
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
=======
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
>>>>>>> 0326fe5 (api)
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
<<<<<<< HEAD
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
=======
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
>>>>>>> 0326fe5 (api)

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailsConstatsController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfmarque;
    @FXML
    private TextField tfassurance;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfemplacement;
    @FXML
    private TextField tfphoto;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfobservations;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfdate;
<<<<<<< HEAD
=======
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnMail;
    
    private String username = "ayabenkhedher84@gmail.com";
    private String password = "airiadbfltlwaizk";

>>>>>>> 0326fe5 (api)

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setTfNom(String message){
        this.tfnom.setText(message);
    }
      public void setTfPrenom(String message){
        this.tfprenom.setText(message);
    }
       public void setTfType(String message){
        this.tftype.setText(message);
    }
        public void setTfMarque(String message){
        this.tfmarque.setText(message);
    }
         public void setTfAssurance(String message){
        this.tfassurance.setText(message);
    }
          public void setTfAdresse(String message){
        this.tfadresse.setText(message);
    }
           public void setTfEmplacement(String message){
        this.tfemplacement.setText(message);
    }
            public void setTfPhoto(String message){
<<<<<<< HEAD
        this.tfphoto.setText(message);
    }
=======
                 this.tfphoto.setText(message);   
           }
>>>>>>> 0326fe5 (api)
             public void setTfDescription(String message){
        this.tfdescription.setText(message);
    }
              public void setTfObservations(String message){
        this.tfobservations.setText(message);
    } 
              public void setTfNum(String message){
        this.tfnum.setText(message);
    }
             public void setTfMail(String message){
        this.tfmail.setText(message);
    }
           public void setTfDate(String message){
        this.tfdate.setText(message);
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
<<<<<<< HEAD
                       String nomclient_e = tfnom.getText();
=======
            String nomclient_e = tfnom.getText();
>>>>>>> 0326fe5 (api)
            String prenomclient_e = tfprenom.getText();
            String typevehicule_e = tftype.getText();
            String marquevehicule_e = tfmarque.getText();
            String assuranceclient_e = tfassurance.getText();
            String adresseclient_e = tfadresse.getText();
            String emplacementaccid = tfemplacement.getText();
            String photoaccid = tfphoto.getText();
            String descriptiondegat = tfdescription.getText();
            String observations = tfobservations.getText();
            String numcontrat_e = tfnum.getText();
            String mail = tfmail.getText();
            String date_creation = tfdate.getText();

            Expert expert = new Expert(1);
            Vehicule vehicule = new Vehicule(1);
 
          
            Constat c = new Constat(nomclient_e,prenomclient_e,typevehicule_e,marquevehicule_e,assuranceclient_e,adresseclient_e,emplacementaccid,photoaccid,descriptiondegat,observations,numcontrat_e,mail,date_creation,expert,vehicule);
            ConstatCRUD ccd = new ConstatCRUD();
            ccd.modifier(c);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Constat modifier avec succées!",ButtonType.OK);
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsConstats.fxml"));
        
        try {    
            Parent root = loader.load();
            DetailsConstatsController dcc = loader.getController();
            dcc.setTfNom(c.getNomclient_e());
            dcc.setTfPrenom(c.getPrenomclient_e());
            dcc.setTfType(c.getTypevehicule_e());
            dcc.setTfMarque(c.getMarquevehicule_e());
            dcc.setTfAssurance(c.getAssuranceclient_e());
            dcc.setTfAdresse(c.getAdresseclient_e());
            dcc.setTfEmplacement(c.getEmplacementaccid());
            dcc.setTfPhoto(c.getPhotoaccid());
            dcc.setTfDescription(c.getDescriptiondegat());
            dcc.setTfObservations(c.getObservations());
            dcc.setTfNum(c.getNumcontrat_e());
            dcc.setTfMail(c.getMail());
            dcc.setTfDate(c.getDate_creation());
            
            tfnom.getScene().setRoot(root);
            
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        Constat c = new Constat();
            ConstatCRUD ccd = new ConstatCRUD();
            ccd.supprimer(c.getId());
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Constat supprimer avec succées!",ButtonType.OK);
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsConstats.fxml"));
        
        try {    
            Parent root = loader.load();
            DetailsConstatsController dcc = loader.getController();
            dcc.setTfNom(null);
            dcc.setTfPrenom(null);
            dcc.setTfType(null);
            dcc.setTfMarque(null);
            dcc.setTfAssurance(null);
            dcc.setTfAdresse(null);
            dcc.setTfEmplacement(null);
            dcc.setTfPhoto(null);
            dcc.setTfDescription(null);
            dcc.setTfObservations(null);
            dcc.setTfNum(null);
            dcc.setTfMail(null);
            dcc.setTfDate(null);
            
            tfnom.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
    }
<<<<<<< HEAD
=======

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream(tfnom.getText()+".pdf"));

        document.open();
        String mail = tfmail.getText();
        if (mail.isEmpty()) {
             mail = "Aucun e-mail spécifié";
        }
String html = "<h1 align='center'><font color='#0F056B'>Informations Client</font></h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<table border='1' cellpadding='5'>" +
              "<tr bgcolor='#74D0F1'><td><b>Nom Client :</b></td><td>" + tfnom.getText() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Prénom Client :</b></td><td>" + tfprenom.getText() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Type Véhicule :</b></td><td>" + tftype.getText() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Marque Véhicule :</b></td><td>" + tfmarque.getText() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Assurance :</b></td><td>" + tfassurance.getText() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Adresse :</b></td><td>" + tfadresse.getText() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Emplacement Accident :</b></td><td>" + tfemplacement.getText() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Photo Accident :</b></td><td>" + tfphoto.getText() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Description Degat :</b></td><td>" + tfdescription.getText() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Observations :</b></td><td>" + tfobservations.getText() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Numéro Contrat :</b></td><td>" + tfnum.getText() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Mail :</b></td><td>" + mail + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Date Création :</b></td><td>" + tfdate.getText() + "</td></tr>" +
              "</table>";
               HTMLWorker htmlWorker = new HTMLWorker(document); 
               htmlWorker.parse(new StringReader(html));
        document.close();
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PDF");
        alert.setHeaderText(null);
        alert.setContentText("Constat Détails Exported in PDF");
        alert.showAndWait();
        }     

    @FXML
    private void mail(ActionEvent event) throws AddressException, MessagingException, IOException {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title>Ma page HTML colorée et décorée</title>\n" +
                "\t<style>\n" +
                "\t\tbody {\n" +
                "\t\t\tbackground-color: lightblue;\n" +
                "\t\t\tfont-family: Arial, sans-serif;\n" +
                "\t\t\tfont-size: 16px;\n" +
                "\t\t\tcolor: white;\n" +
                "\t\t\tmargin: 0;\n" +
                "\t\t\tpadding: 0;\n" +
                "\t\t}\n" +
                "\t\th1 {\n" +
                "\t\t\tfont-size: 36px;\n" +
                "\t\t\tcolor: darkblue;\n" +
                "\t\t\tmargin: 20px;\n" +
                "\t\t\tpadding: 10px;\n" +
                "\t\t\tborder: 3px solid darkblue;\n" +
                "\t\t\tbackground-color: white;\n" +
                "\t\t\ttext-align: center;\n" +
                "\t\t}\n" +
                "\t\tp {\n" +
                "\t\t\tmargin: 20px;\n" +
                "\t\t\tpadding: 10px;\n" +
                "\t\t\tborder: 3px solid darkblue;\n" +
                "\t\t\tbackground-color: white;\n" +
                "\t\t\tcolor: black;\n" +

                "\t\t}\n" +
                "\t</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<h1>4 Roues Assurances</h1>\n" +
                "<h2> Nous vous souhaitons un agréable séjour parmi nous !</h2>" +
                "</body>\n" +
                "</html>\n";
         System.out.println(tfmail.getText());
         Properties props = new Properties();
         props.put("mail.smtp.auth","true");
         props.put("mail.smtp.starttls.enable","true");
         props.put("mail.smtp.host","smtp.gmail.com");
         props.put("mail.smtp.port", "587");            
         Session session = Session.getInstance(props,new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication (username,password);
        }
    });
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(tfmail.getText()));
         message.setSubject("Constat : "+ tfnom.getText() + tfprenom.getText());
         MimeBodyPart htmlPart = new MimeBodyPart();
         htmlPart.setContent(html, "text/html");
         MimeBodyPart titre = new MimeBodyPart();
         titre.setText("Bonjour, MR/MRS "+ tfnom.getText() +" ,Ceci est votre constat !");

         // Create the message part for the PDF file
         MimeBodyPart pdfPart = new MimeBodyPart();
         File pdfFile = new File("C://Users//USER//Documents//NetBeansProjects//PiDev/"+tfnom.getText()+".pdf");
         pdfPart.attachFile(pdfFile);
    
         
         // Create the message content as a Multipart container
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(htmlPart);
         multipart.addBodyPart(titre);
         multipart.addBodyPart(pdfPart);
         message.setContent(multipart);
         message.setSentDate(new Date());
         Transport t;
         t = session.getTransport("smtp");          
         Transport.send(message);
         System.out.println("message envoyé");
         Alert alert= new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("PDF");
         alert.setHeaderText(null);
         alert.setContentText("Constat envoyé avec succées !");
         alert.showAndWait();
}

    @FXML
    private void Localiser(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));  
            Parent root = loader.load();
            
            tfnom.getScene().setRoot(root);
    }

    
    
    
  
>>>>>>> 0326fe5 (api)
    
}
