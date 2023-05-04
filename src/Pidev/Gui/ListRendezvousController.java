/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Rapport;
import Pidev.Entities.Rendezvous;
import Pidev.Services.ServiceRapport;
import Pidev.Services.ServiceRendezvous;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ListRendezvousController implements Initializable {

    @FXML
    private Pane content_area;
    @FXML
    private GridPane offreListContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceRendezvous rendezvouService = new ServiceRendezvous();


             List<Rendezvous> rdv= rendezvouService.getAll1();
            
   
      

        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < rdv.size() ; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneRendezvousListCard.fxml"));
                HBox OneOffreCard = fxmlLoader.load();
               OneRdvListCardController rendezvousController = fxmlLoader.getController();
                rendezvousController.setRdvData(rdv.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                offreListContainer.add(OneOffreCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(OneOffreCard, new Insets(0, 10, 25, 10));
                OneOffreCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }    
    

