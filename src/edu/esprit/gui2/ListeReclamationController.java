/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.Ireclamations;
import edu.esprit.services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ListeReclamationController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private TextField tfRechercher;
    @FXML
    private Button btnre;

   

    /**
     * Initializes the controller class.
     */
    @Override    
    public void initialize(URL url, ResourceBundle rb) {
    // Récupérer toutes les réclamations depuis la base de données
    ReclamationCRUD reclamations = new ReclamationCRUD();
    List<Reclamation> rec = reclamations.afficherReclamations();

    // Parcourir la liste de réclamations et ajouter chaque réclamation dans la GridPane
    int row = 0;
    for (Reclamation r : rec) {
        // Créer les labels pour afficher les informations de la réclamation
        Label labelObjet = new Label(r.getObjet());
        Label labelDescription = new Label(r.getDescription());
        Label labelNote = new Label(Integer.toString(r.getNote()));

        // Ajouter les labels dans la GridPane
        gridPane.add(labelObjet, 0, row);
        gridPane.add(labelDescription, 1, row);
        gridPane.add(labelNote, 2, row);

        // Ajouter un événement de clic pour chaque réclamation
        gridPane.getRowConstraints().add(new RowConstraints(30)); // ajuster la hauteur de la ligne
        gridPane.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Ouvrir la fenêtre de modification pour la réclamation sélectionnée
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamation.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    ModifierReclamationController controller = loader.getController();
                    controller.setReclamation(r);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        row++;
    }      
}
    



    @FXML
    private void rechercherReclamation(ActionEvent event) {

         // Récupérer le texte saisi dans le champ de recherche
    String rechercher = tfRechercher.getText();

    // Créer un prédicat pour filtrer les réclamations
    Predicate<Reclamation> predicate = reclam -> {
        String objet = reclam.getObjet();
        String description = reclam.getDescription();
        return objet.contains(rechercher) || description.contains(rechercher);
    };
    };

    @FXML
    private void statique(ActionEvent event) {
         // Créer des données
    ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
            new PieChart.Data("Réclamation traitée", 30),
            new PieChart.Data("Réclamation en cours", 20),
            new PieChart.Data("Réclamation non traitée", 10));

    // Créer le PieChart
    final PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Statistiques des réclamations");

    // Créer la scène
    VBox root = new VBox();
    root.getChildren().add(chart);
    Scene scene = new Scene(root, 800, 600);

    // Créer une nouvelle fenêtre pour afficher le graphique
    Stage newStage = new Stage();
    newStage.setScene(scene);
    newStage.show();
    }

    @FXML
    private void enregistrer(ActionEvent event) {
  
    CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Réclamations enregistrées");
        xAxis.setLabel("Date");
        yAxis.setLabel("Nombre de réclamations");

        // Création des séries de données
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Réclamations");
        List<XYChart.Data<String, Number>> data = Arrays.asList(
                new XYChart.Data<>("01/01/2023", 10),
                new XYChart.Data<>("01/02/2023", 15),
                new XYChart.Data<>("01/03/2023", 25),
                new XYChart.Data<>("01/04/2023", 20),
                new XYChart.Data<>("01/05/2023", 18),
                new XYChart.Data<>("01/06/2023", 30)
        );
        series1.getData().addAll(data);

        // Ajout des séries de données au LineChart
        lineChart.getData().addAll(series1);

        Scene scene = new Scene(lineChart, 800, 600);
         // Créer une nouvelle fenêtre pour afficher le graphique
    Stage newStage = new Stage();
    newStage.setScene(scene);
    newStage.show();

    }

   
  
}

  


 





