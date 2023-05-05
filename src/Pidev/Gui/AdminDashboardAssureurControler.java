
package Pidev.Gui;

import Pidev.Entities.Offre;
import Pidev.Services.IOffreService;
import Pidev.Services.OffreService;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static org.apache.batik.bridge.WindowWrapper.alert;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class AdminDashboardAssureurControler implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane offreListContainer;

    @FXML
    private AnchorPane content_areaFront;

    @FXML
    private Pane statakrem;
    
    @FXML
    private Pane statchahinez;
    
     @FXML
    private Text logout;
     
     @FXML
    private AnchorPane LACRIM;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   
      
    }
       
    @FXML
    void open_addOffreCard(MouseEvent event) throws IOException {
        /* Offre.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/AddOffre.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);*/

    }

    @FXML
    void open_listeOffre(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/ListeOffre.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);


    }

    @FXML
    void open_listeContrat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/ListeContrat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    void open_addContratCard(MouseEvent event) throws IOException {
        /*Offre.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/AddContrat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);*/

    }

    @FXML
    void open_listeOffreFront(MouseEvent event) throws IOException {
       /* if (content_areaFront != null) {
            Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/Front.fxml"));
            content_areaFront.getChildren().removeAll();
            content_areaFront.getChildren().setAll(fxml);
        } else {
            System.out.println("content_areaFront is null!");
        }*/
    }
    
    @FXML
    void logout(MouseEvent event) {
         try {
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
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

      @FXML
    void open_Dash(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/Stat.fxml"));
        LACRIM.getChildren().removeAll();
        LACRIM.getChildren().setAll(fxml);
        
      
//        Parent fxml2 = FXMLLoader.load(getClass().getResource("/Pidev/Gui/PieChart.fxml"));
//        statchahinez.getChildren().removeAll();
//        statchahinez.getChildren().setAll(fxml2);
//        
//        statakrem.setVisible(true);
    }

    @FXML
    void open_listeCars(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/ListVéhicule.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    void open_listeConstat(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/ListConstat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

   

    @FXML
    void open_listeRec(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/LiReclamation.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
}  