/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Constat;
import Pidev.Services.ConstatCRUD;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class OneConstatItemController implements Initializable {

    @FXML
    private ImageView tfimage;
    @FXML
    private Text tfnom;
    @FXML
    private Text tfemp;
    @FXML
    private Text tfnum;
    @FXML
    private Text tfmail;
    @FXML
    private Text tfdate;
    @FXML
    private ImageView btnadd;
    @FXML
    private ImageView btndelete;
    @FXML
    private ImageView btnshow;
    @FXML
    private Text stockProduit1;
    @FXML
    private Text stockProduit12;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TO DO
    }

     public void setConstatData(Constat c) {
            // Instancier le service de l'offre
            ConstatCRUD ConstatService = new ConstatCRUD();

            String s="C:\\Users\\msi\\Desktop\\Integration\\PidevJava\\src\\Pidev\\assets\\Img\\" + c.getPhotoaccid();
            System.out.println(s);
            File file = new File(s);
            if(file.exists()) {
            Image image = new Image(file.toURI().toString()) {};
            tfimage.setImage(image);
            } else {
                 System.out.println("Image file not found!");
            }
          
    
            tfnom.setText(c.getNomclient_e()+" " +c.getPrenomclient_e());
            tfemp.setText(c.getEmplacementaccid());
            tfnum.setText(c.getNumcontrat_e());
            tfmail.setText(c.getMail());
            tfdate.setText(c.getDate_creation());
            
            ConstatCRUD ccd = new ConstatCRUD();

            // deleteConstat btn click
            
        btndelete.setId(String.valueOf(c.getId()));

        btndelete.setOnMouseClicked(event -> {
            System.out.println("ID du constat à supprimer : " + c.getId());
            try {
                ccd.supprimerConstat(c.getId());

            } catch (SQLException e) {
                e.printStackTrace();
            }
            // supprimer le contenu de la liste et afficher la nouvelle liste(apres
            // supprimer)

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListConstat.fxml"));
                try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ListConstat.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
    });
            // end

        // END deleteDevis btn click
        // editDevis btn click
        btnadd.setId(String.valueOf(c.getId()));

        btnadd.setOnMouseClicked(event -> {
         
            // pour afficher le bouton update
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterConstat.fxml"));
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
           btnshow.setOnMouseClicked(event -> {
       
            // pour afficher le bouton update
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsConstats.fxml"));
            try {
                Parent root = loader.load();
                DetailsConstatsController detailsController = loader.getController();  
                detailsController.setTfNom(tfnom.getText());       
                detailsController.setTfPrenom(c.getPrenomclient_e());
                detailsController.setTfType(c.getTypevehicule_e());   
                detailsController.setTfMarque(c.getMarquevehicule_e());
                detailsController.setTfAssurance(c.getAssuranceclient_e());
                detailsController.setTfAdresse(c.getAdresseclient_e());
                detailsController.setTfEmplacement(tfemp.getText());
                detailsController.setTfPhoto(c.getPhotoaccid());
                detailsController.setTfDescription(c.getDescriptiondegat());
                detailsController.setTfObservations(c.getObservations());
                detailsController.setTfNum(tfnum.getText());
                detailsController.setTfMail(tfmail.getText());
                detailsController.setTfDate(tfdate.getText());



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

