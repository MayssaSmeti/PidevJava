/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Rapport;
import Pidev.Services.ServiceRapport;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author rayen
 */



public class AfficherRapportController implements Initializable {

    @FXML
    private TableView<Rapport> tableRapport;
    @FXML
    private TableColumn<Rapport,Integer> idColomun;
    @FXML
    private TableColumn<Rapport, String> descriptionColomun;
    @FXML
    private TableColumn<Rapport, String> rapportpreColomun;
    @FXML
    private TableColumn<Rapport, String> RapportexColomun;
    @FXML
    private TableColumn<Rapport, String> imageColomun;
    @FXML
    private Button deleteBtn;
    
 ServiceRapport rapportService = new ServiceRapport();

final private ObservableList<Rapport> rapportList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Rapport, String> actionColomun;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          // Set up the table columns
       idColomun.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColomun.setCellValueFactory(new PropertyValueFactory<>("description"));
        rapportpreColomun.setCellValueFactory(new PropertyValueFactory<>("rapportpreliminaire"));
        RapportexColomun.setCellValueFactory(new PropertyValueFactory<>("rapportexpertise"));
        imageColomun.setCellValueFactory(new PropertyValueFactory<>("image"));

        // Load data from the database
     List<Rapport> rapports = rapportService.getAll();
        rapportList.addAll(rapports);

        // Bind the table to the observable list
        tableRapport.setItems(rapportList);
         actionColomun.setCellFactory(col -> new TableCell<Rapport, String>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(e -> {
                    Rapport rapport = getTableView().getItems().get(getIndex());

                    // Open a new window to edit the selected Rendezvous object
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditRapport.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent) fxmlLoader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherRapportController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    EditRapportController controller = fxmlLoader.<EditRapportController>getController();
                    controller.initData(rapport);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                    // Refresh the table view after the editing is done
                    rapportList.clear();
                    rapportList.addAll(rapportService.getAll());
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editButton);
            }
            // Load data from the database

         
         }
                 
         );// TODO
     
        
        
        
        
        
        

        // Bind the table to the observable list
        tableRapport.setItems(rapportList); 
        
        
        
        
        
        
        
        
     };
   
     
     


 @FXML
    private void deleteRapport(ActionEvent event) {
          Rapport selectedRapport = tableRapport.getSelectionModel().getSelectedItem();
        if (selectedRapport != null) {
            rapportService.delete(selectedRapport);
            rapportList.remove(selectedRapport);
        }
    }
}

      
              
              
