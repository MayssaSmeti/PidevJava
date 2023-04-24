/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class FrontDashboardControler implements Initializable {

    @FXML
    private Pane content_area;
    
     @FXML
    private AnchorPane fxFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Parent fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            content_area.getChildren().removeAll();
            content_area.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(FrontDashboardControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
