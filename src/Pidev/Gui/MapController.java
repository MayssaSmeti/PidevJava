/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MapController implements Initializable {
    
    public static double lon;
    public static double lat;
    @FXML
    private WebView webview; 
    private WebEngine webengine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
          webengine = webview.getEngine();

          url = this.getClass().getResource("GoogleMaps.html");
          webengine.load(url.toString());
    }    

    private void Localiser(ActionEvent event) {
        lat = (Double) webview.getEngine().executeScript("lat");
        lon = (Double) webview.getEngine().executeScript("lon");
        System.out.println("Lat: " + lat);
        System.out.println("LOn " + lon);
     }
    
    // JavaScript interface object 
     private class JavaApp {
     public void exit() {
     Platform.exit();
  }
}
}
    

