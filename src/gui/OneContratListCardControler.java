/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Contrat;
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
import service.ContratService;
import service.IContratService;
import service.IOffreService;
import service.OffreService;
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


        
        public void setContratData(Contrat contrat) {
            // Instancier le service de produit
            IContratService ContratService = new ContratService();

            Image image = new Image(
                    getClass().getResource("/assets/OffresUploads/" + contrat.getPhoto_cin()).toExternalForm());
            img.setImage(image);
    
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
    
        
    }    
    

