package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author 
 */
public class AddOffreController implements Initializable {

    @FXML
    private GridPane AddOffreContainer;

    @FXML
    private Pane content_area;

    @FXML
    private ScrollPane scrollPane;


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
          
            // Ajouter offreContainer à la première ligne de AddoffreContainer
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("AddOffreCard.fxml"));
            VBox offreContainer1 = fxmlLoader1.load();
            AddOffreContainer.add(offreContainer1, 0, 1);
            GridPane.setMargin(offreContainer1, new Insets(0, 10, 25, 10));
            offreContainer1.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }    

       

    
}
