/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.CurrentUser;
import Pidev.Entities.Reclamation;
import Pidev.Services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class LiReclamation1Controller implements Initializable {

    @FXML
    private GridPane offreListContainer;
    @FXML
    private HBox offreContainer;
    @FXML
    private TextField searchField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Instancier le service de produit
        ReclamationCRUD r = new ReclamationCRUD();

        List<Reclamation> reclamations = r.afficherReclamationsByUser(CurrentUser.getLoggedInUser().getId());
        System.out.println(reclamations);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            updateDisplayedReclamation(newValue);

        });

        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < reclamations.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("oneReclamationList.fxml"));
                AnchorPane OneOffreCard = fxmlLoader.load();

                OneReclamationListController rapportController = fxmlLoader.getController();
                rapportController.setReclamationData(reclamations.get(i));

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

    private void updateDisplayedReclamation(String newValue) {

        // Instancier le service de réponse
        ReclamationCRUD r = new ReclamationCRUD();
        String o = searchField.getText();
        // Récupérer les réponses filtrées par la recherche avec Stream
        List<Reclamation> reclamations = r.afficherReclamationsByUser(CurrentUser.getLoggedInUser().getId()).stream()
                .filter(rec -> rec.getDescription().toLowerCase().contains(newValue.toLowerCase())
                || rec.getObjet().toLowerCase().contains(newValue.toLowerCase())
                || Integer.toString(rec.getNote()).contains(newValue.toLowerCase()))
                .collect(Collectors.toList());

        // Supprimer les anciens éléments de la liste
        offreListContainer.getChildren().clear();

        // Ajouter les nouveaux éléments de la liste
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < reclamations.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("oneReclamationList_1.fxml"));
                AnchorPane OneOffreCard = fxmlLoader.load();

                OneReclamationList1Controller rapportController = fxmlLoader.getController();
                rapportController.setReclamationData(reclamations.get(i));

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
        ReclamationCRUD reclamationCRUD = new ReclamationCRUD(); // créer une instance de la classe ReponseCRUD
        List<Reclamation> reclamations = reclamationCRUD.afficherReclamationsByUser(CurrentUser.getLoggedInUser().getId());

        // Tri de la liste de réponses dans l'ordre croissant en fonction de leur description
        Collections.sort(reclamations, (r1, r2) -> r1.getObjet().compareTo(r2.getObjet()));

        // Affichage de la liste triée dans le gridpane
        show(reclamations);
    }

    private void show(List<Reclamation> reclamations) {
        // Supprimer les anciens éléments de la liste
        offreListContainer.getChildren().clear();

        // Ajouter les nouveaux éléments de la liste
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < reclamations.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("LiReponse_1.fxml"));
                AnchorPane OneOffreCard = fxmlLoader.load();

                OneReclamationListController reclamationController = fxmlLoader.getController();
                reclamationController.setReclamationData(reclamations.get(i));

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
