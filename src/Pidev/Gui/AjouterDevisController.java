/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;


import Pidev.Entities.Devis;
import Pidev.Entities.DevisItem;
import Pidev.Entities.Expert;
import Pidev.Entities.Mecanicien;
import Pidev.Services.ServiceDevis;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SCORPIO-12
 */
public class AjouterDevisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox itemsContainer;
    @FXML
    private Button addButton;
    @FXML
    private TextField itemDescriptionTextField;
    @FXML
    private TextField itemPrixUTextField;
    @FXML
    private TextField itemQuantiteTextField;

    private Devis devis;
    @FXML
    private GridPane formContainer;

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addNewRow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DevisItemForm.fxml"));
            Node row = loader.load();
            itemsContainer.getChildren().add(row);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void AddDevis() throws SQLException, IOException {

        ServiceDevis sd = new ServiceDevis();

        double prixU = Double.parseDouble(itemPrixUTextField.getText());
        int quantite = Integer.parseInt(itemQuantiteTextField.getText());
        System.out.println(quantite);

        if (itemDescriptionTextField.getText().isEmpty() || itemPrixUTextField.getText().isEmpty() || itemQuantiteTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("SVP remplir tous les champs" + "");
            alert.showAndWait();

        } else if (prixU < 0 || quantite <= 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être supérieur à zéro.");
            alert.showAndWait();
        } else {

            float total = (float) (prixU * quantite);
            DevisItem p = new DevisItem(itemDescriptionTextField.getText(), (int) prixU, quantite, (float) total);
            if (devis == null) {
                devis = new Devis();
                Mecanicien m = new Mecanicien(2);
                Expert e = new Expert(1);

                devis.setMecanicien(m);
                devis.setExpert(e);
            }
            System.out.println("item" + p);

            devis.addDevisItem(p);
            devis.setTotal_ht(55);
            System.out.println("devis" + devis);
            sd.ajouterDevis(devis);

            Alert a = new Alert(Alert.AlertType.INFORMATION, "Devis ajouter avec succées !", ButtonType.OK);
            a.showAndWait();

        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./ListeDevis.fxml"));
            Parent view_2 = loader.load();

            Stage stage;
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeDevisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}