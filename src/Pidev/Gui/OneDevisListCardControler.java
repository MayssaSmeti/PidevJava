package Pidev.Gui;

import Pidev.Entities.Devis;
import Pidev.Entities.DevisItem;
import Pidev.Services.ServiceDevis;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
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
 * @author zaghd
 */
public class OneDevisListCardControler {

    private Text OffreName;

    @FXML
    private HBox deleteOffre;

    @FXML
    private HBox editOffre;

    @FXML
    private HBox priceHbox;

    @FXML
    private Text priceOffre;

    @FXML
    private HBox qrCodeOffre;

    @FXML
    private Text stockProduit;

    @FXML
    private Text stockProduit11;

    @FXML
    private Text validiteOffre;

    private ListView<Devis> devisListView;
    ServiceDevis SD = new ServiceDevis();
    @FXML
    private Text OffreName1;
    @FXML
    private Text ExpertName;

    public void setOffreData(Devis d) {

        ExpertName.setText(d.getNomE() + " " + d.getPrenomE());

        // get category Name
        validiteOffre.setText(d.getDate().toString());

        priceOffre.setText("" + d.getTotal_ht());

        // deleteProduit btn click
        deleteOffre.setId(String.valueOf(d.getId()));

        deleteOffre.setOnMouseClicked(event -> {
            System.out.println("ID du offre à supprimer : " + d.getId());
            try {
                SD.supprimeDevis(d.getId());

            } catch (SQLException e) {
                e.printStackTrace();
            }
            // supprimer le contenu de la liste et afficher la nouvelle liste(apres
            // supprimer)

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeDevis.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end
        });

        // END deleteDevis btn click
        // editDevis btn click
        editOffre.setId(String.valueOf(d.getId()));

        editOffre.setOnMouseClicked(event -> {
            System.out.println("ID du produit à modifier : " + d.getId());
            Devis.setIdDevis(d.getId());

            //Devis.actionTest = 1; // pour afficher le bouton update
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDevis.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de AddProduct.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        // END editProduit btn click

        //showDevis button 
        qrCodeOffre.setId(String.valueOf(d.getId()));

        qrCodeOffre.setOnMouseClicked(event -> {
            System.out.println(d.getId());

            // create a dialog to display the devis items
            Dialog<Devis> dialog = new Dialog<>();
            dialog.setTitle("Devis Items");

            // create a table to display the devis items
            TableView<DevisItem> table = new TableView<>();

            TableColumn<DevisItem, String> descriptionColumn = new TableColumn<>("description");
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            TableColumn<DevisItem, Double> qteColumn = new TableColumn<>("quantite");
            qteColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));

            TableColumn<DevisItem, Double> priceUColumn = new TableColumn<>("unitprice");
            priceUColumn.setCellValueFactory(new PropertyValueFactory<>("unitprice"));

            TableColumn<DevisItem, Double> TotalpriceColumn = new TableColumn<>("totalprice");
            TotalpriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
            ObservableList<DevisItem> list = FXCollections.observableArrayList();
            try {
                SD.getDevisItems(d.getId()).forEach(r -> {

                    //r.setId((int)r.getTotal_ht());
                    list.add(r);

                });
            } catch (SQLException ex) {
                Logger.getLogger(ListeDevisController.class.getName()).log(Level.SEVERE, null, ex);
            }
            table.getColumns().addAll(descriptionColumn, qteColumn, priceUColumn, TotalpriceColumn);
            table.setItems(list);

            // create a button for printing and attaching an event listener to it
            Button printButton = new Button("Send");
            printButton.setStyle("-fx-background-color: #ffa500; -fx-text-fill: #fff; -fx-font-size: 14pt; -fx-font-weight: bold;");

            printButton.setOnAction((ActionEvent e) -> {
                // create a PDF file
                try {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream("D:\\Esprit\\Sem2\\JavaFx\\DevisItems.pdf"));
                    document.open();

                    // Add the header and logo
                    PdfPTable headerTable = new PdfPTable(2);
                    headerTable.setWidthPercentage(100);
                    headerTable.setSpacingAfter(20);

                    // Add the header cell
                    PdfPCell headerCell = new PdfPCell(new Paragraph("Devis"));
                    headerCell.setBorder(Rectangle.NO_BORDER);
                    headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    headerTable.addCell(headerCell);

                    // Add the logo cell
                    Image logo = Image.getInstance("D:\\Esprit\\Sem2\\mobile\\tp\\assurance0\\PidevJava\\src\\Pidev\\assets\\img\\logo_png_with_shadow.png");
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
                    // Add the details section 
                    PdfPTable detailsTable = new PdfPTable(2);
                    detailsTable.setWidthPercentage(100);
                    detailsTable.setSpacingAfter(20);

                    PdfPCell cell = new PdfPCell(new Paragraph("ID:" + " " + Integer.toString(d.getId())));
                    cell.setBorder(PdfPCell.NO_BORDER);
                    detailsTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Date:" + " " + d.getDate()));
                    cell.setBorder(PdfPCell.NO_BORDER);
                    detailsTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Mécanicien:" + " " + d.getMecanicien().getNom()));
                    cell.setBorder(PdfPCell.NO_BORDER);
                    detailsTable.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Expert:" + " " + d.getNomE() + " " + d.getPrenomE()));
                    cell.setBorder(PdfPCell.NO_BORDER);
                    detailsTable.addCell(cell);

                    // add a line separator
                    line.setLineColor(BaseColor.LIGHT_GRAY);
                    document.add(line);

                    // add a table to the PDF
                    PdfPTable table2 = new PdfPTable(4);
                    table2.setWidthPercentage(100);
                    table2.setWidths(new float[]{2, 1, 1, 1});

                    // add table headers
                    cell = new PdfPCell(new Phrase("Description"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Quantite"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Prix Unitaire"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table2.addCell(cell);

                    cell = new PdfPCell(new Phrase("Prix Total"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table2.addCell(cell);

                    // add table rows
                    for (DevisItem item : list) {
                        cell = new PdfPCell(new Phrase(item.getDescription()));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table2.addCell(cell);

                        cell = new PdfPCell(new Phrase(Double.toString(item.getQte())));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table2.addCell(cell);

                        cell = new PdfPCell(new Phrase(Double.toString(item.getUnitprice())));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table2.addCell(cell);

                        cell = new PdfPCell(new Phrase(Double.toString(item.getTotalprice())));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table2.addCell(cell);
                    }

                    document.add(detailsTable);
                    document.add(table2);
                    document.close();
                    System.out.println("PDF file created successfully.");
                    System.out.println(d);

                    // create a new email message
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("wadii.sdouga@esprit.tn", "223JMT7061");
                        }
                    });
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(d.getMecanicien().getEmail()));

                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(d.getEmailE()));
                    message.setSubject("Devis PDF");
                    message.setText("Please find attached the PDF for the Devis.");

                    // attach the PDF file to the email message
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    Multipart multipart = new MimeMultipart();
                    DataSource source = new FileDataSource("D:\\Esprit\\Sem2\\JavaFx\\DevisItems.pdf");
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName("DevisItems.pdf");
                    multipart.addBodyPart(messageBodyPart);
                    // add logo image to the email body
                    MimeBodyPart logoPart = new MimeBodyPart();
                    DataSource logoSource = new FileDataSource("D:\\Esprit\\Sem2\\mobile\\tp\\assurance0\\PidevJava\\src\\Pidev\\assets\\img\\logo_png_with_shadow.png");
                    logoPart.setDataHandler(new DataHandler(logoSource));
                    logoPart.setHeader("Content-ID", "<logo>");

                    // create the HTML content for the email body
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
                            + "<h1>Bonjour " + d.getNomE() + ",</h1>"
                            + "<p>Merci d'avoir choisi nos services. Veuillez trouver ci-joint la version PDF de votre devis.</p>"
                            + "<img src='cid:logo' class='image' style='max-width: 200px; height: auto;'/>" // insert logo
                            + "<p>Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter.</p>"
                            + "<a href='https://www.youtube.com/' class='button'>Visitez Notre Site Web</a>"
                            + "<p>Cordialement,</p>"
                            + "<p>" + d.getMecanicien().getNom() + "</p>"
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
                    multipart.addBodyPart(logoPart);
                    multipart.addBodyPart(htmlPart);

                    multipart.addBodyPart(footerPart);

                    // set the message content
                    message.setContent(multipart);

                    // send the email message
                    Transport.send(message);

                    System.out.println("Email sent successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });

            // add the table to the dialog
            VBox vbox = new VBox(table, printButton);
            dialog.getDialogPane().setContent(vbox);

            // add a close button to the dialog
            ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(closeButton);

            // show the dialog
            dialog.showAndWait();

        });

    }

}
