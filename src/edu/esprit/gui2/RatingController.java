/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class RatingController implements Initializable {

    

    private int ratingValue = 0;
    @FXML
    private ImageView rating1;
    @FXML
    private ImageView rating2;
    @FXML
    private ImageView rating3;
    @FXML
    private ImageView rating4;
    @FXML
    private ImageView rating5;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Ajouter un écouteur d'événements de clic à chaque image
        rating1.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating1.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
        rating2.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating2.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
        rating3.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating3.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
        rating4.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating4.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
        rating5.setOnMouseClicked((MouseEvent event) -> {
            incrementerNote();
            rating5.setImage(new Image(getClass().getResource("/edu/esprit/img/etoile.png").toString()));
        });
    }


    private void incrementerNote() {
        // Vérifier si la note est inférieure à 5 avant de l'incrémenter
        if (ratingValue < 5) {
            ratingValue++;
        }
    }

    @FXML
    private void evaluer(ActionEvent event) {
        // Afficher la valeur de la note dans la console
        System.out.println("La note est: " + ratingValue);
    }

  
    
}
