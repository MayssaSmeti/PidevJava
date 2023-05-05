/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Pidev.Gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.chart.BarChart;

import Pidev.Entities.Offre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Pidev.Services.IOffreService;
import Pidev.Services.OffreService;
import Pidev.Utilis.MyConnection;
//import static com.mysql.cj.util.SaslPrep.prepare;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class StatController implements Initializable {

    @FXML
    private Text OffreDescription;

    @FXML
    private Text OffreName;

    @FXML
    private AnchorPane card_form;

    @FXML
    private ImageView img;

    @FXML
    private HBox panierOffre;

    @FXML
    private HBox priceHbox;

    @FXML
    private Text priceOffre;

    @FXML
    private Label validiteOffre;

    private Offre offre;
    
      @FXML
    private BarChart<?, ?> barChart;
     private PreparedStatement pst;
      Connection cnx = MyConnection.getInstance().getCnx();

        public void initialize(URL url, ResourceBundle rb) {
        try {
            dashboardCustomerChart();
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        public void dashboardCustomerChart() throws SQLException {
    barChart.getData().clear();
    
    String sql = "SELECT YEAR(validitedu), COUNT(id) FROM contrat GROUP BY YEAR(validitedu) ORDER BY TIMESTAMP(validitedu)";
    
    XYChart.Series chart = new XYChart.Series();
    try {
        pst = cnx.prepareStatement(sql);;
        ResultSet result = pst.executeQuery();
        
        while (result.next()) {
            chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
        }
        
        barChart.getData().add(chart);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

        @FXML
    void liste(MouseEvent event) {
//Parent fxml = FXMLLoader.load(getClass().getResource("/Pidev/Gui/ListeContrat.fxml"));
  //      content_area.getChildren().removeAll();
    //    content_area.getChildren().setAll(fxml);
    }
        
    }    
    

