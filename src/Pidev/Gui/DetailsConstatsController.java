/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import Pidev.Entities.Constat;
import Pidev.Entities.Expert;
import Pidev.Entities.User;
import Pidev.Entities.Vehicule;
import Pidev.Services.ConstatCRUD;
import Pidev.Services.UserCrud;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnMail;
    @FXML
    private ImageView tfimage;

    private String username = "ayabenkhedher84@gmail.com";
    private String password = "airiadbfltlwaizk";
    @FXML
    private ImageView tfimage1;
    @FXML
    private ComboBox<String> experts;
    private int userId = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TO Do

        String s = "C:\\Users\\USER\\Documents\\NetBeansProjects\\PiDev\\src\\assets\\Img\\" + tfphoto.getText();
        System.out.println(tfphoto.getText());
        System.out.println(s);
        File file = new File(s);
        if (file.exists()) {
            javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString()) {
            };
            tfimage.setImage(image);
        } else {
            System.out.println("Image file not found!");
        }

        // Instancier le service de categorie
        UserCrud userService = new UserCrud();

        // Récupérer toutes les categories
        List<User> users = userService.afficherExperts();

        //System.out.println("user ");
        Map<String, Integer> valuesMap = new HashMap<>();

        for (User user : users) {
            // System.out.println("user"+user);
            experts.getItems().add("" + user.getNom() + " " + user.getPrenom());
            valuesMap.put(user.getNom() + " " + user.getPrenom(),user.getId());
        }

        experts.setOnAction(event -> {
            String selectedOption = experts.getValue();
            int selectedValue = valuesMap.get(selectedOption);
            userId = selectedValue;
            System.out.println(userId);

            // System.out.println("Selected option: " + selectedOption);
            //System.out.println("Selected value: " + userId);
        });
    }

    public void setTfNom(String message) {
        this.tfnom.setText(message);
    }

    public void setTfPrenom(String message) {
        this.tfprenom.setText(message);
    }

    public void setTfType(String message) {
        this.tftype.setText(message);
    }

    public void setTfMarque(String message) {
        this.tfmarque.setText(message);
    }

    public void setTfAssurance(String message) {
        this.tfassurance.setText(message);
    }

    public void setTfAdresse(String message) {
        this.tfadresse.setText(message);
    }

    public void setTfEmplacement(String message) {
        this.tfemplacement.setText(message);
    }

    public void setTfPhoto(String message) {
        this.tfphoto.setText(message);
    }

    public void setTfDescription(String message) {
        this.tfdescription.setText(message);
    }

    public void setTfObservations(String message) {
        this.tfobservations.setText(message);
    }

    public void setTfNum(String message) {
        this.tfnum.setText(message);
    }

    public void setTfMail(String message) {
        this.tfmail.setText(message);
    }

    public void setTfDate(String message) {
        this.tfdate.setText(message);
    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        String nomclient_e = tfnom.getText();
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
        if (nomclient_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre nom!", ButtonType.OK);
            alert.showAndWait();
        }
        if (prenomclient_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre prenom!", ButtonType.OK);
            alert.showAndWait();
        }
        if (typevehicule_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez choisir le type de votre véhicule!", ButtonType.OK);
            alert.showAndWait();
        }
        if (marquevehicule_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez choisir la marque de votre véhicule!", ButtonType.OK);
            alert.showAndWait();
        }
        if (assuranceclient_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre assurance!", ButtonType.OK);
            alert.showAndWait();
        }
        if (adresseclient_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre adresse!", ButtonType.OK);
            alert.showAndWait();
        }
        if (emplacementaccid.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer l'emplacement de l'accident!", ButtonType.OK);
            alert.showAndWait();
        }
        if (photoaccid.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez insérer la photo de l'accident!", ButtonType.OK);
            alert.showAndWait();
        }
        if (descriptiondegat.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre description!", ButtonType.OK);
            alert.showAndWait();
        }
        if (observations.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre observation de degat!", ButtonType.OK);
            alert.showAndWait();
        }
        if (!numcontrat_e.matches("[0-9]+") || numcontrat_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre numéro de contrat!!", ButtonType.OK);
            alert.showAndWait();

        }
        if (mail.isEmpty() || !mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre email!", ButtonType.OK);
            alert.showAndWait();

        }
        if (date_creation.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer la date de l'accident!", ButtonType.OK);
            alert.showAndWait();
        }

        Expert expert = new Expert(1);
        Vehicule vehicule = new Vehicule(1);

        Constat c = new Constat(nomclient_e, prenomclient_e, typevehicule_e, marquevehicule_e, assuranceclient_e, adresseclient_e, emplacementaccid, photoaccid, descriptiondegat, observations, numcontrat_e, mail, date_creation, expert, vehicule);
        ConstatCRUD ccd = new ConstatCRUD();
        ccd.modifier(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Constat modifier avec succées!", ButtonType.OK);
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
        ccd.supprimerConstat(c.getId());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Constat supprimer avec succées!", ButtonType.OK);
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

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(tfnom.getText() + ".pdf"));
        document.open();
        // Add the header and logo
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        headerTable.setSpacingAfter(20);

        // Add the header cell
        BaseColor color = new BaseColor(0xff, 0x85, 0x21);
        PdfPCell headerCell = new PdfPCell(new Paragraph("Informations Client ", new Font(Font.FontFamily.HELVETICA, 36, Font.BOLD, color)));
        headerCell.setBorder(Rectangle.NO_BORDER);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(10);
        headerTable.addCell(headerCell);

        // Add the logo cell
        Image logo = Image.getInstance("C:\\Users\\msi\\Documents\\NetBeansProjects\\Integration\\src\\Pidev\\assets\\Img\\logo.png");
        logo.scaleAbsolute(100, 100); // resize the logo if needed
        PdfPCell logoCell = new PdfPCell(logo, false);
        logoCell.setBorder(Rectangle.NO_BORDER);

        logoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        headerTable.addCell(logoCell);

        document.add(headerTable);

        // add a line separator
        LineSeparator line = new LineSeparator();
        line.setLineColor(BaseColor.LIGHT_GRAY);
        document.add(line);

        // add a line separator
        line.setLineColor(BaseColor.LIGHT_GRAY);
        document.add(line);

        // Ajouter le nom et prénom
        Paragraph nom = new Paragraph();
        nom.add(new Chunk("\n Nom Client : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        nom.add(new Chunk(tfnom.getText() + " " + tfprenom.getText()));
        document.add(nom);
        document.add(new Chunk("\n"));

        Paragraph type = new Paragraph();
        type.add(new Chunk("Type Véhicule : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        type.add(new Chunk(tftype.getText()));
        document.add(type);
        document.add(new Chunk("\n"));

        Paragraph marque = new Paragraph();
        marque.add(new Chunk("Marque Véhicule : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        marque.add(new Chunk(tfmarque.getText()));
        document.add(marque);
        document.add(new Chunk("\n"));

        Paragraph assu = new Paragraph();
        assu.add(new Chunk("Assurance : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        assu.add(new Chunk(tfassurance.getText()));
        document.add(assu);
        document.add(new Chunk("\n"));

        Paragraph adresse = new Paragraph();
        adresse.add(new Chunk("Adresse du client : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        adresse.add(new Chunk(tfadresse.getText()));
        document.add(adresse);
        document.add(new Chunk("\n"));

        // Ajouter l'emplacement de l'accident 
        Paragraph emp = new Paragraph();
        emp.add(new Chunk("Emplacement Accident : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        emp.add(new Chunk(tfemplacement.getText()));
        document.add(emp);
        document.add(new Chunk("\n"));

        // Ajouter la photo de l'accident
        Paragraph image = new Paragraph();
        image.add(new Chunk("Photo Accident   :  ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        Image img = Image.getInstance("src/Pidev/assets/img/" + tfphoto.getText());
        image.add(new Chunk(img, 0, 0, true));
        document.add(image);
        document.add(new Chunk("\n"));

        Paragraph desc = new Paragraph();
        desc.add(new Chunk("Description Degat :", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        desc.add(new Chunk(tfdescription.getText()));
        document.add(desc);
        document.add(new Chunk("\n"));

        Paragraph obs = new Paragraph();
        obs.add(new Chunk("Observations :", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        obs.add(new Chunk(tfobservations.getText()));
        document.add(obs);
        document.add(new Chunk("\n"));

        // Ajouter le numéro de contrat
        Paragraph num = new Paragraph();
        num.add(new Chunk("Numéro Contrat :", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        num.add(new Chunk(tfnum.getText()));
        document.add(num);
        document.add(new Chunk("\n"));

        // Ajouter l'adresse email
        Paragraph mail = new Paragraph();
        mail.add(new Chunk("Mail : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        mail.add(new Chunk(tfmail.getText()));
        document.add(mail);
        document.add(new Chunk("\n"));

        // Ajouter la date
        Paragraph date = new Paragraph();
        date.add(new Chunk("Date Création :", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
        date.add(new Chunk(tfdate.getText()));
        document.add(date);
        document.add(new Chunk("\n"));

        document.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PDF");
        alert.setHeaderText(null);
        alert.setContentText("Les détails du constat ont été exportés dans un fichier PDF.");
        alert.showAndWait();
    }

    @FXML
    private void mail(ActionEvent event) throws AddressException, MessagingException, IOException {
        String html = "<html>"
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
                + "<h1>Bonjour " + tfnom.getText()  + ",</h1>"
                + "<p><p>Merci d'avoir choisi nos services. Veuillez trouver ci-joint la version PDF de votre constat.</p></p>"
                + "<img src='cid:logo' class='image' style='max-width: 200px; height: auto;'/>" // insert logo
                + "<p>Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter.</p>"
                + "<a href='https://www.youtube.com/' class='button'>Visitez Notre Site Web</a>"
                + "<p>Cordialement,</p>"
                + "</div>"
                + "<p style='text-align:center;font-size:12px;color:gray;'>"
                + "Cet e-mail a été envoyé par 4RouesAssurances. &copy; 2023"
                + "</p>"
                + "</div>"
                + "</body>"
                + "</html>";
      
              
        System.out.println(tfmail.getText());
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tfmail.getText()));
        message.setSubject("Constat : " + tfnom.getText() + tfprenom.getText());

        // add logo image to the email body
        MimeBodyPart logoPart = new MimeBodyPart();
        DataSource logoSource = new FileDataSource("C:\\Users\\msi\\Documents\\NetBeansProjects\\Integration\\src\\Pidev\\assets\\Img\\logo.png");
        logoPart.setDataHandler(new DataHandler(logoSource));
        logoPart.setHeader("Content-ID", "<logo>");

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html, "text/html");
//         MimeBodyPart titre = new MimeBodyPart();
//         titre.setText("Bonjour, MR/MRS "+ tfnom.getText() +" ,Ceci est votre constat !");

        // Create the message part for the PDF file
        MimeBodyPart pdfPart = new MimeBodyPart();
        File pdfFile = new File("C://Users//msi//Documents//NetBeansProjects//Integration//" + tfnom.getText() + ".pdf");
        pdfPart.attachFile(pdfFile);

        MimeBodyPart footerPart = new MimeBodyPart();
        footerPart.setText("\n"
                + "Cet e-mail et ses pièces jointes sont confidentiels et destinés exclusivement à la personne ou à l'entité à qui ils sont adressés. "
                + "Si vous avez reçu cet e-mail par erreur, veuillez en informer immédiatement l'expéditeur et supprimer l'e-mail de votre système. "
                + "La divulgation ou l'utilisation non autorisée des informations contenues dans cet e-mail est strictement interdite. "
                + "(C) 2023 Eya Ben Khedher. Tous droits réservés.");

        // Create the message content as a Multipart container
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(logoPart);
        multipart.addBodyPart(htmlPart);
//         multipart.addBodyPart(titre);
        multipart.addBodyPart(pdfPart);
        multipart.addBodyPart(footerPart);
        message.setContent(multipart);
        message.setSentDate(new Date());
        Transport t;
        t = session.getTransport("smtp");
        Transport.send(message);
        System.out.println("message envoyé");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
}
