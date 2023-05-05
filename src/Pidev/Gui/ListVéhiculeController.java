/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import Pidev.Entities.Vehicule;
import Pidev.Gui.OneVehiculeItemController;
import Pidev.Services.VehiculeCRUD;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ListVéhiculeController implements Initializable {

    @FXML
    private Pane content_area;
    @FXML
    private TextField search;
    @FXML
    private GridPane VehiculeListContainer;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Instancier le service de produit
            
            VehiculeCRUD vcd = new VehiculeCRUD();
            List<Vehicule> vehicule = vcd.getvehiculeList();
            // product list ------------------------------------------------
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < vehicule.size(); i++) {
                    
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("OneVehiculeItem.fxml"));
                    HBox OneCard = fxmlLoader.load();
                    OneVehiculeItemController vehiculeCardController = fxmlLoader.getController();
                    vehiculeCardController.setVehiculeData(vehicule.get(i));
                    
                    if (column == 1) {
                        column = 0;
                        ++row;
                    }
                    VehiculeListContainer.add(OneCard, column++, row);
                    // GridPane.setMargin(oneProductCard, new Insets(10));
                    GridPane.setMargin(OneCard, new Insets(0, 10, 25, 10));
                    OneCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ListVéhiculeController.class.getName()).log(Level.SEVERE, null, ex);
}

    }



    @FXML
    private void Rechercher(ActionEvent event) {
        search.textProperty().addListener((observable, oldValue, newValue) -> {
          // Effectuer une recherche Ã  chaque fois que la valeur du champ change
        try {
          Afficher(newValue);
        } catch (SQLException ex) {
            Logger.getLogger(ListConstatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListConstatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    }

    
        private void Afficher(String searchTerm) throws SQLException, IOException {
        // Clear the existing cards
        VehiculeListContainer.getChildren().clear();

        // Create a new VBox layout container
        VBox cardsContainer = new VBox(10);

        // Get all Devis objects from the database
        VehiculeCRUD vcd = new VehiculeCRUD();
        List<Vehicule> vehiculeList = vcd.getvehiculeList();

        // Loop through each Devis object
        boolean foundResults = false;
        for (Vehicule vehicule : vehiculeList) {
            // If the Devis object matches the search term, add it to the filtered VBox
            if (vehicule.getType().toLowerCase().contains(searchTerm.toLowerCase()) || vehicule.getMarque().toLowerCase().contains(searchTerm.toLowerCase())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneVehiculeItem.fxml"));
                HBox OneCard = fxmlLoader.load();
                OneVehiculeItemController vehiculeCardController = fxmlLoader.getController();

                // Set the data for the card
                vehiculeCardController.setVehiculeData(vehicule);

                // Add the card to the filtered VBox
                cardsContainer.getChildren().add(OneCard);
                foundResults = true;

                // Set the margin for the card
                VBox.setMargin(OneCard, new Insets(10, 0, 0, 0));
                OneCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        }

        // Add the cards container to the main container
        VehiculeListContainer.getChildren().add(cardsContainer);
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("C:/Users/msi/Documents/NetBeansProjects/Integration/Vehicule.pdf"));
        document.open();
        
        
                    // Add the header and logo
                    PdfPTable headerTable = new PdfPTable(2);
                    headerTable.setWidthPercentage(100);
                    headerTable.setSpacingAfter(20);

                    // Add the header cell
                  BaseColor color = new BaseColor(0xff, 0x85, 0x21);  
                  PdfPCell headerCell = new PdfPCell(new Paragraph("List Véhicule", new Font(Font.FontFamily.HELVETICA, 36, Font.BOLD, color)));
                  headerCell.setBorder(Rectangle.NO_BORDER);
                  headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                  headerCell.setPadding(10);
                  headerTable.addCell(headerCell);

                    // Add the logo cell
                    Image logo = Image.getInstance("C:\\Users\\msi\\Documents\\NetBeansProjects\\Integration\\src\\Pidev\\assets\\Img\\logo.png");
                    logo.scaleAbsolute(100, 100); // resize the logo if needed
                    PdfPCell logoCell = new PdfPCell(logo, false);
                    logoCell.setBorder(Rectangle.NO_BORDER);

                    logoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    headerTable.addCell(logoCell);

                    document.add(headerTable);

                    // add a line separator
                    LineSeparator line = new LineSeparator();
                    line.setLineColor(BaseColor.LIGHT_GRAY);
                    document.add(line);

           VehiculeCRUD vcd = new VehiculeCRUD();
           ObservableList<Vehicule> liste = vcd.getvehiculeList();

                    // add a line separator
                    line.setLineColor(BaseColor.LIGHT_GRAY);
                    document.add(line);

                    // add a table to the PDF
                    PdfPTable table = new PdfPTable(4);
                    table.setWidthPercentage(100);
                    table.setWidths(new float[]{2, 1, 1, 1});

                    // add table headers
            PdfPCell cell = new PdfPCell(new Phrase("Matricule"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Type"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Marque"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Nombre Cheveaux"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);

                    // add table rows
                    for (Vehicule item : liste) {
                        cell = new PdfPCell(new Phrase(item.getMatricule()));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(item.getType()));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(item.getMarque()));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(item.getNb_ch()));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table.addCell(cell);
                    }
                    
                    document.add(table);
        document.close();
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PDF");
        alert.setHeaderText(null);
        alert.setContentText("Les détails du véhicule ont été exportés dans un fichier PDF.");
        alert.showAndWait();   
    }

    @FXML
    private void Excel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {        
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
                              
            // Créer une nouvelle feuille dans le classeur
            Sheet feuille = (Sheet) workbook.createSheet("Vehicule");
            // Ajouter les en-tÃªtes de colonnes dans la premiére ligne
            Row headerRow = feuille.createRow(0);
            headerRow.createCell(0).setCellValue("Matricule");
            headerRow.createCell(1).setCellValue("Type Véhicule");
            headerRow.createCell(2).setCellValue("Marque Véhicule");
            headerRow.createCell(3).setCellValue("Nombre de cheveaux");

            for(Cell cell : headerRow) {
                headerRow.getCell(0).setCellStyle(style1);
                headerRow.getCell(1).setCellStyle(style2);
                headerRow.getCell(2).setCellStyle(style3);
                headerRow.getCell(3).setCellStyle(styleHeader);
//                cell.setCellStyle(styleHeader);
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
                cell3.setCellStyle(styleHeader);      
                                     
            }     
            // Enregistrer le classeur dans un fichier Excel
            FileOutputStream outputStream = new FileOutputStream("C:/Users/msi/Documents/NetBeansProjects/Integration/Vehicule.xlsx");
            workbook.write(outputStream);
            outputStream.close();
    // Afficher une boÃ®te de dialogue pour informer l'utilisateur que l'exportation est terminée
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Export Excel");
    alert.setHeaderText(null);
    alert.setContentText("Les détails du véhicule ont été exportés dans un fichier Excel.");
    alert.showAndWait();
    }
} 
    
