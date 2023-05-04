/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Rapport;
import Pidev.Services.ServiceRapport;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class EditRapportController implements Initializable {

 
    @FXML
    private TextField desc;
    
    @FXML
    private TextField rapport_pre;
    
    @FXML
    private TextField rapport_ex;
    @FXML
    private Button updateRapportBtn;
    private ImageView imageInput;
     private ServiceRapport rapportService;
  private Rapport rapport;
    private Stage dialogStage;
        private Rapport selectedRapport;
    private boolean okClicked = false;
    
    
    @FXML
    private HBox cinInputErrorHbox;
    @FXML
    private HBox dateduErrorInputHbox;
    @FXML
    private HBox dateauErrorInputHbox;
    @FXML
    private ImageView img;

    
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
      public boolean isOkClicked() {
        return okClicked;
    }
      
      public void setRendezvous(Rapport rapport) {
        this.rapport = rapport;
        desc.setText(rapport.getDescription());
        rapport_pre.setText(rapport.getRapportpreliminaire());
        rapport_ex.setText(rapport.getRapportexpertise());
        
    }

    private void handleOk() {
        if (isInputValid()) {
            rapport.setDescription(desc.getText());
            rapport.setRapportpreliminaire(rapport_pre.getText());
             rapport.setRapportexpertise(rapport_ex.getText());

            rapportService.modifier1(rapport);
            okClicked = true;
            dialogStage.close();
        }
    }

    private void handleCancel() {
        dialogStage.close();
    }
    private boolean isInputValid() {
        String errorMessage = "";

       if (desc.getText() == null || desc.getText().length() == 0) {
            errorMessage += " description invalide!\n";}

        if (rapport_pre.getText() == null || rapport_pre.getText().length() == 0) {
            errorMessage += "Rapport preliminaire invalide!\n";
        }
         if (rapport_ex.getText() == null || rapport_ex.getText().length() == 0) {
            errorMessage += "Rapport d'expertise invalide!\n";
        }
       

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
      }
    
    public void initData(Rapport rapport) {
    this.rapport = rapport;
    desc.setText(rapport.getDescription());
    rapport_pre.setText(rapport.getRapportpreliminaire());
    rapport_ex.setText(rapport.getRapportexpertise());
    String imagePath = rapport.getImage(); // get the image file path as a String
    if (imagePath != null) {
        File file = new File(imagePath);
        if (file.exists()) {
            Image image = new Image(file.toURI().toString());
            img.setImage(image);
        }
    }


    /*if (rapport.getImage() != null) {
Image image;
if (rapport.getImage() != null) {
    image = new Image(rapport.getImage());
} else {
    image = null;
}        imageInput.setImage(image);
    } else {
        
        imageInput.setImage(null);
    }
    */
}

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addNewRapport(MouseEvent event) {
        String description = desc.getText();
        String rapportpreliminaire = rapport_pre.getText();
        String rapportexpertise = rapport_ex.getText();
                    rapportService.modifier1(rapport);

    }

   /* private void ajouter_image(MouseEvent event) {
          FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
    selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
    if (selectedImageFile != null) {
        try {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "assets", "OffresUploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            // pour le controle de saisie

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}
     */   
        
    }
   

    

