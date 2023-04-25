package edu.esprit.gui2;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ReclamationCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifierReclamationController implements Initializable {

    private Reclamation reclamation;

    @FXML
    private TextField tfObjet;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfNote;
    @FXML
    private Button btnModifier;
    @FXML
    private Label lblIdReclamation;

    private ReclamationCRUD rc = new ReclamationCRUD();

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
        lblIdReclamation.setText(String.valueOf(reclamation.getId()));
        tfObjet.setText(reclamation.getObjet());
        taDescription.setText(reclamation.getDescription());
        tfNote.setText(String.valueOf(reclamation.getNote()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void modifierReclamation() {
        String objet = tfObjet.getText();
        String description = taDescription.getText();
        String noteStr = tfNote.getText();

        // Vérifier si les champs sont vides
        if (objet.isEmpty() || description.isEmpty() || noteStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        int note;
        try {
            // Convertir la note en un entier
            note = Integer.parseInt(noteStr);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Note invalide");
            alert.setContentText("La note doit etre un nombre entier");
            alert.showAndWait();
            return;
        }

        // Mettre à jour la réclamation
        reclamation.setObjet(objet);
        reclamation.setDescription(description);
        reclamation.setNote(note);
        rc.modifier(reclamation);

        // Afficher une notification
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Réclamation modifiée avec succès!", ButtonType.OK);
        alert.showAndWait();

        // Fermer la fenêtre de modification
        tfObjet.getScene().getWindow().hide();
    }
}
