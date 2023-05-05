/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Constat;
import Pidev.Entities.Expert;
import Pidev.Entities.Vehicule;
import Pidev.Services.ConstatCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterConstatController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private ComboBox tftype;
    @FXML
    private ComboBox tfmarque;
    @FXML
    private TextField tfassurance;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfemplacement;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfobservations;
    @FXML
    private TextField tfnum;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button tfimage;
    @FXML
    private Label t1;
    @FXML
    private TextField t2;
    @FXML
    private ImageView image;  
    
    private String imageName = null;
           
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> listtype = FXCollections.observableArrayList("Voiture","Moto","Camion");
         tftype.setItems(listtype);
         ObservableList<String> listmarque = FXCollections.observableArrayList("Kia","Symbole","Clio","BMW","Range Rover","Ibiza","Mercedes","Toyota","Ford","Honda");
         tfmarque.setItems(listmarque);
         t1.setText(generateCaptcha());                         
    }    

    @FXML
    private void EnregistrerConstat(ActionEvent event) throws SQLException {
           
            String nomclient_e = tfnom.getText();
            String prenomclient_e = tfprenom.getText();
            String typevehicule_e = tftype.getSelectionModel().getSelectedItem().toString();
            String marquevehicule_e = tfmarque.getSelectionModel().getSelectedItem().toString();
            String assuranceclient_e = tfassurance.getText();
            String adresseclient_e = tfadresse.getText();
            String emplacementaccid = tfemplacement.getText();
            String photoaccid = tfimage.getText();
            String descriptiondegat = tfdescription.getText();
            String observations = tfobservations.getText();
            String numcontrat_e = tfnum.getText();
            String mail = tfmail.getText();
            LocalDate selectedDate = tfdate.getValue();
            String date_creation = selectedDate.toString();
            String s1 = t1.getText();
            String s2 = t2.getText();
         if (nomclient_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre nom!", ButtonType.OK);
            alert.showAndWait();
        }
        if (prenomclient_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre prenom!", ButtonType.OK);
            alert.showAndWait();
        }
        if (typevehicule_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez choisir le type de votre véhicule!", ButtonType.OK);
            alert.showAndWait();
        }
        if (marquevehicule_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez choisir la marque de votre véhicule!", ButtonType.OK);
            alert.showAndWait();
        }
        if (assuranceclient_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre assurance!", ButtonType.OK);
            alert.showAndWait();
        }
        if (adresseclient_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre adresse!", ButtonType.OK);
            alert.showAndWait();
        }
        if (emplacementaccid.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer l'emplacement de l'accident!", ButtonType.OK);
            alert.showAndWait();
        }
        if (photoaccid.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez insérer la photo de l'accident!", ButtonType.OK);
            alert.showAndWait();
        }
        if (descriptiondegat.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre description!", ButtonType.OK);
            alert.showAndWait();
        }
        if (observations.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre observation de degat!", ButtonType.OK);
            alert.showAndWait();
        }
        if (!numcontrat_e.matches("[0-9]+") || numcontrat_e.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre numéro de contrat!!", ButtonType.OK);
            alert.showAndWait();

        }
        if (mail.isEmpty() || !mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer votre email!", ButtonType.OK);
            alert.showAndWait();

        }
        if (date_creation.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Veuillez entrer la date de l'accident!", ButtonType.OK);
            alert.showAndWait();
        }
               if(s2.isEmpty() || !s1.equals(s2)){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please enter valid captcha!!", ButtonType.OK);
                  alert.showAndWait();
               }
                                
            Expert expert = new Expert(130);
            Vehicule vehicule = new Vehicule(167);
 
          
            Constat c = new Constat(nomclient_e,prenomclient_e,typevehicule_e,marquevehicule_e,assuranceclient_e,adresseclient_e,emplacementaccid,photoaccid,descriptiondegat,observations,numcontrat_e,mail,date_creation,expert,vehicule);
            ConstatCRUD ccd = new ConstatCRUD();
            ccd.ajouterConstat2(c);
                 
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Constat ajouter avec succées!",ButtonType.OK);
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsConstats.fxml"));
        
        try {    
            Parent root = loader.load();
            DetailsConstatsController dcc = loader.getController();
            dcc.setTfNom(c.getNomclient_e());
            dcc.setTfPrenom(c.getPrenomclient_e());
            dcc.setTfType(c.getTypevehicule_e());
            dcc.setTfMarque(c.getMarquevehicule_e());
            dcc.setTfAssurance(c.getAssuranceclient_e());
            dcc.setTfAdresse(c.getAdresseclient_e());
            dcc.setTfEmplacement(c.getEmplacementaccid());
            dcc.setTfPhoto(c.getPhotoaccid());
            dcc.setTfDescription(c.getDescriptiondegat());
            dcc.setTfObservations(c.getObservations());
            dcc.setTfNum(c.getNumcontrat_e());
            dcc.setTfMail(c.getMail());
            dcc.setTfDate(c.getDate_creation());
           
            ((Pane)tfnom.getParent()).getChildren().setAll(root);
            
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void ChoisirFichier(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        // Filtrer les fichiers pour ne montrer que les images
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif", "*.jfif"));

        // Ouvrir la boîte de dialogue pour choisir un fichier
        File selectedFile = fileChooser.showOpenDialog(image.getScene().getWindow());

        // Si l'utilisateur a sélectionné un fichier, afficher l'image dans l'ImageView
        if (selectedFile != null) {
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;
            String imageName = selectedFile.getName();
            String imagePath = selectedFile.getAbsolutePath();
            String destinationPath = imageName;
            Path destination = Paths.get(destinationPath);
            Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            String imageUri = imageName;
            tfimage.setText(imageUri);
            Image photo = new Image(destination.toUri().toString());
            image.setImage(photo);

            Constat c = new Constat();
            c.setPhotoaccid(imageName);
        }
    }  
    

    private static String generateCaptcha() {
        String captchaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String captcha = "";
        for(int i = 0; i < 5; i++) {
             int L = (int) (Math.random()*captchaString.length());
             captcha = captcha + captchaString.charAt(L);
         }
           return captcha;
    }
     
}


    

