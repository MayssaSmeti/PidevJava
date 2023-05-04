/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Rapport;
import Pidev.Services.IService;
import Pidev.Services.ServiceRapport;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AddRapportxCardControler implements Initializable {

    @FXML
    private Text productName;
    @FXML
    private TextArea desc;
    @FXML
    private HBox descriptionInputErrorHbox;
    @FXML
    private Text descriptionInputError;
    @FXML
    private ImageView imageInput;
    @FXML
    private HBox choose_photoBtn;
    @FXML
    private HBox photoInputErrorHbox;
    @FXML
    private Text photoInputError;
    @FXML
    private Button add_new_offreBtn;
    @FXML
    private Button update_offreBtn;
    @FXML
    private TextArea rapport_pre;
    @FXML
    private HBox descriptionInputErrorHbox1;
    @FXML
    private Text descriptionInputError1;
    @FXML
    private TextArea rapport_ex;
    @FXML
    private HBox descriptionInputErrorHbox11;
    @FXML
    private Text descriptionInputError11;
    
    private File selectedImageFile;
    private String imageName = null;
    private int nomTest = 0;
    private int descriptionTest = 0;
    private int validiteTest = 0;
    private int priceTest = 0;
    private int photoTest = 0;
     
     private ServiceRapport serviceRapport;
ServiceRapport sr=new ServiceRapport();
    @FXML
    private ImageView aaa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*ServiceRapport serviceRapport = new ServiceRapport();
            Rapport rapport = new Rapport();
             try {
             rapport = serviceRapport.getOneOffre(Rapport.getIdOffre());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
             
        desc.setText(rapport.getDescription());
        rapport_pre.setText(rapport.getRapportpreliminaire());
        rapport_ex.setText(rapport.getRapportexpertise());
           /*  Image image = new Image(getClass().getResource("/assets/OffresUploads/" + rapport.getImage()).toExternalForm());
        imageInput.setImage(image);
        imageName = rapport.getImage();*/
           
           
        descriptionInputErrorHbox.setVisible(false);
        descriptionInputErrorHbox1.setVisible(false);
        descriptionInputErrorHbox11.setVisible(false);
        photoInputErrorHbox.setVisible(false);

        if (Rapport.actionTest == 0) { // add offre
            update_offreBtn.setVisible(false);

        } else { // update offre
            add_new_offreBtn.setVisible(false);

            // Instancier le service de produit
            ServiceRapport serviceRapport = new ServiceRapport();
            Rapport rapport = new Rapport();
           try {
             rapport = serviceRapport.getOneOffre(Rapport.getIdOffre());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        desc.setText(rapport.getDescription());
        rapport_pre.setText(rapport.getRapportpreliminaire());
        rapport_ex.setText(rapport.getRapportexpertise());
        Image image = new Image(getClass().getResource("/assets/OffresUploads/" + rapport.getImage()).toExternalForm());
        imageInput.setImage(image);
        imageName = rapport.getImage();

            nomTest = 1;
            descriptionTest = 1;
            validiteTest = 1;
            priceTest = 1;
            

        }
           
    }    

    @FXML
    private void descriptionTypedInput(KeyEvent event) {
    }

    @FXML
    private void ajouter_image(MouseEvent event) throws IOException {
        
    
       
           
    /////////////////////////////////////
    FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get("C:", "Users", "rayen", "Desktop", "4rouesAssuranccesPidev", "pidev1", "src", "edu", "pidev", "assets", "OffresUploads", imageName);
Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            // pour le controle de saisie
            photoTest = 1;
            photoInputErrorHbox.setVisible(false);
        }
    }

    @FXML
    private void addNewOffre(MouseEvent event) {
        
        Rapport r = new Rapport();
         
        String description = desc.getText();
        String rapportpreliminaire = rapport_pre.getText();
        String rapportexpertise = rapport_ex.getText();
        Image Image =imageInput.getImage();
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
            r.setImage(imageName);


            sr.ajouter(r);
            
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Rapport ajouté avec succes");
        alert.showAndWait();
          clearFields();
        }  
      
       
    }

    @FXML
    private void updateOffre(MouseEvent event) {
    }
    
     private void clearFields() {
        desc.setText("");
        rapport_pre.setText("");
        rapport_ex.setText("");
        imageName= null;
        imageInput.setImage(null);
    }
    
}
