/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Pidev.Gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Pidev.Entities.Offre;
import Pidev.Entities.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Pidev.Services.IOffreService;
import Pidev.Services.OffreService;
import Pidev.Utilis.MyConnection;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class OneOffreListCardFrontControler  {
    Connection cnx = MyConnection.getInstance().getCnx();

    @FXML
    private Text OffreDescription;

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
    private Label validiteOffre;

    private PreparedStatement pst;
    private ResultSet result;
    private Alert alert;
    private Offre offre;
    private Image image;
    private String prodID;
    private String type;
    private String prod_date;
    private String prod_image;
    private double totalP;
    private double pr;

        
        public void setOffreData(Offre offre) {
            // Instancier le service de produit
            IOffreService OffreService = new OffreService();
            this.offre = offre ;

            String s="C:\\Users\\zaghd\\Desktop\\lacrim\\PidevJava\\src\\Pidev\\OffresUploads\\" + offre.getImage_offre();
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
            OffreDescription.setText(offre.getDescription());
            validiteOffre.setText(offre.getValidite_offre());
    
            priceOffre.setText("" + offre.getPrix());
            

            // deleteProduit btn click
           // deleteOffre.setId(String.valueOf(offre.getId()));

            //deleteOffre.setOnMouseClicked(event -> {
            //System.out.println("ID du offre à supprimer : " + offre.getId());
            // try {
            //     OffreService.supprimer(offre.getId());
               
            // } catch (SQLException e) {
            //     e.printStackTrace();
            // }
            // supprimer le contenu de la liste et afficher la nouvelle liste(apres
            // supprimer)

        //     FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeOffre.fxml"));
        //     try {
        //         Parent root = loader.load();
        //         // Accéder à la pane content_area depuis le controller de
        //         // OneProductListCard.fxml
        //         Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

        //         // Vider la pane et afficher le contenu de ProductsList.fxml
        //         contentArea.getChildren().clear();
        //         contentArea.getChildren().add(root);
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        //     // end
        // });
        // END deleteProduit btn click

        // editProduit btn click
        // editOffre.setId(String.valueOf(offre.getId()));

        // editOffre.setOnMouseClicked(event -> {
        //     System.out.println("ID du produit à modifier : " + offre.getId());
        //     Offre.setIdOffre(offre.getId());

        //     Offre.actionTest = 1; // pour afficher le bouton update

        //     FXMLLoader loader = new FXMLLoader(getClass().getResource("AddOffre.fxml"));
        //     try {
        //         Parent root = loader.load();
        //         // Accéder à la pane content_area depuis le controller de
        //         // OneProductListCard.fxml
        //         Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

        //         // Vider la pane et afficher le contenu de AddProduct.fxml
        //         contentArea.getChildren().clear();
        //         contentArea.getChildren().add(root);
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }

        // });
        // END editProduit btn click


            
    
    
    
        }
        @FXML
    void addBtn(ActionEvent event) throws  SQLException {
        FrontControler mForm = new FrontControler();
        mForm.customerID();
        String insertData = "INSERT INTO customer "
                            + "( customer_id, prix, titre, validite_offre) "
                            + "VALUES(?,?,?,?)";
           pst = cnx.prepareStatement(insertData);
           pst.setString(1, String.valueOf(data.cID));
           pst.setString(2, priceOffre.getText());
           pst.setString(3, OffreName.getText());
           pst.setString(4, validiteOffre.getText());

             pst.executeUpdate();

        alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Offre ajoutée");
                    alert.setHeaderText(null);
                    alert.setContentText("L'offre est ajouté a votre panier avec succées!");
                    alert.showAndWait();

    }
    
        
    }    
    

