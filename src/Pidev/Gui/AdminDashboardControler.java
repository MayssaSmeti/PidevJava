
package Pidev.Gui;

import Pidev.Entities.Rapport;
import Pidev.Services.ServiceRapport;
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
public class AdminDashboardControler implements Initializable {

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
       
    @FXML
    void open_addOffreCard(MouseEvent event) throws IOException {
     //   Rapport.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("AddRapport.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_listeOffre(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ListRapport.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);


    }

    @FXML
    void open_listeContrat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AfficherRendezvous.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    void open_addContratCard(MouseEvent event) throws IOException {
        //Rapport.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("AddRendezvousCard.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    private void open_stat(MouseEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("RdvStat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
        
    }
}  