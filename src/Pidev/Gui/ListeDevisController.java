package Pidev.Gui;

import Pidev.Entities.Devis;
import Pidev.Services.ServiceDevis;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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
public class ListeDevisController implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane DevisListContainer;

    @FXML
    private TextField txtRechercher;

    //@FXML
    //private TextField searchField;
    //@FXML
    //private TextField searchQuery;
    // private ObservableList<Devis> searchedProduits = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    ServiceDevis DS = new ServiceDevis();
    @FXML
    private Label lblNoResults;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Instancier le service de produit

        List<Devis> devis;

        txtRechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                updateDisplayedDevis(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ListeDevisController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ListeDevisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        try {
            devis = DS.getAll();
            // product list ------------------------------------------------
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < devis.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("OneDevisListCard.fxml"));
                    HBox OneCard = fxmlLoader.load();
                    OneDevisListCardControler devisCardController = fxmlLoader.getController();
                    devisCardController.setOffreData(devis.get(i));

                    if (column == 1) {
                        column = 0;
                        ++row;
                    }
                    DevisListContainer.add(OneCard, column++, row);
                    // GridPane.setMargin(oneProductCard, new Insets(10));
                    GridPane.setMargin(OneCard, new Insets(0, 10, 25, 10));
                    OneCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeDevisController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateDisplayedDevis(String searchTerm) throws SQLException, IOException {
        // Clear the existing cards
        DevisListContainer.getChildren().clear();

        // Create a new VBox layout container
        VBox cardsContainer = new VBox(10);

        // Get all Devis objects from the database
        List<Devis> devisList = DS.getAll();

        // Loop through each Devis object
        boolean foundResults = false;
        for (Devis devis : devisList) {
            // If the Devis object matches the search term, add it to the filtered VBox
            if (devis.getNomE().toLowerCase().contains(searchTerm.toLowerCase()) || devis.getPrenomE().toLowerCase().contains(searchTerm.toLowerCase())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneDevisListCard.fxml"));
                HBox OneCard = fxmlLoader.load();
                OneDevisListCardControler devisCardController = fxmlLoader.getController();

                // Set the data for the card
                devisCardController.setOffreData(devis);

                // Add the card to the filtered VBox
                cardsContainer.getChildren().add(OneCard);
                foundResults = true;

                // Set the margin for the card
                VBox.setMargin(OneCard, new Insets(10, 0, 0, 0));
                OneCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        }

        // Add the cards container to the main container
        DevisListContainer.getChildren().add(cardsContainer);

        // Set the visibility of the "No results found" label
        lblNoResults.setVisible(!foundResults);
    }

}
