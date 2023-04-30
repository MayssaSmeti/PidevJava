/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.CurrentUser;
import Pidev.Entities.User;
import Pidev.Utilis.MyConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class PasswordController implements Initializable {

    @FXML
    private TextField fxnouveau;
    @FXML
    private TextField fxconfirmernouveau;
    @FXML
    private TextField fxactuel;
    Connection DS ; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SEND(MouseEvent event) throws SQLException {
        
          User loggedInUser = CurrentUser.getLoggedInUser();

        String ancienMotDePasse = hashMotDePasse(fxactuel.getText());
        String nouveauMotDePasse = hashMotDePasse(fxnouveau.getText());
        String confirmerMotDePasse = hashMotDePasse(fxconfirmernouveau.getText());

        if (!nouveauMotDePasse.equals(confirmerMotDePasse)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Les mots de passe ne correspondent pas.");
            alert.showAndWait();
            return;
        }

         String hashAncienMotDePasse = hashMotDePasse(ancienMotDePasse);
        if (hashAncienMotDePasse.equals(loggedInUser.getPassword())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe actuel est incorrect.");
            alert.showAndWait();
            System.out.println(loggedInUser.getPassword()+ " /// " + ancienMotDePasse);
            return;
        }

        String hashNouveauMotDePasse = hashMotDePasse(nouveauMotDePasse);

        DS = MyConnection.getInstance().getCnx();
        String query = "UPDATE user SET password=? WHERE id=?";
        PreparedStatement stmt = DS.prepareStatement(query);
        stmt.setString(1, nouveauMotDePasse);
        stmt.setInt(2, loggedInUser.getId());
        stmt.executeUpdate();
        stmt.close();
        DS.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mot de passe modifié");
        alert.setHeaderText(null);
        alert.setContentText("Le mot de passe a été modifié avec succès.");
        alert.showAndWait();
    }

    public String hashMotDePasse(String motDePasse) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(motDePasse.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    }
    

