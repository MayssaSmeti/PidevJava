package Pidev.Gui;

import Pidev.Entities.Rapport;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import Pidev.Services.ServiceRapport;
import javafx.scene.control.Button;
import Pidev.Utilis.DataSource;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class AjouterRapportController {
    private final String UPLOAD_DIR = "C:\\Users\\rayen\\Desktop\\PidevJava\\image";
    String path="";
    
    private File imageFile;

    @FXML
    private TextField description_field;
    @FXML
    private TextField preliminaire_field;
    @FXML
    private TextField expertise_field;
    @FXML
    private ImageView image_view;
    @FXML
    private Button choose;
    
    private ServiceRapport serviceRapport;

      ServiceRapport  sr  = new ServiceRapport();
    

    void initialize() {
     
    }

    @FXML
    void selectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            path=selectedFile.getAbsolutePath();
            Image image = new Image(selectedFile.toURI().toString());
            image_view.setImage(image);
        }
    }
 @FXML
     void ajouterRapport(ActionEvent event) throws IOException  {
         Rapport r = new Rapport();
         
        String description = description_field.getText();
        String rapportpreliminaire = preliminaire_field.getText();
        String rapportexpertise = expertise_field.getText();
        Image Image =image_view.getImage();
        System.out.println(rapportpreliminaire+" "+description+" "+rapportexpertise+" "+ Image);
        if (description.isEmpty() || rapportpreliminaire.isEmpty() || rapportexpertise.isEmpty() || Image == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs et sélectionner un fichier image");
            alert.showAndWait();
        }else{
            r.setDescription(description);
            r.setRapportpreliminaire(rapportpreliminaire);
            r.setRapportexpertise(rapportexpertise);
            r.setImage(path);


            sr.ajouter(r);
        }
        String destinationPath = "C:\\Users\\rayen\\Desktop\\PidevJava\\image";
////// Récupérer le fichier sélectionné
        File selectedFile = new File(path);
////
////// Créer un nouveau fichier dans le dossier de destination avec le même nom que le fichier sélectionné
        File destinationFile = new File(destinationPath + selectedFile.getName());
////
////// Copier le fichier sélectionné dans le dossier de destination
        Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Rapport ajouté avec succes");
        alert.showAndWait();
        clearFields();
    }

    private void clearFields() {
        description_field.setText("");
        preliminaire_field.setText("");
        expertise_field.setText("");
        imageFile = null;
        image_view.setImage(null);
    }

    @FXML
private void display(ActionEvent event) {
    Stage nouveauStage;
    try {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherRapport.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception however you want
    }
}


   
    
}
