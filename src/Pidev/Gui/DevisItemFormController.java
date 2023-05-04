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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author SCORPIO-12
 */
public class DevisItemFormController implements Initializable {

    @FXML
    private VBox itemsContainer;
    @FXML
    private Button addButton;
    @FXML
    private GridPane formContainer;
    @FXML
    private TextField itemDescriptionTextField;
    @FXML
    private TextField itemPrixUTextField;
    @FXML
    private TextField itemQuantiteTextField;

    /**
     * Initializes the controller class.
     */
    private Devis devis;

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addNewRow(ActionEvent event) {
    }

    public float calculateDevisTotal(Devis devis) {
        float total = 0;
        for (DevisItem item : devis.getDevisItems()) {
            total += item.getTotalprice();
        }
        return total;
    }

    @FXML
    private void AddDevisItem() throws SQLException {
        ServiceDevis sd = new ServiceDevis();
        System.out.println(devis);
        double prixU = Double.parseDouble(itemPrixUTextField.getText());
        int quantite = Integer.parseInt(itemQuantiteTextField.getText());

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

            devis.addDevisItem(p);
            float devisTotal = calculateDevisTotal(devis);
            devis.setTotal_ht(devisTotal);
            sd.ajouterDevis(devis);

            Alert a = new Alert(Alert.AlertType.INFORMATION, "Item added successfully!", ButtonType.OK);
            a.showAndWait();
        }
    }
}
