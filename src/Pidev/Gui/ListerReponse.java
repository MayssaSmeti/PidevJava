/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author msi
 */
public class ListerReponse extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try {
            Parent parent = FXMLLoader.load(getClass().getResource("ListerReponse.fxml"));
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            
            primaryStage.setTitle("TableView!");
            
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
