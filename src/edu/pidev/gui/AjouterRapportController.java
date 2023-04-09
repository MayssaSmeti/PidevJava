package edu.pidev.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import edu.pidev.entities.Rapport;
import edu.pidev.services.ServiceRapport;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class AjouterRapportController implements Initializable {
	
    @FXML
    private TextField descriptionTF;

    @FXML
    private TextArea preliminaireTA;

    @FXML
    private TextArea expertiseTA;

    @FXML
    private Button imageButton;

    @FXML
    private Button ajouterButton;

    private Path imagePath;

    @FXML
    void onAjouterClicked() {
        String description = descriptionTF.getText();
        String preliminaire = preliminaireTA.getText();
        String expertise = expertiseTA.getText();

        if (description==  || preliminaire.isBlank() || expertise.isBlank()) {
            System.out.println("Veuillez remplir tous les champs.");
            return;
        }

        if (imagePath == null) {
            System.out.println("Veuillez choisir une image.");
            return;
        }

        byte[] imageData;
        try {
            imageData = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture de l'image.");
            return;
        }

        Rapport rapport = new Rapport(description, preliminaire, expertise, imageData);
        ServiceRapport serviceRapport = new ServiceRapport();
        serviceRapport.ajouter(rapport);

        System.out.println("Rapport ajouté !");
    }

    @FXML
    void onImageButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagePath = Paths.get(selectedFile.getAbsolutePath());
            imageButton.setText(selectedFile.getName());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagePath = null;
    }

}
