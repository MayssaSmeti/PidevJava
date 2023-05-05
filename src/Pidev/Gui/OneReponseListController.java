/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import javafx.event.ActionEvent;
import Pidev.Entities.Reponse;
import Pidev.Services.ReponseCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class OneReponseListController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private HBox deleteOffre;
    @FXML
    private Text description;
    @FXML
    private HBox editOffre;
    @FXML
    private HBox detailsOffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 

    void setReponseData(Reponse reponse) {
   // récupérer la description de la réponse
    String descriptionReponse = reponse.getDescription();
    // définir la description pour l'affichage
    description.setText(descriptionReponse);

    ReponseCRUD re = new ReponseCRUD();
    List<Reponse> reponses = re.afficherReponses();
         
    deleteOffre.setOnMouseClicked((MouseEvent event) -> {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette réponse ?");
    alert.setContentText("Cette action est irréversible !");
    
    ButtonType buttonTypeYes = new ButtonType("Oui");
    ButtonType buttonTypeNo = new ButtonType("Non");
    
    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
    
    // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
    Optional<ButtonType> result = alert.showAndWait();
    
    if (result.get() == buttonTypeYes) {
        re.supprimerRep(reponse.getId()); // supprimer la réponse de la base de données
                
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LiReponse.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierReponse.fxml"));
        Parent root = loader.load();
        ModifierReponseController modifierReponseController = loader.getController();
        modifierReponseController.setReponse(reponse);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        // mettre à jour l'affichage de la liste des réponses après la fermeture de la fenêtre de modification
        chargerReponses(new ActionEvent());
    } catch (IOException ex) {
        Logger.getLogger(OneReponseListController.class.getName()).log(Level.SEVERE, null, ex);
    }
});

  // END edit btn click
           detailsOffre.setOnMouseClicked(event -> {
       
            // pour afficher le bouton update
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListerReponse.fxml"));
            try {
                Parent root = loader.load();
                ListerReponseController detailsController = loader.getController();  
                detailsController.setTextDescription(description.getText());       
           



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

  private void chargerReponses(ActionEvent event) {
    ReponseCRUD reponseCRUD = new ReponseCRUD();
    List<Reponse> reponses = reponseCRUD.afficherReponses();
    
    // Effacer le contenu de la zone d'affichage des réponses existantes
    // avant de charger les nouvelles réponses
    // Ici, content_area est une pane qui contient la liste des réponses
    Pane contentArea = (Pane) ((Node) event.getTarget()).getScene().lookup("#content_area");


    contentArea.getChildren().clear();
    
    // Parcourir toutes les réponses et créer un OneReponseListController pour chaque réponse
    for (Reponse reponse : reponses) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OneReponseList.fxml"));
            Parent root = loader.load();
            OneReponseListController oneReponseListController = loader.getController();
            oneReponseListController.setReponseData(reponse);
            contentArea.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(LiReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



    
    
}
