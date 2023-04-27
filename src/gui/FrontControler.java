
package gui;

import entities.Offre;
import service.IOffreService;
import service.OffreService;
import utils.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    public ObservableList<Offre> cardListData;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Label labelVide = new Label("Votre panier est vide");


        menu_tableView.setPlaceholder(labelVide);
        

        //menuDisplayCard();
      
   // }

    //public ObservableList<Offre> menuGetData(){

    //     String sql = "SELECT * FROM offre";

    //     ObservableList<Offre> listData =FXCollections.observableArrayList();

    //     Connection cnx = MyConnection.getInstance().getCnx();
    //     try{
    //         PreparedStatement ps = cnx.prepareStatement(sql);
    //         ResultSet result = ps.executeQuery();

    //         Offre o ;
    //         while (result.next()){
    //             o = new Offre(result.getInt("id"), result.getString("description"),
    //             result.getInt("prix"),result.getString("titre"),
    //              result.getString("validite_offre"), result.getString("image_offre"));
    //             listData.add(o);
    //             }
    //     }catch (Exception e) {e.printStackTrace();}
        
    //     return listData;
    // }
       
    // private void menuDisplayCard() {
    //     cardListData.clear();
    //     cardListData.setAll(menuGetData());

    //     int row = 0;
    //     int column = 0;

    //     menu_gridPane.getRowConstraints().clear();
    //     menu_gridPane.getColumnConstraints().clear();
    //     for (int q = 0; q < cardListData.size(); q++){
    //         try {
    //             FXMLLoader load = new FXMLLoader();
    //             load.setLocation(getClass().getResource("/gui/OneOffreListCard.fxml"));
    //             AnchorPane pane  = load.load();
    //             OneOffreListCardFrontControler card = load.getController();
    //             card.setOffreData(cardListData.get(q));

    //             if (column == 3) {
    //                 column = 0;
    //                 row += 1;
    //             }

    //             menu_gridPane.add(pane, column++, row);
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }

    //     }
    }

    @FXML
    void open_addOffreCard(MouseEvent event) throws IOException {
        

    }

    

    @FXML
    void open_listeContrat(MouseEvent event) throws IOException {
       
    }

    @FXML
    void open_addContratCard(MouseEvent event) throws IOException {
       

    }
    @FXML 
    void open_listeOffre (MouseEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("ListeOffreFront.fxml"));
        menu_gridPane.getChildren().removeAll();
        menu_gridPane.getChildren().setAll(fxml);
    }
  
}  