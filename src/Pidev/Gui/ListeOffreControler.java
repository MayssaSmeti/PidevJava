
package Pidev.Gui;

import Pidev.Entities.Offre;
import Pidev.Services.IOffreService;
import Pidev.Services.OffreService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class ListeOffreControler implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane offreListContainer;

    @FXML
    private TextField txtRechercher;
    IOffreService offreService = new OffreService();

    @FXML
    private Label lblNoResults;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Offre> offre;

        txtRechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                updateDisplayedOffre(newValue);
            } catch (SQLException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
         // Instancier le service de produit
        


             List<Offre> offres= offreService.getAll();
            
   
      

        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        
        try {
            
            for (int i = 0; i < offres.size() ; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneOffreListCard.fxml"));
                HBox OneOffreCard = fxmlLoader.load();
               OneOffreListCardControler offreCardController = fxmlLoader.getController();
                offreCardController.setOffreData(offres.get(i));

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

    private void updateDisplayedOffre(String newValue) throws SQLException, IOException {
        // Clear the existing cards
        offreListContainer.getChildren().clear();

        // Create a new VBox layout container
        VBox cardsContainer = new VBox(10);

        // Get all Offre objects from the database
        List<Offre> offreList = offreService.getAll();

        // Loop through each Offre object
        boolean foundResults = false;
        for (Offre offre : offreList) {
            // If the Offre object matches the search term, add it to the filtered VBox
            if (offre.getTitre().toLowerCase().contains(newValue.toLowerCase()) ) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneOffreListCard.fxml"));
                HBox OneCard = fxmlLoader.load();
                OneOffreListCardControler offreCardController = fxmlLoader.getController();

                // Set the data for the card
                offreCardController.setOffreData(offre);

                // Add the card to the filtered VBox
                cardsContainer.getChildren().add(OneCard);
                foundResults = true;

                // Set the margin for the card
                VBox.setMargin(OneCard, new Insets(10, 0, 0, 0));
                OneCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        }

        // Add the cards container to the main container
        offreListContainer.getChildren().add(cardsContainer);

        // Set the visibility of the "No results found" label
        lblNoResults.setVisible(!foundResults);
    }

    }


    

