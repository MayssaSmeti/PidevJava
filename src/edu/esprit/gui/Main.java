/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
           try {
<<<<<<< HEAD
            Parent  root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            //Parent  root = FXMLLoader.load(getClass().getResource("ListVéhicule.fxml"));
           //Parent   root = FXMLLoader.load(getClass().getResource("Map.fxml"));
=======
            //Parent  root = FXMLLoader.load(getClass().getResource("DetailsConstats.fxml"));
            Parent  root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("Map.fxml"));
>>>>>>> 0326fe5 (api)

            Scene scene = new Scene(root);
        
            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(scene);
            primaryStage.show();
        } 
        catch (IOException ex) {
           System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
