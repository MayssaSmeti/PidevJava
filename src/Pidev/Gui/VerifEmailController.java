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
    
    
    Connection DS;

    Statement ste;
    String email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public boolean verifierCode(String activation_token ) {
        String requete = "SELECT * FROM user WHERE activation_token= ?";
        PreparedStatement statement;
        ResultSet resultSet;
        Connection Ds = MyConnection.getInstance().getCnx();
        boolean codeExiste = true;

        try {
            statement = Ds.prepareStatement(requete);
            statement.setString(1,activation_token);
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
        String code = Code.getText();
        boolean verifierCode= true;
        String messageErreur = "";

        DS = MyConnection.getInstance().getCnx();

        if (code.isEmpty()) {
            verifierCode = false;
            messageErreur += "Le champ code est requis.\n";
        } else {

            String req = "SELECT * FROM user WHERE activation_token = '" + code + "'AND email = '" + email + "'";
            try {
                ste = DS.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(VerifEmailController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultSet result = null;
            try {
                result = ste.executeQuery(req);
            } catch (SQLException ex) {
                Logger.getLogger(VerifEmailController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!result.next()) {
                verifierCode = false;
                messageErreur += "Le code saisi est invalide.\n";
            }
        }

        if (!verifierCode) {
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
           /// String requeteUpdate = "UPDATE utilisateur SET activation_token = 'actif' WHERE email = ?";
            //PreparedStatement statementUpdate = DS.prepareStatement(requeteUpdate);
            //System.out.println(email);
            //statementUpdate.setString(1,email);
            //statementUpdate.executeUpdate();
            //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //stage.close();

        }

        
    }
      public void setData(String email) {
        this.email = email;
        System.out.println(email + "aaaaa");

    }
}
