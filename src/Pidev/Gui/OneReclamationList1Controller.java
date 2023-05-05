/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Reclamation;
import Pidev.Services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class OneReclamationList1Controller implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Text objet;
    @FXML
    private Text description;
    @FXML
    private Text note;
    @FXML
    private HBox editOffre;
    @FXML
    private HBox deleteOffre;
    @FXML
    private HBox detailsOffre;
    
      private Reclamation currentRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setReclamationData(Reclamation reclamation) {
        currentRec = reclamation;
        System.out.println(currentRec);
        String descriptionReclamation = reclamation.getDescription();
        String objetReclamation = reclamation.getObjet();
        int noteReclamation = reclamation.getNote();
        // définir la description pour l'affichage
        description.setText(descriptionReclamation);
        objet.setText(objetReclamation);
        note.setText(Integer.toString(noteReclamation));

        ReclamationCRUD r = new ReclamationCRUD();
        List<Reclamation> reclamations = r.afficherReclamations();

        deleteOffre.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette réclamation ?");
            alert.setContentText("Cette action est irréversible !");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeYes) {
                r.supprimerRec(reclamation.getId()); // supprimer la réclamations de la base de données

                FXMLLoader loader = new FXMLLoader(getClass().getResource("LiReclamation.fxml"));
                try {
                    Parent root = loader.load();
                    // Accéder à la pane content_area depuis le controller de
                    // OneProductListCard.fxml

                    Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                    contentArea.getChildren().clear();
                    contentArea.getChildren().add(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        editOffre.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierReclamation.fxml"));
                Parent root = loader.load();
                ModifierReclamationController modifierReclamationController = loader.getController();
                modifierReclamationController.setReclamation(reclamation);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
                // mettre à jour l'affichage de la liste des réponses après la fermeture de la fenêtre de modification
                chargerReclamations(new ActionEvent());
            } catch (IOException ex) {
                Logger.getLogger(OneReclamationListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // END edit btn click
        detailsOffre.setOnMouseClicked(event -> {

            // pour afficher le bouton update
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LiReponse_1.fxml"));
            try {
                Parent root = loader.load();

                LiReponse1Controller detailsController = loader.getController();
               
                   System.out.println("aaaaaaaaaaaaa");
                detailsController.setReponse(currentRec.getId());

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

    private void chargerReclamations(ActionEvent event) {
        ReclamationCRUD reclamationCRUD = new ReclamationCRUD();
        List<Reclamation> reclamations = reclamationCRUD.afficherReclamations();

        // Effacer le contenu de la zone d'affichage des réponses existantes
        // avant de charger les nouvelles réponses
        // Ici, content_area est une pane qui contient la liste des réponses
        Pane contentArea = (Pane) ((Node) event.getTarget()).getScene().lookup("#content_area");

        contentArea.getChildren().clear();

        // Parcourir toutes les réponses et créer un OneReponseListController pour chaque réponse
        for (Reclamation reclamation : reclamations) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("OneReclamationList.fxml"));
                Parent root = loader.load();
                OneReclamationListController oneReclamationListController = loader.getController();
                oneReclamationListController.setReclamationData(reclamation);
                contentArea.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(LiReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
