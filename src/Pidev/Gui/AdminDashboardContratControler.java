
package Pidev.Gui;

import Pidev.Entities.Offre;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class AdminDashboardContratControler implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane offreListContainer;

    @FXML
    private AnchorPane content_areaFront;

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
      
    }
       
    @FXML
    void open_addOffreCard(MouseEvent event) throws IOException {
        Offre.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/AddOffre.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_listeOffre(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/ListeOffre.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);


    }

    @FXML
    void open_listeContrat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/ListeContrat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    void open_addContratCard(MouseEvent event) throws IOException {
        Offre.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/AddContrat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_listeOffreFront(MouseEvent event) throws IOException {
        if (content_areaFront != null) {
            Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/Front.fxml"));
            content_areaFront.getChildren().removeAll();
            content_areaFront.getChildren().setAll(fxml);
        } else {
            System.out.println("content_areaFront is null!");
        }
    }
    
}  