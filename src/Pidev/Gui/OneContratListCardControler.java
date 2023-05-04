/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Pidev.Gui;

import Pidev.Entities.Contrat;
import Pidev.Services.ContratService;
import Pidev.Services.IContratService;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

//import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;
import java.io.File;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class OneContratListCardControler  {

    @FXML
    private Text CName;

    @FXML
    private Text VName;

    @FXML
    private Text dateau;

    @FXML
    private Text datedu;

    @FXML
    private HBox ExportrListe;

    @FXML
    private HBox deleteC;

    @FXML
    private HBox editC;

    @FXML
    private ImageView img;

    @FXML
    private HBox priceHbox;

    @FXML
    private HBox qrCodeOffre;

    @FXML
    private Text stockProduit;

    @FXML
    private HBox tablec;


        
        public void setContratData(Contrat contrat) {
            // Instancier le service de produit
            IContratService ContratService = new ContratService();

            /*Image image = new Image(
                    getClass().getResource("C:\\Users\\zaghd\\Desktop\\lacrim\\\\PidevJava\\src\\Pidev\\OffresUploads\\" + contrat.getPhoto_cin()).toExternalForm());
            img.setImage(image);*/
            String s="C:\\Users\\msi\\Documents\\NetBeansProjects\\Integration\\src\\Pidev\\OffresUploads\\" + contrat.getPhoto_cin();
//System.out.println(s);
            File file = new File(s);
if(file.exists()) {
    Image image = new Image(file.toURI().toString());
    img.setImage(image);
} else {
    System.out.println("Image file not found!");
}
    
            CName.setText("" + contrat.getId_client());
            // get category Name
            VName.setText("" + contrat.getId_vehicule());
    
            datedu.setText("" + contrat.getValiditedu());

            dateau.setText("" + contrat.getValiditeau());
            // deleteProduit btn click
            deleteC.setId(String.valueOf(contrat.getId()));

            deleteC.setOnMouseClicked(event -> {
            System.out.println("ID du offre à supprimer : " + contrat.getId());
            try {
                ContratService.supprimer(contrat.getId());
                
               
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // supprimer le contenu de la liste et afficher la nouvelle liste(apres
            // supprimer)

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeContrat.fxml"));
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
        // END deleteProduit btn click

        // editProduit btn click
        editC.setId(String.valueOf(contrat.getId()));

        editC.setOnMouseClicked(event -> {
            System.out.println("ID du produit à modifier : " + contrat.getId());
            Contrat.setIdContrat(contrat.getId());

            Contrat.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddContrat.fxml"));
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


            
    
    
    
        }
        @FXML
        void ExportrListe(MouseEvent event) throws IOException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, SQLException {

       
        
             printNode(tablec);
        
    }
        
        public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
            Printer printer = Printer.getDefaultPrinter();
            javafx.print.PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
            PrinterAttributes attr = printer.getPrinterAttributes();
            PrinterJob job = PrinterJob.createPrinterJob();
            double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
            Scale scale = new Scale(scaleX, scaleY);
            node.getTransforms().add(scale);
            
            
            if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
                boolean success = job.printPage(pageLayout, node);
                
                if (success) {
                    job.endJob();
                    
                }
            }
            node.getTransforms().remove(scale);
            
        }

        private void PDF(MouseEvent event) {
            // Contrat f = tablec.setId(String.valueOf(contrat.getId()));
    
            // Pdf pd=new Pdf();
            // try{
            //    pd.GeneratePdf("MesInformations",f,f.getMatricule());
            //     System.out.println("impression done");
            // } catch (Exception ex) {
            //     Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
            //     }
        }
    
        }
        
     
    

