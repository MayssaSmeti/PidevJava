
package gui;

import entities.Offre;
import service.IOffreService;
import service.OffreService;
import test.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class AddOffreCardControler implements Initializable {

    @FXML
    private Button add_new_offreBtn;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private Text descriptionInputError;

    @FXML
    private HBox descriptionInputErrorHbox;

    @FXML
    private ImageView imageInput;

    @FXML
    private TextField nameInput;

    @FXML
    private Text nameInputError;

    @FXML
    private HBox nameInputErrorHbox;

    @FXML
    private Text photoInputError;

    @FXML
    private HBox photoInputErrorHbox;

    @FXML
    private TextField priceInput;

    @FXML
    private Text priceInputError;

    @FXML
    private HBox priceInputErrorHbox;

    @FXML
    private Text productName;

    @FXML
    private Button update_offreBtn;

    @FXML
    private TextField validiteInput;

    @FXML
    private Text validiteInputError;

    @FXML
    private HBox validiteInputErrorHbox;

    
    private File selectedImageFile;
    private String imageName = null;
    private int nomTest = 0;
    private int descriptionTest = 0;
    private int validiteTest = 0;
    private int priceTest = 0;
    private int photoTest = 0;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameInputErrorHbox.setVisible(false);
        descriptionInputErrorHbox.setVisible(false);
        validiteInputErrorHbox.setVisible(false);
        priceInputErrorHbox.setVisible(false);
        photoInputErrorHbox.setVisible(false);

        if (Offre.actionTest == 0) { // add offre
            update_offreBtn.setVisible(false);

        } else { // update offre
            add_new_offreBtn.setVisible(false);

            // Instancier le service de produit
            IOffreService offreService = new OffreService();
            Offre offre = new Offre();
           try {
             offre = offreService.getOneOffre(Offre.getIdOffre());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        nameInput.setText(offre.getTitre());
        descriptionInput.setText(offre.getDescription());
        validiteInput.setText(offre.getValidite_offre());
        priceInput.setText(String.valueOf(offre.getPrix()));
        Image image = new Image(getClass().getResource("/assets/OffresUploads/" + offre.getImage_offre()).toExternalForm());
        imageInput.setImage(image);
        imageName = offre.getImage_offre();

            nomTest = 1;
            descriptionTest = 1;
            validiteTest = 1;
            priceTest = 1;
            

        }
}
@FXML
    void addNewOffre(MouseEvent event) throws SQLException{

        Offre offre = new Offre();

        if (nameInput.getText().isEmpty()) {
            nomTest = 0;
            nameInputErrorHbox.setVisible(true);
        } else {
            if (nomTest == 1) {
                offre.setTitre(nameInput.getText());
            }
        }

        if (descriptionInput.getText().isEmpty()) {
            descriptionTest = 0;
            descriptionInputErrorHbox.setVisible(true);
        } else {
            if (descriptionTest == 1) {
                offre.setDescription(descriptionInput.getText());
            }
        }

       

        if (validiteInput.getText().isEmpty()) {
            validiteTest = 0;
            validiteInputErrorHbox.setVisible(true);
        } else {
            if (validiteTest == 1) {
                offre.setValidite_offre(validiteInput.getText());
            }
        }

        if (priceInput.getText().isEmpty()) {
            priceTest = 0;
            priceInputErrorHbox.setVisible(true);
        } else {
            if (priceTest == 1) {
                offre.setPrix(Integer.parseInt(priceInput.getText()));
            }
        }

        

        if (imageName == null) {
            photoTest = 0;
            photoInputErrorHbox.setVisible(true);
        } else {
            photoTest = 1;
            offre.setImage_offre(imageName);
        }

        if (nomTest == 1 && descriptionTest == 1 && validiteTest == 1 && priceTest == 1
                && photoTest == 1) {
            // Instancier le service de produit
            IOffreService offreService = new OffreService();
            try {
                offreService.ajouter(offre);
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeOffre.fxml"));

                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
              //  Parent root = FXMLLoader.load(getClass().getResource("ListeOffre.fxml"));
              //  Main.stage.getScene().setRoot(root);
                
              Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void ajouter_image(MouseEvent event) throws IOException {
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

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "assets", "OffresUploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            // pour le controle de saisie
            photoTest = 1;
            photoInputErrorHbox.setVisible(false);
        }
    }

    

   

   

    @FXML
    void descriptionTypedInput(KeyEvent event) {
        String descriptionText = ((TextArea) event.getSource()).getText();
        if (!descriptionText.isEmpty()) {
            descriptionInputErrorHbox.setVisible(false);
            descriptionTest = 1;
        }
    }

    @FXML
    void nameTypedInput(KeyEvent event) {
        String nameText = ((TextField) event.getSource()).getText();
        if (!nameText.isEmpty()) {
            nameInputErrorHbox.setVisible(false);
            nomTest = 1;
        }
    }

    @FXML
    void validiteTypedInput(KeyEvent event) {
        validiteTest = 1;
        validiteInputErrorHbox.setVisible(false);

        String validiteText = ((TextField) event.getSource()).getText();
        if (!validiteText.isEmpty()) {
            validiteInputErrorHbox.setVisible(false);
            validiteTest = 1;
        }
    }

    @FXML
    void priceTypedInput(KeyEvent event) {
        String priceText = ((TextField) event.getSource()).getText();
        if (!priceText.matches("-?\\d+")) {
            priceInputError.setText("le prix doit etre un entier");
            priceInputErrorHbox.setVisible(true);
            priceTest = 0;
        } else {
            int price = Integer.parseInt(priceText);
            if (price < 0) {
                priceInputError.setText("le prix doit etre positif");
                priceInputErrorHbox.setVisible(true);
                priceTest = 0;
            } else {
                priceInputErrorHbox.setVisible(false);
                priceTest = 1;
            }
        }
    }

    @FXML
    void updateOffre(MouseEvent event) throws SQLException {
        Offre offre = new Offre();

        offre.setId(Offre.getIdOffre());

        if (nameInput.getText().isEmpty()) {
            nomTest = 0;
            nameInputErrorHbox.setVisible(true);
        } else {
            if (nomTest == 1) {
                offre.setTitre(nameInput.getText());
            }
        }

        if (descriptionInput.getText().isEmpty()) {
            descriptionTest = 0;
            descriptionInputErrorHbox.setVisible(true);
        } else {
            if (descriptionTest == 1) {
                offre.setDescription(descriptionInput.getText());
            }
        }

       

        if (validiteInput.getText().isEmpty()) {
            validiteTest = 0;
            validiteInputErrorHbox.setVisible(true);
        } else {
            if (validiteTest == 1) {
                offre.setValidite_offre(validiteInput.getText());
            }
        }

        if (priceInput.getText().isEmpty()) {
            priceTest = 0;
            priceInputErrorHbox.setVisible(true);
        } else {
            if (priceTest == 1) {
                offre.setPrix(Integer.parseInt(priceInput.getText()));
            }
        }

        

        if (imageName == null) {
            photoTest = 0;
            photoInputErrorHbox.setVisible(true);
        } else {
            photoTest = 1;
            offre.setImage_offre(imageName);
        }

        System.out.println("nom: " +nomTest+"des: " + descriptionTest+ "va: " + validiteTest+"prix: " + priceTest+"photo: " + photoTest);
        if (nomTest == 1 && descriptionTest == 1 && validiteTest == 1 && priceTest == 1
                && photoTest == 1) {
            // Instancier le service de produit
            IOffreService offreService = new OffreService();
            try {
                offreService.modifier(offre);
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeOffre.fxml"));

                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
              //  Parent root = FXMLLoader.load(getClass().getResource("ListeOffre.fxml"));
              //  Main.stage.getScene().setRoot(root);
                
              Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    
}
    

