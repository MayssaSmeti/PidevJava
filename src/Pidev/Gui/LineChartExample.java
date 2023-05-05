package edu.esprit.gui2;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartExample extends Application {

    @Override
    public void start(Stage primaryStage) {
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
                new XYChart.Data<>("01/01/2022", 10),
                new XYChart.Data<>("01/02/2022", 15),
                new XYChart.Data<>("01/03/2022", 25),
                new XYChart.Data<>("01/04/2022", 20),
                new XYChart.Data<>("01/05/2022", 18),
                new XYChart.Data<>("01/06/2022", 30)
        );
        series1.getData().addAll(data);

        // Ajout des séries de données au LineChart
        lineChart.getData().addAll(series1);

        Scene scene = new Scene(lineChart, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
