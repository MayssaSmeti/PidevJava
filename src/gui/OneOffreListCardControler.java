/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

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

            Image image = new Image(
                    getClass().getResource("/assets/OffresUploads/" + offre.getImage_offre()).toExternalForm());
            img.setImage(image);
    
            OffreName.setText(offre.getTitre());
            // get category Name
            validiteOffre.setText(offre.getValidite_offre());
    
            priceOffre.setText("" + offre.getPrix());

            // deleteOffre btn click
            deleteOffre.setId(String.valueOf(offre.getId()));

            deleteOffre.setOnMouseClicked(event -> {
            System.out.println("ID du offre à supprimer : " + offre.getId());
            try {
                OffreService.supprimer(offre.getId());
               
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // supprimer le contenu de la liste et afficher la nouvelle liste(apres
            // supprimer)

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeOffre.fxml"));
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
        editOffre.setId(String.valueOf(offre.getId()));

        editOffre.setOnMouseClicked(event -> {
            System.out.println("ID du produit à modifier : " + offre.getId());
            Offre.setIdOffre(offre.getId());

            Offre.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddOffre.fxml"));
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
    

