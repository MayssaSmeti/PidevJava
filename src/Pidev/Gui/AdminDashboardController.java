
package Pidev.Gui;



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

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane offreListContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
      
    }
       
   /* @FXML
    void open_addOffreCard(MouseEvent event) throws IOException {
        Devis.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("AddOffre.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }*/

    @FXML
    void open_listeOffre(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ListeDevis.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
        

    }

    @FXML
    void open_listeContrat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ListeContrat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    /*@FXML
    void open_addContratCard(MouseEvent event) throws IOException {
        Offre.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("AddContrat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }*/
} 