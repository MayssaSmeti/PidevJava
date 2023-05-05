
package Pidev.Gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Pidev.Entities.Offre;
//import Pidev.Entities.Panier;
import Pidev.Entities.customersData;
import Pidev.Entities.data;
import Pidev.Services.IOffreService;
import Pidev.Services.OffreService;
import Pidev.Tests.Main;
import Pidev.Utilis.MyConnection;
import Pidev.Utilis.SessionManager;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class FrontControler implements Initializable {

    @FXML
    private AnchorPane content_areaFront;

    @FXML
    private TextField menu_amount;

    @FXML
    private Label menu_change;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private Button menu_payer;

    @FXML
    private TableColumn<Offre, String> menu_prix;

    @FXML
    private Button menu_recu;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private Button menu_supprimer;

    @FXML
    private TableView<Offre> menu_tableView;
    
    @FXML
    private AnchorPane payement;

    @FXML
    private TableColumn<Offre, String> menu_titre;

    @FXML
    private Label menu_total;

    @FXML
    private TableColumn<Offre, String> menu_validite;


      @FXML
    private AnchorPane panier;
      
      @FXML
    private Text payement_total;
      
    public ObservableList<Offre> cardListData;
    @FXML
    private AnchorPane payement1;
    @FXML
    private AnchorPane payement11;
    @FXML
    private Button logout;
    
    public static double totalG = 0  ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         customerID();
       // Main myApp = new Main();
        //Panier panier = myApp.getPanier();

        Label labelVide = new Label("Votre panier est vide");


        menu_tableView.setPlaceholder(labelVide);
        
        menuDisplayTotal();
        
        menuShowOrderData();
        

        //menuDisplayCard();
      
   // }

    //public ObservableList<Offre> menuGetData(){

    //     String sql = "SELECT * FROM offre";

    //     ObservableList<Offre> listData =FXCollections.observableArrayList();

    //     Connection cnx = MyConnection.getInstance().getCnx();
    //     try{
    //         PreparedStatement ps = cnx.prepareStatement(sql);
    //         ResultSet result = ps.executeQuery();

    //         Offre o ;
    //         while (result.next()){
    //             o = new Offre(result.getInt("id"), result.getString("description"),
    //             result.getInt("prix"),result.getString("titre"),
    //              result.getString("validite_offre"), result.getString("image_offre"));
    //             listData.add(o);
    //             }
    //     }catch (Exception e) {e.printStackTrace();}
        
    //     return listData;
    // }
       
    // private void menuDisplayCard() {
    //     cardListData.clear();
    //     cardListData.setAll(menuGetData());

    //     int row = 0;
    //     int column = 0;

    //     menu_gridPane.getRowConstraints().clear();
    //     menu_gridPane.getColumnConstraints().clear();
    //     for (int q = 0; q < cardListData.size(); q++){
    //         try {
    //             FXMLLoader load = new FXMLLoader();
    //             load.setLocation(getClass().getResource("/gui/OneOffreListCard.fxml"));
    //             AnchorPane pane  = load.load();
    //             OneOffreListCardFrontControler card = load.getController();
    //             card.setOffreData(cardListData.get(q));

    //             if (column == 3) {
    //                 column = 0;
    //                 row += 1;
    //             }

    //             menu_gridPane.add(pane, column++, row);
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }

    //     }
    }
    private int cID;
    Connection cnx = MyConnection.getInstance().getCnx();
    private PreparedStatement pst;
    private ResultSet result;
    public void customerID() {
        String sql = "SELECT MAX(customer_id) FROM customer";
        
        
        try {
            pst = cnx.prepareStatement(sql);
          result = pst.executeQuery();
            
            if (result.next()) {
                cID = result.getInt("MAX(customer_id)");
            }
            
            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            pst = cnx.prepareStatement(checkCID);
          result = pst.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(customer_id)");
            }
            
            if (cID == 0) {
                cID += 1;
            } else if (cID == checkID) {
                cID += 1;
            }
            
            data.cID = cID;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void open_addOffreCard(MouseEvent event) throws IOException {
        

    }

    

    void open_listeContrat(MouseEvent event) throws IOException {
       
    }

    void open_addContratCard(MouseEvent event) throws IOException {
       

    }
    @FXML 
    void open_listeOffre (MouseEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("ListeOffreFront.fxml"));
        menu_gridPane.getChildren().removeAll();
        menu_gridPane.getChildren().setAll(fxml);
        panier.setVisible(true  );
    }
     private double totalP;
    
    public void menuGetTotal() {
        customerID();
        String total = "SELECT SUM(prix) FROM customer WHERE customer_id = " + cID;
        
        
        
        try {
            
           pst = cnx.prepareStatement(total);
          result = pst.executeQuery();
            
            if (result.next()) {
                totalP = result.getInt("SUM(prix)");
                System.out.println(totalP);
                
            }
            totalG = totalP ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void menuDisplayTotal() {
        menuGetTotal();
        menu_total.setText("" + totalP);
        payement_total.setText("" + totalP);
    }
    
   private ObservableList<Offre> menuOrderListData;
    
    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();
        
        menu_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        menu_validite.setCellValueFactory(new PropertyValueFactory<>("validite_offre"));
        menu_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       
        menu_tableView.setItems(menuOrderListData);
    }
    
    ///////////////////////////////////////////////////////////
    public ObservableList<Offre> menuGetOrder() {
        customerID();
        ObservableList<Offre> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM customer WHERE customer_id = " + cID;
        
        
        
        try {
             pst = cnx.prepareStatement(sql);
          result = pst.executeQuery();
            
            Offre prod;
            
            while (result.next()) {
                prod = new Offre(result.getInt("id"),
                        result.getInt("prix"),
                        result.getString("titre"),
                        result.getString("validite_offre"));
                listData.add(prod);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listData;
    }
    
     @FXML
    void menu_payer(ActionEvent event) {
        if (totalP == 0 ) { //|| menu_amount.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Vous devez faire votre ordre d'abord");
            alert.showAndWait();
        } else {
        
       System.out.println(totalG);
        int somme = (int) totalG; 
        Stripe.apiKey = "sk_test_51MiKtNI5bjLZeRXm26hwFmdRtPxBgJuJXKpwmJvkW6IHpQe9X96ycjMe3FqhtIwm57Y6W258Hb6anhOHsIt2CtS000nAtIw1WT";

// Create a charge
Map<String, Object> chargeParams = new HashMap<String, Object>();
chargeParams.put("amount", somme );
chargeParams.put("currency", "usd");
chargeParams.put("source", "tok_visa");

        try {
            Charge charge = Charge.create(chargeParams);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payement");
        alert.setHeaderText("Payement est faite avec succées");
        alert.setContentText("success");
        alert.showAndWait();
        } catch (StripeException ex) {
            System.out.println(ex);        }
    }
    }
     private int getid;
     String gettitre;
     Alert alert;
     
public void menuSelectOrder() {
    Offre prod = menu_tableView.getSelectionModel().getSelectedItem();
    int num = menu_tableView.getSelectionModel().getSelectedIndex();

    if (num < 0) {
        getid = -1;
        gettitre = "";
        return;
    }

    getid = prod.getId();
    gettitre = prod.getTitre();
}

      
    
    @FXML
public void menuRemoveBtn() {
    //System.out.println(gettitre);
    menuSelectOrder();
    if (gettitre == null || gettitre.isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the order you want to remove");
        alert.showAndWait();
    } else {
        String deleteData = "DELETE FROM customer WHERE id = " + getid;

        try {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this order?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                pst = cnx.prepareStatement(deleteData);
                pst.executeUpdate();
            }

            menuShowOrderData();
            menuDisplayTotal();
            gettitre = null; // reset the gettitre variable
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    
    @FXML
    void refresh_panier(ActionEvent event) {
         menuShowOrderData();
        menuDisplayTotal();
    }
    
     
    @FXML
    private void pdfbtn(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException, BadElementException, IOException {
        ObservableList<Offre> listData = FXCollections.observableArrayList();
        
        menuGetTotal();
        listData = menuGetOrder() ;
//        SessionManager sm=new SessionManager(listData);
//        
//        
//        
//      for (Offre o:listData){
//          System.out.println(sm.getListoffre());
//        for (int i=0;i<sm.getListoffre().size();i++){
//              int a=o.getId();
//                   sm.getListoffre().set(i,a);
//            
//            
//        }
//        
//        }
//      String insertData1 = "INSERT INTO `contrat`( `validitedu`, `validiteau`, `id_vehicule_id`, `id_client_id`, `photo_cin`) VALUES ('2023-05-02','2023-05-02','22','2','kkkkk')";
//      pst = cnx.prepareStatement(insertData1);
//      
//      pst.executeUpdate();
//      
//      for( int a:sm.getListoffre()){
//       String insertData = "INSERT INTO contrat_offre (contrat_id,offre_id) VALUES (?,?)";
//        pst = cnx.prepareStatement(insertData);
//        pst.setString(2, String.valueOf(a));
//        pst.setInt(1,7);
//    
//       
//        pst.executeUpdate();
//      }
      

      
      
        //System.out.println(listData);
         // Afficher la boîte de dialogue de sélection de fichier
         //System.out.println(totalP);
         //System.out.println(menu_amount);
         if (totalP == 0 ) { //|| menu_amount.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Vous devez faire votre ordre d'abord");
            alert.showAndWait();
        } else {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            // Générer le fichier PDF avec l'emplacement de sauvegarde sélectionné
            // Récupérer la liste des produits
            //CompetitionServices CompetitionService = new CompetitionServices();
           // List<Competition> CompetitionList = CompetitionService.afficherListe();

            try {
                // Créer le document PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Créer une instance de l'image
                Image image = Image.getInstance(System.getProperty("user.dir") + "/src/Pidev/assets/Img/pdfff.png");

                // Positionner l'image en haut à gauche
                image.setAbsolutePosition(5, document.getPageSize().getHeight() - 120);

                // Modifier la taille de l'image
                image.scaleAbsolute(100, 100);

                // Ajouter l'image au document
                document.add(image);

                // Créer une police personnalisée pour la date
                Font fontDate = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                BaseColor color = new BaseColor(255,128,0);
                fontDate.setColor(color); // Définir la couleur de la police
                
                // Créer un paragraphe avec le lieu
                Paragraph tunis = new Paragraph("Tunis", fontDate);
                tunis.setIndentationLeft(455); // Définir la position horizontale
                tunis.setSpacingBefore(-30); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(tunis);

                // Obtenir la date d'aujourd'hui
                LocalDate today = LocalDate.now();

                // Créer un paragraphe avec la date
                Paragraph date = new Paragraph(today.toString(), fontDate);

                date.setIndentationLeft(437); // Définir la position horizontale
                date.setSpacingBefore(1); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(date);

                // Créer une police personnalisée
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor titleColor = new BaseColor(255,128,0); //
                font.setColor(titleColor);

                // Ajouter le contenu au document
                Paragraph title = new Paragraph("Votre recu", font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50); // Ajouter une marge avant le titre pour l'éloigner de l'image
                title.setSpacingAfter(20);
                document.add(title);

                PdfPTable table = new PdfPTable(3); // 
                table.setWidthPercentage(100);
                table.setSpacingBefore(30f);
                table.setSpacingAfter(30f);
                //document.add(table);

                // Ajouter les en-têtes de colonnes
                Font hrFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                BaseColor hrColor = new BaseColor(0, 0, 0);
                hrFont.setColor(hrColor);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Nom Offre", hrFont));
                BaseColor bgColor = new BaseColor(211, 211, 211);
                cell1.setBackgroundColor(bgColor);
                cell1.setBorderColor(titleColor);
                cell1.setPaddingTop(20);
                cell1.setPaddingBottom(20);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell2 = new PdfPCell(new Paragraph("Validité", hrFont));
                cell2.setBackgroundColor(bgColor);
                cell2.setBorderColor(titleColor);
                cell2.setPaddingTop(20);
                cell2.setPaddingBottom(20);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell3 = new PdfPCell(new Paragraph("Prix", hrFont));
                cell3.setBackgroundColor(bgColor);
                cell3.setBorderColor(titleColor);
                cell3.setPaddingTop(20);
                cell3.setPaddingBottom(20);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);

                
                Font hdFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
                BaseColor hdColor = new BaseColor(255,128,0); //
                hdFont.setColor(hdColor);
                // Ajouter les données des produits
                 
                for (Offre offre : listData) {
                    Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor borderColor = new BaseColor(255,128,0); //
                font.setColor(borderColor);
                
                    PdfPCell cellR1 = new PdfPCell(new Paragraph(offre.getTitre()));
                    cellR1.setBorderColor(borderColor);
                    cellR1.setPaddingTop(10);
                    cellR1.setPaddingBottom(10);
                    cellR1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR1);

                    PdfPCell cellR2 = new PdfPCell(new Paragraph(offre.getValidite_offre().toString()));
                    cellR2.setBorderColor(titleColor);
                    cellR2.setPaddingTop(10);
                    cellR2.setPaddingBottom(10);
                    cellR2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR2);

                    PdfPCell cellR3 = new PdfPCell(new Paragraph(String.valueOf(offre.getPrix())));
                    cellR3.setBorderColor(titleColor);
                    cellR3.setPaddingTop(10);
                    cellR3.setPaddingBottom(10);
                    cellR3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR3);

                
                
            } 
                Font fontTotal = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            BaseColor totalColor = new BaseColor(255,128,0); //
            fontTotal.setColor(totalColor);
            Paragraph total = new Paragraph("Total: " + totalP + " DT", fontTotal);
            total.setAlignment(Element.ALIGN_RIGHT);
            total.setSpacingBefore(50);
            
            
                // Créer une police personnalisée
                Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor merciColor = new BaseColor(255,128,0); //
                font.setColor(merciColor);

                // Ajouter le contenu au document
                Paragraph merci = new Paragraph("Merci pour votre commande", font2);
                merci.setAlignment(Element.ALIGN_CENTER);
                merci.setSpacingBefore(20); // Ajouter une marge avant le titre pour l'éloigner de l'image
                //title.setSpacingAfter(20);
                
                
                table.setSpacingBefore(20);
                document.add(table);
                document.add(total);
                document.add(merci);
            document.close();

                System.out.println("Le fichier PDF a été généré avec succès.");
                menuRestart();
                menuClear();
                Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Fichier PDF généré avec succès");
            alert.setContentText("Le fichier PDF a été enregistré avec succès:\n"); //+ selectedFile.getAbsolutePath());
            alert.showAndWait();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
         }
    }
    public void menuClear() {
        
         String deleteData = "DELETE FROM customer";

        try {

           
                pst = cnx.prepareStatement(deleteData);
                pst.executeUpdate();
            

            menuShowOrderData();
            menuDisplayTotal();
            gettitre = null; // reset the gettitre variable
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
     public void menuRestart() {
        totalP = 0;
        menu_total.setText("$0.0");
    }
     
    @FXML
    void closepay(ActionEvent event) {
        payement.setVisible(false);
    }
    
    void openpanier(MouseEvent event) {
       // panier.setVisible(true);
    }

    @FXML
    private void open_listeConstat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AjouterConstat.fxml"));
        menu_gridPane.getChildren().removeAll();
        menu_gridPane.getChildren().setAll(fxml);
        panier.setVisible(false);
    }

    @FXML
    private void open_listeRdv(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AfficherRendezVous.fxml"));
        menu_gridPane.getChildren().removeAll();
        menu_gridPane.getChildren().setAll(fxml);
        panier.setVisible(false);
    }

    @FXML
    private void open_listeRec(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        menu_gridPane.getChildren().removeAll();
        menu_gridPane.getChildren().setAll(fxml);
        panier.setVisible(false);
    }

      @FXML
    void logout(MouseEvent event) {
         try {
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Déconnexion");
            alert.setHeaderText("Confirmation de déconnexion");
            alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {
                
                Stage stage = (Stage) logout.getScene().getWindow();
            stage.close();
                // TO HIDE MAIN FORM -
                //logout_btn.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM AND SHOW IT 
                Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                
                Stage stage2 = new Stage();
                Scene scene = new Scene(root);
                
                stage.setTitle("4RouesAssurances");
                
                stage.setScene(scene);
                stage.show();
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void open_listeProfil(MouseEvent event) throws IOException {
           Parent fxml = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        menu_gridPane.getChildren().removeAll();
        menu_gridPane.getChildren().setAll(fxml);
        panier.setVisible(false);
    }
}  