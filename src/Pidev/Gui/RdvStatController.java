/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Rendezvous;
import Pidev.Services.ServiceRendezvous;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class RdvStatController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
ServiceRendezvous rendezvousService = new ServiceRendezvous();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
 populateChart();
    }

  private void populateChart() {
      List<Rendezvous> rendezvousList = rendezvousService.getAll();
      Map<String, XYChart.Series<String, Integer>> seriesMap = new HashMap<>();
      for (Rendezvous r : rendezvousList) {
          String lieu = r.getLieu();
          if (!seriesMap.containsKey(lieu)) {
              XYChart.Series<String, Integer> series = new XYChart.Series<>();
              series.setName(lieu);
              series.getData().add(new XYChart.Data<>(lieu, 1));
              seriesMap.put(lieu, series);
          } else {
              XYChart.Series<String, Integer> series = seriesMap.get(lieu);
              int count = series.getData().get(0).getYValue() + 1;
              series.getData().set(0, new XYChart.Data<>(lieu, count));
          }
      }
      for (XYChart.Series<String, Integer> series : seriesMap.values()) {
          chart.getData().add(series);
      }
}
  }

// TODO
        
    

