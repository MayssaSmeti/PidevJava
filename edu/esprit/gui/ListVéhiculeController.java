/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
<<<<<<< HEAD
=======
import com.itextpdf.text.html.simpleparser.HTMLWorker;
>>>>>>> 0326fe5 (api)
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Vehicule;
import edu.esprit.services.VehiculeCRUD;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
<<<<<<< HEAD
=======
import java.io.StringReader;
>>>>>>> 0326fe5 (api)
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
<<<<<<< HEAD
=======
import static java.util.stream.Collectors.toList;
>>>>>>> 0326fe5 (api)
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
<<<<<<< HEAD
=======
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
>>>>>>> 0326fe5 (api)

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ListVéhiculeController implements Initializable {

    @FXML
    private ListView<String> list;
    private ObservableList<String> listItems = FXCollections.observableArrayList();

    Connection cnx2;
    @FXML
    private TextField search;
    @FXML
    private Button btnSearch;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
<<<<<<< HEAD
            try {
            cnx2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");  
            String sql = "SELECT * FROM vehicule";
            Statement statement = cnx2.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String matricule = resultSet.getString("matricule");
                String type = resultSet.getString("type");
                String marque = resultSet.getString("marque");
                String nb_ch = resultSet.getString("nb_ch");
                String listout = matricule +"   \""+ type + "   \"" + marque + "   \"" + nb_ch + "\"";
                list.getItems().add(listout);
            }   
         }
        catch(Exception e){
            e.printStackTrace(); 
        }
    }

    @FXML
    private void Imprimer(ActionEvent event) throws SQLException, DocumentException {
          Document document = new Document();
        try {
            PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\USER\\Documents\\NetBeansProjects\\ConnectionDB\\Vehicule.pdf"));

        document.open();
        VehiculeCRUD vcd = new VehiculeCRUD();
        ObservableList<Vehicule> list = vcd.getvehiculeList();
        for (Vehicule v : list) {
        document.add(new Paragraph("le vehicule :"+v.getId()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("Matricule :" + v.getMatricule()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Type :" + v.getType()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Marque :" + v.getMarque()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Nombre de Cheveaux :" + v.getNb_ch()));
        document.add(new Paragraph("                      "));
       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
=======
            VehiculeCRUD vcd = new VehiculeCRUD();
            List<Vehicule> listOfVehicule = vcd.afficherVehicules();                
            ObservableList<String> items = FXCollections.observableArrayList();

         for(Vehicule v : listOfVehicule){
             items.add(v.getMatricule() + "  \"" + v.getType() + " \"" + v.getMarque() + "  \"" + v.getNb_ch() + "\n");
        }
            list.setItems(items);
        }
    

    @FXML
    private void Imprimer(ActionEvent event) throws SQLException, DocumentException, FileNotFoundException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("C:/Users/USER/Documents/NetBeansProjects/PiDev/Vehicule.pdf"));
        document.open();
        VehiculeCRUD vcd = new VehiculeCRUD();
        ObservableList<Vehicule> liste = vcd.getvehiculeList();
        for (Vehicule v : liste) {
        String html = "<h1 align='center'><font color='#0F056B'>List des Véhicules</font></h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<table border='1' cellpadding='5'>" +
              "<tr bgcolor='#74D0F1'><td><b>Matricule :</b></td><td>" + v.getMatricule() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Type Véhicule :</b></td><td>" + v.getType() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Marque Véhicule :</b></td><td>" + v.getMarque() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Nombre de Cheveaux :</b></td><td>" + v.getNb_ch() + "</td></tr>" +
              "</table><br>";
        HTMLWorker htmlWorker = new HTMLWorker(document); 
        htmlWorker.parse(new StringReader(html));
>>>>>>> 0326fe5 (api)
        }
        document.close();
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PDF");
        alert.setHeaderText(null);
<<<<<<< HEAD
        alert.setContentText("Vehicule Détails Exported in PDF");
        alert.showAndWait();
        }      
        catch(FileNotFoundException ex){
                        Logger.getLogger(VehiculeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
=======
        alert.setContentText("Les détails du véhicule ont été exportés dans un fichier PDF.");
        alert.showAndWait();      
>>>>>>> 0326fe5 (api)
    }

    @FXML
    private void Excel(ActionEvent event) throws IOException, SQLException {
<<<<<<< HEAD
          Writer writer = null;
                VehiculeCRUD vcd = new VehiculeCRUD();
                ObservableList<Vehicule> list = vcd.getvehiculeList();
         try {
            File file = new File("C:\\Users\\USER\\Documents\\NetBeansProjects\\ConnectionDB\\Vehicule.csv");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("ID | Matricule | Type | Marque | Nb_ch \n");
            for (Vehicule v : list) {

                String text =v.getId()+" | "+ v.getMatricule()+" | " +v.getType()+ " | " + v.getMarque()+ " | "+v.getNb_ch()+ "\n";
                System.out.println(text);
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("excel");
            alert.setHeaderText(null);
            alert.setContentText("Vehicule Détails Exported in Excel Sheet");
            alert.showAndWait();
        }
    }

    @FXML
    private void Rechercher(ActionEvent event) {
        VehiculeCRUD vcd = new VehiculeCRUD();
        List<Vehicule> vehicules = vcd.RechercheVehicule(search.getText());
        ObservableList<Vehicule> observableVehicules = FXCollections.observableArrayList(vehicules);
        listItems.setAll(observableVehicules.stream().map(Vehicule::toString).collect(Collectors.toList()));
        list.setItems(listItems);
    }

=======
            VehiculeCRUD vcd = new VehiculeCRUD();
            ObservableList<Vehicule> liste = vcd.getvehiculeList();  
         // Créer une nouvelle feuille dans le classeur
        // Créer un nouveau classeur Excel
             Workbook workbook = new XSSFWorkbook();

             
             CellStyle styleHeader = workbook.createCellStyle();
             styleHeader.setFillForegroundColor(IndexedColors.TAN.getIndex());
             styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
             CellStyle style1 = workbook.createCellStyle();
             style1.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
             style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                         
             CellStyle style2 = workbook.createCellStyle();
             style2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
             style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
             CellStyle style3 = workbook.createCellStyle();
             style3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE1.getIndex());
             style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                              
             CellStyle style4 = workbook.createCellStyle();
             style4.setFillForegroundColor(IndexedColors.LIME.getIndex());
             style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
            // Créer une nouvelle feuille dans le classeur
            Sheet feuille = (Sheet) workbook.createSheet("Vehicule");
            // Ajouter les en-têtes de colonnes dans la première ligne
            Row headerRow = feuille.createRow(0);
            headerRow.createCell(0).setCellValue("Matricule");
            headerRow.createCell(1).setCellValue("Type Véhicule");
            headerRow.createCell(2).setCellValue("Marque Véhicule");
            headerRow.createCell(3).setCellValue("Nombre de cheveaux");

            for(Cell cell : headerRow) {
                cell.setCellStyle(styleHeader);
             }
            // Ajouter les données dans les cellules correspondantes
            int rowIndex = 1;
            for (Vehicule v : liste) {
                Row row = feuille.createRow(rowIndex++);
                feuille.setColumnWidth(0, 20 * 256);
                feuille.setColumnWidth(1, 20 * 256);
                feuille.setColumnWidth(2, 20 * 256);
                feuille.setColumnWidth(3, 20 * 256);
     
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(v.getMatricule());
                cell0.setCellStyle(style1);
                
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(v.getType());
                cell1.setCellStyle(style2);
                
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(v.getMarque());
                cell2.setCellStyle(style3); 
                
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(v.getNb_ch());
                cell3.setCellStyle(style4);      
                                     
            }     
            // Enregistrer le classeur dans un fichier Excel
            FileOutputStream outputStream = new FileOutputStream("C:/Users/USER/Documents/NetBeansProjects/PiDev/Vehicule.xlsx");
            workbook.write(outputStream);
            outputStream.close();
    // Afficher une boîte de dialogue pour informer l'utilisateur que l'exportation est terminée
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Export Excel");
    alert.setHeaderText(null);
    alert.setContentText("Les détails du véhicule ont été exportés dans un fichier Excel.");
    alert.showAndWait();
    }

 
      @FXML
        private void Rechercher(ActionEvent event) {
          search.textProperty().addListener((observable, oldValue, newValue) -> {
          // Effectuer une recherche à chaque fois que la valeur du champ change
         VehiculeCRUD vehicule = new VehiculeCRUD();
         List<Vehicule> vehicules = vehicule.afficherVehicules();
         List<Vehicule> chercher = vehicules.stream().filter(re -> re.getMarque().startsWith(newValue)).collect(Collectors.toList());
         Afficher(chercher);
    });
        }
    
    
    private void Afficher(List<Vehicule> vehicules) {
     //Ajout des véhicules dans la liste observable
    ObservableList<String> items = FXCollections.observableArrayList();
    for (Vehicule v: vehicules) {
        items.add(v.getMatricule() + "  \"" + v.getType() + " \"" + v.getMarque() + "  \"" + v.getNb_ch() + "\n");
    }
    list.setItems(items);
    }

    
>>>>>>> 0326fe5 (api)
}