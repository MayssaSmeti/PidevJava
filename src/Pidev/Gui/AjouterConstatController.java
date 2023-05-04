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
    private Text messagenom;
    @FXML
    private Text messageprenom;
    @FXML
    private Text messagetype;
    @FXML
    private Text messagemarque;
    @FXML
    private Text messageassu;
    @FXML
    private Text messageadresse;
    @FXML
    private Text messageaccid;
    @FXML
    private Text messagephoto;
    @FXML
    private Text messagedesc;
    @FXML
    private Text messageobs;
    @FXML
    private Text messagenum;
    @FXML
    private Text messagemail;
    @FXML
    private Text messagedate;
    @FXML
    private Label t1;
    @FXML
    private TextField t2;
    @FXML
    private Text messagecaptcha;
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
             if(nomclient_e.isEmpty()){
                   messagenom.setText("Veuillez entrer votre nom!");
                   return;
             }     
             if(prenomclient_e.isEmpty()){
                    messageprenom.setText("Veuillez entrer votre prenom!");
                    return;
             }           
             if(typevehicule_e.isEmpty()){
                   messagetype.setText("Veuillez choisir le type de votre véhicule!");
                   return;
             }
             if(marquevehicule_e.isEmpty()){
                  messagemarque.setText("Veuillez choisir la marque de votre véhicule!");
                  return;
             }         
             if(assuranceclient_e.isEmpty()){
                   messageassu.setText("Veuillez entrer votre assurance!");
                   return;
             } 
             if(adresseclient_e.isEmpty()){
                  messageadresse.setText("Veuillez entrer votre adresse!");
                  return;
             }
             if(emplacementaccid.isEmpty()){
                  messageaccid.setText("Veuillez entrer l'emplacement de l'accident!");
                  return;
             }
             if(photoaccid.isEmpty()){
                   messagephoto.setText("Veuillez insérer la photo de l'accident!");
                   return;
             }
             if(descriptiondegat.isEmpty()){
                  messagedesc.setText("Veuillez entrer votre description!"); 
                  return;
             }
             if(observations.isEmpty()){
                  messageobs.setText("Veuillez entrer votre observation de degat!");
                  return;
             }
             if(!numcontrat_e.matches("[0-9]+") || numcontrat_e.isEmpty()){
             messagenum.setText("Veuillez entrer votre numéro de contrat!");
             return;
             }
             if(mail.isEmpty() || !mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
                  messagemail.setText("Veuillez entrer votre email!");  
                  return;
             }
             if(date_creation.isEmpty()) {
                 messagedate.setText("Veuillez entrer la date de l'accident!");
                 return;
             }
               if(s2.isEmpty() || !s1.equals(s2)){
                messagecaptcha.setText("Please enter valid captcha!!");
              }
                                
            Expert expert = new Expert(1);
            Vehicule vehicule = new Vehicule(1);
 
          
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


    

