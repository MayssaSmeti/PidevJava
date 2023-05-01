
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
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Offre, String> menu_prix;

    @FXML
    private Button menu_recu;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private Button menu_supprimer;

    @FXML
    private TableView<Offre> menu_tableView;

    @FXML
    private TableColumn<Offre, String> menu_titre;

    @FXML
    private Label menu_total;

    @FXML
    private TableColumn<Offre, String> menu_validite;

    @FXML
    private Button open_listeOffre;

    @FXML
    private TableColumn<customersData, String> customers_col_customerID;

      @FXML
    private AnchorPane panier;
      
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
        
        menuDisplayTotal();
        
        menuShowOrderData();
        

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
        panier.setVisible(true);
    }
     private double totalP;
    
    public void menuGetTotal() {
        customerID();
        String total = "SELECT SUM(prix) FROM customer WHERE customer_id = " + cID;
        
        
        
        try {
            
           pst = cnx.prepareStatement(total);
          result = pst.executeQuery();
            
            if (result.next()) {
                totalP = result.getInt("SUM(prix)");
                System.out.println(totalP);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void menuDisplayTotal() {
        menuGetTotal();
        menu_total.setText("" + totalP);
    }
    
   private ObservableList<Offre> menuOrderListData;
    
    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();
        
        menu_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        menu_validite.setCellValueFactory(new PropertyValueFactory<>("validite_offre"));
        menu_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        System.out.println(menuOrderListData);
        menu_tableView.setItems(menuOrderListData);
    }
    
    ///////////////////////////////////////////////////////////
    public ObservableList<Offre> menuGetOrder() {
        customerID();
        ObservableList<Offre> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM customer WHERE customer_id = " + cID;
        
        
        
        try {
             pst = cnx.prepareStatement(sql);
          result = pst.executeQuery();
            
            Offre prod;
            
            while (result.next()) {
                prod = new Offre(result.getInt("id"),
                        result.getInt("prix"),
                        result.getString("titre"),
                        result.getString("validite_offre"));
                listData.add(prod);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listData;
    }
    
     @FXML
    void menu_payer(ActionEvent event) {
       
        
    }
     private int getid;
     String gettitre;
     Alert alert;
     
      @FXML
public void menuSelectOrder() {
    Offre prod = menu_tableView.getSelectionModel().getSelectedItem();
    int num = menu_tableView.getSelectionModel().getSelectedIndex();

    if (num < 0) {
        getid = -1;
        gettitre = "";
        return;
    }

    getid = prod.getId();
    gettitre = prod.getTitre();
}

      
    
    @FXML
public void menuRemoveBtn() {
    //System.out.println(gettitre);
    menuSelectOrder();
    if (gettitre == null || gettitre.isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the order you want to remove");
        alert.showAndWait();
    } else {
        String deleteData = "DELETE FROM customer WHERE id = " + getid;

        try {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this order?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                pst = cnx.prepareStatement(deleteData);
                pst.executeUpdate();
            }

            menuShowOrderData();
            menuDisplayTotal();
            gettitre = null; // reset the gettitre variable
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    
    @FXML
    void refresh_panier(ActionEvent event) {
         menuShowOrderData();
        menuDisplayTotal();
    }
    
    @FXML
    void openpanier(MouseEvent event) {
       // panier.setVisible(true);
    }
}  