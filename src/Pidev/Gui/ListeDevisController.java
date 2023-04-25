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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Instancier le service de produit
        ServiceDevis DS = new ServiceDevis();

        List<Devis> devis;
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

}
