/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Services.ReponseCRUD;
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
public class PieReponseController implements Initializable {

    @FXML
    private PieChart PieRe;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
         ReponseCRUD reponses = new ReponseCRUD();
        List<Pidev.Entities.Reponse> reponsesList = reponses.afficherReponses();

        Map<String, Long> counts = reponsesList.stream()
                .collect(Collectors.groupingBy(e -> e.getDescription().toString(), Collectors.counting()));

        int totalCount = reponsesList.size();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            String etat = entry.getKey();
            long count = entry.getValue();
            double percentage = count * 100.0 / reponsesList.size();
            pieChartData.add(new PieChart.Data(etat, percentage));
        }

       PieRe.setAnimated(true);
       PieRe.setData(pieChartData);

        // afficher le nombre total de réclamations 
        label.setText("" + totalCount);
    }    
    
}
