package edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 0326fe5 (api)
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
<<<<<<< HEAD
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
=======
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
>>>>>>> 0326fe5 (api)
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
<<<<<<< HEAD


=======
>>>>>>> 0326fe5 (api)
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
<<<<<<< HEAD
            System.out.println("content_areaFront is null!");
=======
            System.out.println("content_Front is null!");
>>>>>>> 0326fe5 (api)
        }
    }
    
}  