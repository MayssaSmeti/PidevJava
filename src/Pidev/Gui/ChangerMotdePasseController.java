/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import Pidev.Utilis.MyConnection;
import Pidev.Utilis.SessionManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Session;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class ChangerMotdePasseController implements Initializable {

    @FXML
    private TextField fxcode;
    @FXML
    private FontAwesomeIconView fxsend;
    @FXML
    private PasswordField fxnouveau;
    @FXML
    private PasswordField fxconfirmernouveau;

    private int verificationCode;

    public void setVerificationCode(int code) {
        this.verificationCode = code;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SendVerificationCode(MouseEvent event) throws SQLException, IOException {

//         String codeString = fxcode.getText();
//        String newPassword = hashMotDePasse(fxnouveau.getText());
//        String confirmNewPassword = hashMotDePasse(fxconfirmernouveau.getText());
//
//        // Check if the code entered matches the verification code
//        if (codeString.isEmpty() || !codeString.matches("\\d+")) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Code Invalid ");
//            alert.setHeaderText("Entrez le code s'il vous plait .");
//            alert.showAndWait();
//            return;
//        }
//
//        int code = Integer.parseInt(codeString);
//
//        if (code != verificationCode) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Code Incorrect ");
//            alert.setHeaderText("Le code de vérification est incorrect .");
//            alert.showAndWait();
//            return;
//        }
//        else{
//        // Check if the new password meets the minimum requirements
//        if (!isValidPassword(newPassword)) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Mot de passe invalide ");
//            alert.setHeaderText("Mot de Passe invalid . Votre mot de Passe doit etre >8 characteres .");
//            alert.showAndWait();
//            return;
//        }
//
//        // Check if the new password and confirm password match
//        if (!newPassword.equals(confirmNewPassword)) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle(" Les Mot de passe ne sont pas compatibles ");
//            alert.setHeaderText("Les Mot de passe ne sont pas compatibles .");
//            alert.showAndWait();
//            return;
//        }
//        
//        // TODO: Update the user's password in the database
//        // ...
//        UserCrud cc = new UserCrud(); 
//        User user = new User(codeString, newPassword);
//        cc.updateUserPassword(user);
//        
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Mot de passe modifié ");
//        alert.setHeaderText("Mot de passe modifié avec succes .");
//        alert.showAndWait();
//
//        Stage stage = (Stage) fxsend.getScene().getWindow();
//        stage.close();
//    }
//    }
//    
//     private boolean isValidPassword(String password) {
//        return password.length() >= 8;if(passid1.getText().equals(passid2.getText())){
         String pass = hashMotDePasse(fxnouveau.getText()); 
        if (fxnouveau.getText().equals(fxconfirmernouveau.getText())) {
            Connection cnx = MyConnection.getInstance().getCnx();
            String req = "UPDATE `user` SET `password`=? WHERE email= ?";
            try {
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, pass);
                ps.setString(2, SessionManager.getEmail());
                ps.executeUpdate();
                Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (SQLException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mot de passe modifié ");
            alert.setHeaderText("Mot de passe modifié avec succes .");
            alert.showAndWait();

        }

    }

    public String hashMotDePasse(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
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
