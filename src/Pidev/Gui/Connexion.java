/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

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

/**
 *
 * @author Mayssa
 */
public class Connexion extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("AdminDasshboard.fxml")) ;
            Parent root = FXMLLoader.load(getClass().getResource("Front.fxml")) ;
         //  Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("Liste.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("AdminDashboardDevis.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("AdminDashboardAssureur.fxml"));
             //Parent root = FXMLLoader.load(getClass().getResource("AdminDashboardRec.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("AdminDashboardContrat.fxml"));
                    //   Parent root = FXMLLoader.load(getClass().getResource("AdminDashboardRap.fxml"));

            //  Parent root = FXMLLoader.load(getClass().getResource("AdminDashboardCons.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("VerifEmail.fxml")) ;
           // Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
            // Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setTitle("Connexion !");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
