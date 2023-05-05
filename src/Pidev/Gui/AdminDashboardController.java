package Pidev.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Pane content_area;


    @FXML
    private AnchorPane content_areaFront;

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
      
    }
    
    @FXML   
    void open_addConstat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AjouterConstat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }
    
    @FXML
    void open_listeConstat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ListConstat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
    
    @FXML
    void open_listeVéhicule(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ListVéhicule.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
    
    @FXML
    void open_addVéhicule(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AjouterVehicule.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_Front(MouseEvent event) throws IOException {
        if (content_areaFront != null) {
            Parent fxml = FXMLLoader.load(getClass().getResource("Front.fxml"));
            content_areaFront.getChildren().removeAll();
            content_areaFront.getChildren().setAll(fxml);
        } else {
            System.out.println("content_Front is null!");
        }
    }

    @FXML
    private void open_detailsConstat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("DetailsConstats.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    private void open_detailsVéhicule(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("DetailsVehicule.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }
    
    
}  