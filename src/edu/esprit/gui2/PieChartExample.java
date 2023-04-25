import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PieChartExample extends Application {
 
    @Override public void start(Stage stage) {
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
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
