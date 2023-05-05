
package Pidev.Gui;



import Pidev.Entities.Devis;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class AdminDashboardDevisController implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private Text logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
      
    }
       
    @FXML
    void open_addOffreCard(MouseEvent event) throws IOException {
      
        Parent fxml = FXMLLoader.load(getClass().getResource("AjouterDevis.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_listeOffre(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ListeDevis.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
        

    }


    /*@FXML
    void open_addContratCard(MouseEvent event) throws IOException {
        Offre.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("AddContrat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }*/

      @FXML
    void logout(MouseEvent event) {
         try {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Déconnexion");
            alert.setHeaderText("Confirmation de déconnexion");
            alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {
                
                Stage stage = (Stage) logout.getScene().getWindow();
            stage.close();
                // TO HIDE MAIN FORM -
                //logout_btn.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM AND SHOW IT 
                Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                
                Stage stage2 = new Stage();
                Scene scene = new Scene(root);
                
                stage.setTitle("4RouesAssurances");
                
                stage.setScene(scene);
                stage.show();
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

} 