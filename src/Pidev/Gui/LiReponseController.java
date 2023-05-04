/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Services.ReponseCRUD;
import Pidev.Entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class LiReponseController implements Initializable {

    @FXML
    private  GridPane offreListContainer;
    @FXML
    private TextField searchField;
    @FXML
    private HBox offreContainer;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  @FXML
 
    /**
     * Initializes the controller class.
    **/
        
           // Instancier le service de produit
        ReponseCRUD re= new ReponseCRUD();


        List<Reponse> reponses= re.afficherReponses();
            
   searchField.textProperty().addListener((observable, oldValue, newValue) -> {
          
                updateDisplayedReponse(newValue);
       
        });
      

        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < reponses.size() ; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("oneReponseList.fxml"));
                AnchorPane OneOffreCard = fxmlLoader.load();

               OneReponseListController rapportController = fxmlLoader.getController();
                rapportController.setReponseData(reponses.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                offreListContainer.add(OneOffreCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(OneOffreCard, new Insets(0, 10, 25, 10));
                OneOffreCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    

    @FXML
    private void handlePieChartButtonAction(ActionEvent event) {
    
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./PieReponse.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(((Node)event.getSource()).getScene().getWindow());
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(javafx.scene.chart.PieChart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  private void updateDisplayedReponse(String newValue) {
    // Instancier le service de réponse
    ReponseCRUD re= new ReponseCRUD();

    // Récupérer les réponses filtrées par la recherche avec Stream
    List<Reponse> reponses = re.afficherReponses().stream()
                            .filter(r -> r.getDescription().toLowerCase().contains(newValue.toLowerCase()))
                            .collect(Collectors.toList());

    // Supprimer les anciens éléments de la liste
    offreListContainer.getChildren().clear();

    // Ajouter les nouveaux éléments de la liste
    int column = 0;
    int row = 1;
    try {
        for (int i = 0; i < reponses.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("oneReponseList.fxml"));
            AnchorPane OneOffreCard = fxmlLoader.load();

            OneReponseListController rapportController = fxmlLoader.getController();
            rapportController.setReponseData(reponses.get(i));

            if (column == 1) {
                column = 0;
                ++row;
            }
            offreListContainer.add(OneOffreCard, column++, row);
            GridPane.setMargin(OneOffreCard, new Insets(0, 10, 25, 10));
            OneOffreCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

      @FXML
    private void trie(ActionEvent event) {
                ReponseCRUD reponseCRUD = new ReponseCRUD(); // créer une instance de la classe ReponseCRUD
    List<Reponse> reponses = reponseCRUD.afficherReponses();
    
    // Tri de la liste de réponses dans l'ordre croissant en fonction de leur description
    Collections.sort(reponses, (r1, r2) -> r1.getDescription().compareTo(r2.getDescription()));

    // Affichage de la liste triée dans le gridpane
    show(reponses);
    }
    private void show(List<Reponse> reponses) {
    // Supprimer les anciens éléments de la liste
    offreListContainer.getChildren().clear();

    // Ajouter les nouveaux éléments de la liste
    int column = 0;
    int row = 1;
    try {
        for (int i = 0; i < reponses.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("oneReponseList.fxml"));
            AnchorPane OneOffreCard = fxmlLoader.load();

            OneReponseListController rapportController = fxmlLoader.getController();
            rapportController.setReponseData(reponses.get(i));

            if (column == 1) {
                column = 0;
                ++row;
            }
            offreListContainer.add(OneOffreCard, column++, row);
            GridPane.setMargin(OneOffreCard, new Insets(0, 10, 25, 10));
            OneOffreCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
             }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void trie(MouseEvent event) {
    }

  

  
}

        






    

