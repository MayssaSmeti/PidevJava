
package Pidev.Gui;

import Pidev.Entities.Offre;
//import service.IOffreService;
//import service.OffreService;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class AdminDasshboardControler implements Initializable {

    @FXML
    private Pane content_area;
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
   

    @FXML
    void open_listeOffre(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);


    }

    @FXML
    private void openListUser(MouseEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

  


   

}
