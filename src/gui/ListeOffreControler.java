
package gui;

import entities.Offre;
import service.IOffreService;
import service.OffreService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
public class ListeOffreControler implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane offreListContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Instancier le service de produit
        IOffreService offreService = new OffreService();


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

}    
    

