
package gui;

import entities.Offre;
import service.IOffreService;
import service.OffreService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class FrontControler implements Initializable {

    @FXML
    private AnchorPane content_areaFront;

    @FXML
    private TextField menu_amount;

    @FXML
    private Label menu_change;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private Button menu_payer;

    @FXML
    private TableColumn<?, ?> menu_prix;

    @FXML
    private Button menu_recu;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private Button menu_supprimer;

    @FXML
    private TableView<?> menu_tableView;

    @FXML
    private TableColumn<?, ?> menu_titre;

    @FXML
    private Label menu_total;

    @FXML
    private TableColumn<?, ?> menu_validite;

    @FXML
    private Button open_listeOffre;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        menuDisplayCard();
      
    }
       
    private void menuDisplayCard() {
        // cardListData.clear();
        // cardListData.addALL(menuGetData());

        // int row = 0;
        // int column = 0;

        // menu_gridPane.getRowConstraints().clear();
        // menu_gridPane.getColumnConstraints().clear();
        // for (int q = 0; q < cardListData.size(); q++){
        //     try {
        //         FXMLLoader load = new FXMLLoader();
        //         load.getClass().getResource("/gui/AdminDashboard.fxml");
        //         AnchorPane pane  = load.load();
        //         OneOffreListCardFrontControler card = load.getController();
        //         card.setData(cardListData.get(q));

        //         if (column == 3) {
        //             column = 0;
        //             row += 1;
        //         }

        //         menu_gridPane.add(pane, column++, row);
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }

        // }
    }

    @FXML
    void open_addOffreCard(MouseEvent event) throws IOException {
        

    }

    @FXML
    void open_listeOffre(MouseEvent event) throws IOException {
        


    }

    @FXML
    void open_listeContrat(MouseEvent event) throws IOException {
       
    }

    @FXML
    void open_addContratCard(MouseEvent event) throws IOException {
       

    }

    @FXML
    void open_listeOffreFront(MouseEvent event) throws IOException {
        
    }
}  