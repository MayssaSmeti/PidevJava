/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author SCORPIO-12
 */
public class Main extends Application{
public static Stage stage = null;
    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/gui/UserDashboard.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/gui/AdminDashboard.fxml"));
            Scene scene = new Scene(root);

          //  primaryStage.getIcons().add(new Image("/assets/img/logo.png"));
            primaryStage.setTitle("4RouesAssurances");
            primaryStage.setScene(scene);
            Main.stage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    
    }
    
}
