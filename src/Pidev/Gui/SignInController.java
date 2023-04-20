/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  Pidev.Gui;


import Pidev.Entities.User;
import Pidev.Utilis.MyConnection;
import edu.esprit.utilis.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class SignInController implements Initializable {

    @FXML
    private TextField fxemail;
    @FXML
    private TextField fxpassword;
    @FXML
    private Button btnconnexion;
    @FXML
    private Button btninscrit;

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private Hyperlink mtpoublie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
        if (fxemail.getText().equals("mayssa@gmail.com") && fxpassword.getText().equals("06102020")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("4roues assurrances  :: Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenu Admin");
            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene scene;

            scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);

            stage.show();

        } else {

            String query2 = "select * from user where email=?  and password=?";
            cnx = MyConnection.getInstance().getCnx();

            PreparedStatement smt = cnx.prepareStatement(query2);

            smt.setString(1, fxemail.getText());
            smt.setString(2, fxpassword.getText());
            ResultSet rs = smt.executeQuery();
            User p;
            if (rs.next()) {
                p = new User(rs.getString("email"), rs.getString("nom"), rs.getString("prenom"), rs.getString("password"), rs.getInt("cin"), rs.getString("adresse"), rs.getInt("num_tel"));
                User.setCurrent_User(p);
                SessionManager.getInstace(rs.getInt("id"), rs.getInt("cin"), rs.getString("email"), rs.getInt("num_tel"), rs.getString("nom"), rs.getString("adresse"), rs.getString("prenom"), rs.getString("roles"));
                System.out.println(User.Current_User.getEmail());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("4 roues assurances:: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Vous etes connecté");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Scene scene;
                scene = new Scene(root);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);

                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("4 roues assurances :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password !!");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void inscription(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("SignUp.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void ResetPassword(ActionEvent event) throws AddressException, MessagingException {
     
    }

}
