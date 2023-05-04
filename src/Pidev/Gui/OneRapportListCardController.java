
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Rapport;
import Pidev.Services.ServiceRapport;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class OneRapportListCardController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Text OffreName;
    @FXML
    private Text stockProduit;
    @FXML
    private Text validiteOffre;
    @FXML
    private Text stockProduit11;
    @FXML
    private HBox priceHbox;
    @FXML
    private Text priceOffre;
    @FXML
    private HBox editOffre;
    @FXML
    private HBox deleteOffre;
    @FXML
    private Text stockProduit1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setRapportData(Rapport rapport) {
            // Instancier le service de produit
            ServiceRapport rapportService = new ServiceRapport();

         /*   Image image = new Image(
                                    getClass().getResource("C:\\Users\\rayen\\Desktop\\4rouesAssuranccesPidev\\pidev1\\src\\edu\\pidev\\assets\\OffresUploads\\" + rapport.getImage()).toExternalForm());
            img.setImage(image);*/
           
         String s = "C:\\Users\\rayen\\Desktop\\4rouesAssuranccesPidev\\pidev1\\src\\edu\\pidev\\assets\\OffresUploads\\" + rapport.getImage();
System.out.println(s);
            File file = new File(s);
if(file.exists()) {
    Image image = new Image(file.toURI().toString());
    img.setImage(image);

} else {
    System.out.println("Image file not found!");
}

            OffreName.setText(rapport.getDescription());
            // get category Name
            validiteOffre.setText(rapport.getRapportpreliminaire());
    
            priceOffre.setText(rapport.getRapportexpertise());

            // deleteProduit btn click
            deleteOffre.setId(String.valueOf(rapport.getId()));

            System.out.println("ID du rapport à supprimer : " + rapport.getId());
            deleteOffre.setOnMouseClicked((MouseEvent event) -> {
                rapportService.supprimer(rapport.getId()); // supprimer le contenu de la liste et afficher la nouvelle liste(apres
                // supprimer)

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListRapport.fxml"));
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
        editOffre.setId(String.valueOf(rapport.getId()));

        editOffre.setOnMouseClicked(event -> {
            System.out.println("ID du rapport à modifier : " + rapport.getId());
            Rapport rapportToUpdate = new Rapport();
            rapportToUpdate.setId(rapport.getId());

           Rapport.setId(rapport, rapport.getId());

            Rapport.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRapport.fxml"));
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
    
        
    }    

