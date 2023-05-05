/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import com.jfoenix.controls.JFXButton;
import Pidev.Utilis.MyConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class VerifEmailController implements Initializable {

    @FXML
    private TextField Code;
    @FXML
    private FontAwesomeIconView valider;

    Connection DS, cnx;

    Statement ste;
    String email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean verifierCode(String activation_token) {
        String requete = "SELECT * FROM user WHERE activation_token= ?";
        PreparedStatement statement;
        ResultSet resultSet;
        Connection Ds = MyConnection.getInstance().getCnx();
        boolean codeExiste = true;

        try {
            statement = Ds.prepareStatement(requete);
            statement.setString(1, activation_token);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                codeExiste = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return !codeExiste;
    }

    @FXML
    private void sendActivation(MouseEvent event) throws SQLException {
        String activation_token = Code.getText();
        boolean saisieValide = true;
        String messageErreur = "";

        DS = MyConnection.getInstance().getCnx();

        if (activation_token.isEmpty()) {
            saisieValide = false;
            messageErreur += "Le champ code est requis.\n";
        } else {

            String req = "SELECT * FROM user WHERE activation_token = '" + activation_token + "'AND email = '" + email + "'";
            ste = DS.createStatement();
            ResultSet result = ste.executeQuery(req);

            if (!result.next()) {
                saisieValide = false;
                messageErreur += "Le code saisi est invalide.\n";
            }
        }

        if (!saisieValide) {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle("Erreur de saisie");
            alerte.setHeaderText("Le code saisi est incorrect");
            alerte.setContentText(messageErreur);
            alerte.showAndWait();
        } else {
            Alert alerte = new Alert(Alert.AlertType.INFORMATION);
            alerte.setTitle("Validation du code");
            alerte.setHeaderText("Le code saisi est correct");
            alerte.setContentText("Le code saisi est valide dans la base de données.");
            alerte.showAndWait();
            String requeteUpdate = "UPDATE user SET status = 'actif' WHERE email = ?";
            PreparedStatement statementUpdate = DS.prepareStatement(requeteUpdate);
            System.out.println(email);
            statementUpdate.setString(1, email);
            statementUpdate.executeUpdate();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("4 Roues Assurrances :: BIENVENUE");
            alert.setHeaderText(null);
            alert.setContentText("Vous Etes Inscrit !!");
            alert.showAndWait();

        }
    }

    public void setData(String email) {
        this.email = email;
        System.out.println(email + "aaaaa");

    }
}
