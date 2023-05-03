/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Vehicule;
import edu.esprit.services.VehiculeCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class OneVehiculeItemController implements Initializable {

    @FXML
    private Text tfmat;
    @FXML
    private Text tftype;
    @FXML
    private Text tfmarque;
    @FXML
    private Text tfnb;
    @FXML
    private ImageView btnAjout;
    @FXML
    private ImageView btnSupprime;
    @FXML
    private ImageView btnShow;
    @FXML
    private Text OffreName1;
    @FXML
    private Text stockProduit;
    @FXML
    private HBox editOffre;
    @FXML
    private HBox deleteOffre;
    @FXML
    private HBox qrCodeOffre;


   
//    private ListView<Vehicule> vehiculeListView;
//    VehiculeCRUD vcd = new VehiculeCRUD();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TO DO
    }
    public void setVehiculeData(Vehicule v) {

        tfmat.setText(v.getMatricule());
        tftype.setText(v.getType());
        tfmarque.setText(v.getMarque());
        tfnb.setText(v.getNb_ch());
        VehiculeCRUD vcd = new VehiculeCRUD();
        
       // deleteProduit btn click
        btnSupprime.setId(String.valueOf(v.getId()));

        btnSupprime.setOnMouseClicked(event -> {
            System.out.println("ID du vehicule à supprimer : " + v.getId());
            try {
                vcd.supprimer(v.getId());

            } catch (SQLException e) {
                e.printStackTrace();
            }
            // supprimer le contenu de la liste et afficher la nouvelle liste(apres
            // supprimer)

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListVéhicule.fxml"));
                try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
    });
            // end
//   });

        // END deleteDevis btn click
        // editDevis btn click
        btnAjout.setId(String.valueOf(v.getId()));

        btnAjout.setOnMouseClicked(event -> {
         
            // pour afficher le bouton update
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterVehicule.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneVehiculeListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de AddVehicule.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        // END edit btn click
           btnShow.setOnMouseClicked(event -> {
       
            // pour afficher le bouton update
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsVehicule.fxml"));
            try {
                Parent root = loader.load();
                DetailsVehiculeController detailsController = loader.getController();  
                detailsController.setTfMatricule(tfmat.getText());       
                detailsController.setTfType(tftype.getText());
                detailsController.setTfMarque(tfmarque.getText());   
                detailsController.setTfNb_Ch(tfnb.getText());

                // Accéder à la pane content_area depuis le controller de
                // OneVehiculeListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de AddVehicule.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
}
}
    

