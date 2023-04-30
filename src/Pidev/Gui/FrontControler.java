
package Pidev.Gui;

import Pidev.Entities.Offre;
//import Pidev.Entities.Panier;
import Pidev.Entities.customersData;
import Pidev.Entities.data;
import Pidev.Services.IOffreService;
import Pidev.Services.OffreService;
import Pidev.Tests.Main;
import Pidev.Utilis.MyConnection;

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

    @FXML
    private TableColumn<customersData, String> customers_col_customerID;

    public ObservableList<Offre> cardListData;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         customerID();
       // Main myApp = new Main();
        //Panier panier = myApp.getPanier();

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
    private int cID;
    Connection cnx = MyConnection.getInstance().getCnx();
    private PreparedStatement pst;
    private ResultSet result;
    public void customerID() {
        String sql = "SELECT MAX(customer_id) FROM customer";
        
        
        try {
            pst = cnx.prepareStatement(sql);
          result = pst.executeQuery();
            
            if (result.next()) {
                cID = result.getInt("MAX(customer_id)");
            }
            
            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            pst = cnx.prepareStatement(checkCID);
          result = pst.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(customer_id)");
            }
            
            if (cID == 0) {
                cID += 1;
            } else if (cID == checkID) {
                cID += 1;
            }
            
            data.cID = cID;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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