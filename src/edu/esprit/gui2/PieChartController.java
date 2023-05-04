/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import edu.esprit.entities.Reclamation;
import edu.esprit.services.ReclamationCRUD;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class PieChartController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private PieChart PieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ReclamationCRUD reclamations = new ReclamationCRUD();
        List<Reclamation> reclamationsList = reclamations.afficherReclamations();

        Map<String, Long> counts = reclamationsList.stream()
                .collect(Collectors.groupingBy(e -> e.getObjet().toString(), Collectors.counting()));

        int totalCount = reclamationsList.size();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            String etat = entry.getKey();
            long count = entry.getValue();
            double percentage = count * 100.0 / reclamationsList.size();
            pieChartData.add(new PieChart.Data(etat, percentage));
        }

       PieChart.setAnimated(true);
       PieChart.setData(pieChartData);

        // afficher le nombre total de réclamations 
        label.setText("" + totalCount);
    }    
    
}
