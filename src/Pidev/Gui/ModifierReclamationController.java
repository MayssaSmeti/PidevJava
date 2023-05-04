package Pidev.Gui;

import Pidev.Entities.Reclamation;
import Pidev.Services.ReclamationCRUD;
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
import javafx.scene.layout.Region;

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
        
        // Vérifier que la note est entre 0 et 20
    if(note < 0 || note > 5) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "La note doit être entre 0 et 5", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue

        // Appliquer un style personnalisé au message d'erreur
        alert.getDialogPane().getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("my-dialog");
        alert.showAndWait();
        return;
    }
    
    // Vérifier si la description est entre 10 et 200 caractères inclusivement
    if (description.length() < 10 || description.length() > 200) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "La description doit être entre 10 et 200 caractères inclusivement.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue

        // Appliquer un style personnalisé au message d'erreur
        alert.getDialogPane().getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("my-dialog");
        alert.showAndWait();
        return;
    }

    // Vérifier si l'objet est entre 5 et 50 caractères inclusivement
    if (objet.length() < 5 || objet.length() > 50) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "L'objet doit être entre 5 et 50 caractères inclusivement.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // pour ajuster la taille du dialogue

        // Appliquer un style personnalisé au message d'erreur
        alert.getDialogPane().getStylesheets().add(getClass().getResource("fxml.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("my-dialog");
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
