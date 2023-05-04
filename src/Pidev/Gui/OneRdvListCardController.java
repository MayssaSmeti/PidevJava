/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;
import Pidev.Entities.Rendezvous;
import Pidev.Entities.Rapport;
import Pidev.Services.ServiceRapport;
import Pidev.Services.ServiceRendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
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
public class OneRdvListCardController implements Initializable {

    @FXML
    private Text stockProduit;
    private Text validiteOffre;
    @FXML
    private Text stockProduit11;
    @FXML
    private HBox priceHbox;
    private HBox editOffre;
    private HBox deleteOffre;
    private Text lb_date;
@FXML
private ImageView img;
    @FXML
    private Text CName;
    @FXML
    private Text datedu;
    @FXML
    private HBox editC;
    @FXML
    private HBox deleteC;
    @FXML
    private HBox qrCodeOffre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setRdvData(Rendezvous rendezvous) {
            // Instancier le service de produit
            ServiceRendezvous rendezvousService = new ServiceRendezvous();
            Rendezvous rdv = new Rendezvous();
         
    
            CName.setText("" + rendezvous.getLieu());
            // get category Name
            datedu.setText("" + rendezvous.getDate());
    

            // deleteProduit btn click
            deleteC.setId(String.valueOf(rendezvous.getId()));

            deleteC.setOnMouseClicked(event -> {
            System.out.println("ID du rendezvous à supprimer : " + rendezvous.getId());
            rendezvousService.supprimer1(rendezvous.getId()); // supprimer le contenu de la liste et afficher la nouvelle liste(apres
                // supprimer)

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListRendezvous.fxml"));
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
        editC.setId(String.valueOf(rendezvous.getId()));

       /*  editC.setOnMouseClicked(event -> {
            System.out.println("ID du rendezvous à modifier : " + rendezvous.getId());
            Rendezvous.setId(rendezvous.getId());

            Rendezvous.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRendezvous.fxml"));
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

        }); */
        // END editProduit btn click


            
    
    
    
        }
    
        
    }    
    
