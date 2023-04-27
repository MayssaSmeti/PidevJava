/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Offre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import service.IOffreService;
import service.OffreService;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class OneOffreListCardControler  {

    @FXML
    private Text OffreName;

    @FXML
    private HBox deleteOffre;

    @FXML
    private HBox editOffre;

    @FXML
    private ImageView img;

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


        
        public void setOffreData(Offre offre) {
            // Instancier le service de l'offre
            IOffreService OffreService = new OffreService();

           
            // Image image = new Image(
            //         getClass().getResource("/assets/OffresUploads/" + offre.getImage_offre()).toExternalForm());
            // img.setImage(image);
String s="C:\\Users\\zaghd\\Desktop\\lacrim\\4RouesAssurances\\src\\assets\\OffresUploads\\" + offre.getImage_offre();
System.out.println(s);
            File file = new File(s);
if(file.exists()) {
    Image image = new Image(file.toURI().toString());
    img.setImage(image);
} else {
    System.out.println("Image file not found!");
}
          
    
            OffreName.setText(offre.getTitre());
            // get category Name
            validiteOffre.setText(offre.getValidite_offre());
    
            priceOffre.setText("" + offre.getPrix());

            // deleteOffre btn click
           


            
    
    
    
        }
    
        
    }    
    

