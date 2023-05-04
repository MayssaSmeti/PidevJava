
package Pidev.Gui;

import Pidev.Entities.Contrat;
import Pidev.Entities.Offre;
import Pidev.Services.ContratService;
import Pidev.Services.IContratService;
import Pidev.Services.IOffreService;
import Pidev.Services.OffreService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class Listestat implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane offreListContainer;
    
     @FXML
    private HBox Excel;
     
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
          
            // Ajouter offreContainer à la première ligne de AddoffreContainer
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("Stat.fxml"));
            StackPane offreContainer1 = fxmlLoader1.load();
            offreListContainer.add(offreContainer1, 0, 1);
            GridPane.setMargin(offreContainer1, new Insets(0, 10, 25, 10));
            offreContainer1.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

        } catch (IOException e) {
            e.printStackTrace();
        }
            }
      
    
     @FXML
    void liste(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ListeContrat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
}

 
    

