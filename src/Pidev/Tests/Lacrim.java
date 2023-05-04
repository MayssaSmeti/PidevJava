package Pidev.Tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Lacrim extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Pidev/Gui/AdminDashboard.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/gui/UserDashboard.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/gui/AdminDashboard.fxml"));
            Scene scene = new Scene(root);

          //  primaryStage.getIcons().add(new Image("/assets/img/logo.png"));
            primaryStage.setTitle("4RouesAssurances");
            primaryStage.setScene(scene);
            Lacrim.stage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    public static void main(String[] args) {
        // System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        launch(args);
    }
}