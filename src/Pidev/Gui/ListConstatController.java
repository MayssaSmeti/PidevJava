/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import com.itextpdf.text.BadElementException;
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
import Pidev.Entities.Constat;
import Pidev.Services.ConstatCRUD;
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
public class ListConstatController implements Initializable {

    @FXML
    private Pane content_area;
    
    @FXML
    private GridPane constatListContainer;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Instancier le service de produit
            ConstatCRUD ccd = new ConstatCRUD();
            List<Constat> constats = ccd.getconstatList();
            // list ------------------------------------------------
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < constats.size() ; i++) {
                    
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("OneConstatItem.fxml"));
                    HBox OneConstatCard = fxmlLoader.load();
                    OneConstatItemController constatCardController = fxmlLoader.getController();
                    constatCardController.setConstatData(constats.get(i));
                    
                    if (column == 1) {
                        column = 0;
                        ++row;
                    }
                    constatListContainer.add(OneConstatCard, column++, row);
                    GridPane.setMargin(OneConstatCard, new Insets(0, 10, 25, 10));
                    OneConstatCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListConstatController.class.getName()).log(Level.SEVERE, null, ex);
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
        constatListContainer.getChildren().clear();
        
        // Create a new VBox layout container
        VBox cardsContainer = new VBox(10);

        // Get all Devis objects from the database
        ConstatCRUD ccd = new ConstatCRUD();
        List<Constat> constatList = ccd.getconstatList();

        // Loop through each Devis object
        boolean foundResults = false;
        for (Constat constat : constatList) {
            // If the Devis object matches the search term, add it to the filtered VBox
            if (constat.getNomclient_e().toLowerCase().contains(searchTerm.toLowerCase()) || constat.getPrenomclient_e().toLowerCase().contains(searchTerm.toLowerCase())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneConstatItem.fxml"));
                HBox OneCard = fxmlLoader.load();
                OneConstatItemController constatCardController = fxmlLoader.getController();

                // Set the data for the card
                constatCardController.setConstatData(constat);

                // Add the card to the filtered VBox
                cardsContainer.getChildren().add(OneCard);
                foundResults = true;

                // Set the margin for the card
                 VBox.setMargin(OneCard, new Insets(10, 0, 0, 0));
                 OneCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        }

        // Add the cards container to the main container
           constatListContainer.getChildren().add(cardsContainer);

    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException, BadElementException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("C:/Users/msi/Documents/NetBeansProjects/Integration/Constat.pdf"));
        document.open();
        ConstatCRUD ccd = new ConstatCRUD();
        ObservableList<Constat> liste = ccd.getconstatList();
                       // Add the header and logo
                    PdfPTable headerTable = new PdfPTable(2);
                    headerTable.setWidthPercentage(100);
                    headerTable.setSpacingAfter(20);

                    // Add the header cell
                  BaseColor color = new BaseColor(0xff, 0x85, 0x21);  
                  PdfPCell headerCell = new PdfPCell(new Paragraph("List Constat", new Font(Font.FontFamily.HELVETICA, 36, Font.BOLD, color)));
                  headerCell.setBorder(Rectangle.NO_BORDER);
                  headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                  headerCell.setPadding(10);
                  headerTable.addCell(headerCell);

                    // Add the logo cell
                    Image logo = Image.getInstance("C:/Users/msi/Documents/NetBeansProjects/Integration/src/Pidev/assets/Img/logo.png");
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

                    // add a line separator
                    line.setLineColor(BaseColor.LIGHT_GRAY);
                    document.add(line);

                    // add a table to the PDF
                    PdfPTable table = new PdfPTable(6);
                    table.setWidthPercentage(100);
                    table.setWidths(new float[]{5, 6, 5, 6, 6, 6});

                    // add table headers
            PdfPCell cell = new PdfPCell(new Phrase("Nom"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);
                 
                    cell = new PdfPCell(new Phrase("Emplacement Accident"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);
                    
                    
                    
                    cell = new PdfPCell(new Phrase("Photo Accident"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);
                    
                    
                    cell = new PdfPCell(new Phrase("Numéro Contrat"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);
                    
                    
                          cell = new PdfPCell(new Phrase("Mail"));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);
                    
                          cell = new PdfPCell(new Phrase("Date "));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderColor(BaseColor.DARK_GRAY);
                    cell.setPadding(8);
                    table.addCell(cell);
                    
                    // add table rows
                    for (Constat item : liste) {
                        cell = new PdfPCell(new Phrase(item.getNomclient_e()+" "+item.getPrenomclient_e()));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase(item.getEmplacementaccid()));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table.addCell(cell);
                        
                        Image image = Image.getInstance("src/Pidev/assets/img/"+item.getPhotoaccid());
                        image.scaleAbsolute(70, 70);
                        PdfPCell imageCell = new PdfPCell(image, false);
                        image.setAlignment(Element.ALIGN_CENTER);
                        table.addCell(imageCell);
                        
                        cell = new PdfPCell(new Phrase(item.getNumcontrat_e()));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table.addCell(cell);
                        
                        
                        cell = new PdfPCell(new Phrase(item.getMail()));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setBorderColor(BaseColor.LIGHT_GRAY);
                        cell.setPadding(8);
                        table.addCell(cell);
                        
                        
                        cell = new PdfPCell(new Phrase(item.getDate_creation()));
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
        alert.setContentText("Les détails du constat ont été exportés dans un fichier PDF.");
        alert.showAndWait();      
    }

    @FXML
    private void Excel(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
          ConstatCRUD ccd = new ConstatCRUD();
          ObservableList<Constat> liste = ccd.getconstatList();  
         // Créer une nouvelle feuille dans le classeur
        // Créer un nouveau classeur Excel
             Workbook workbook = new XSSFWorkbook();
             CellStyle style = workbook.createCellStyle();
             style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
             style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
             CellStyle styleHeader = workbook.createCellStyle();
             styleHeader.setFillForegroundColor(IndexedColors.TAN.getIndex());
             styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
             CellStyle style1 = workbook.createCellStyle();
             style1.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
             style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
             CellStyle style2 = workbook.createCellStyle();
             style2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
             style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
             CellStyle style3 = workbook.createCellStyle();
             style3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE1.getIndex());
             style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                              
             CellStyle style4 = workbook.createCellStyle();
             style4.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
             style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
            // CrÃ©er une nouvelle feuille dans le classeur
            Sheet feuille = (Sheet) workbook.createSheet("Constat");
            // Ajouter les en-tÃªtes de colonnes dans la premiére ligne
            Row headerRow = feuille.createRow(0);
            headerRow.createCell(0).setCellValue("Nom Client");
            headerRow.createCell(1).setCellValue("Prénom Client");
            headerRow.createCell(2).setCellValue("Type");
            headerRow.createCell(3).setCellValue("Marque");
            headerRow.createCell(4).setCellValue("Assurance");
            headerRow.createCell(5).setCellValue("Adresse");
            headerRow.createCell(6).setCellValue("Emplacement Accident");
            headerRow.createCell(7).setCellValue("Photo Accident");
            headerRow.createCell(8).setCellValue("Description Dégat");
            headerRow.createCell(9).setCellValue("Observations");
            headerRow.createCell(10).setCellValue("Numéro Contrat");
            headerRow.createCell(11).setCellValue("Mail");
            headerRow.createCell(12).setCellValue("Date Création");
            for(Cell cell : headerRow) {
                headerRow.getCell(0).setCellStyle(style);
                headerRow.getCell(1).setCellStyle(style);
                headerRow.getCell(2).setCellStyle(style1);
                headerRow.getCell(3).setCellStyle(style1);
                headerRow.getCell(4).setCellStyle(style2);
                headerRow.getCell(5).setCellStyle(style2);
                headerRow.getCell(6).setCellStyle(style2);
                headerRow.getCell(7).setCellStyle(style4);
                headerRow.getCell(8).setCellStyle(style4);
                headerRow.getCell(9).setCellStyle(style4);
                headerRow.getCell(10).setCellStyle(style3);
                headerRow.getCell(11).setCellStyle(style3);
                headerRow.getCell(12).setCellStyle(style3);
//                cell.setCellStyle(styleHeader);
             }
            // Ajouter les données dans les cellules correspondantes
            int rowIndex = 1;
            for (Constat c : liste) {
                Row row = feuille.createRow(rowIndex++);
                feuille.setColumnWidth(0, 20 * 256);
                feuille.setColumnWidth(1, 20 * 256);
                feuille.setColumnWidth(2, 20 * 256);
                feuille.setColumnWidth(3, 20 * 256);
                feuille.setColumnWidth(4, 20 * 256);
                feuille.setColumnWidth(5, 20 * 256);
                feuille.setColumnWidth(6, 40 * 256);
                feuille.setColumnWidth(7, 40 * 256);
                feuille.setColumnWidth(8, 40 * 256);
                feuille.setColumnWidth(9, 40 * 256);
                feuille.setColumnWidth(10, 40 * 256);
                feuille.setColumnWidth(11, 40 * 256);
                feuille.setColumnWidth(12, 40 * 256);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(c.getNomclient_e());
                cell0.setCellStyle(style);
                
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(c.getPrenomclient_e());
                cell1.setCellStyle(style);
                
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(c.getTypevehicule_e());
                cell2.setCellStyle(style1); 
                
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(c.getMarquevehicule_e());
                cell3.setCellStyle(style1);      
                
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(c.getAssuranceclient_e());
                cell4.setCellStyle(style2);      
                
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(c.getAdresseclient_e());
                cell5.setCellStyle(style2); 
                
                Cell cell6 = row.createCell(6);
                cell6.setCellValue(c.getEmplacementaccid());
                cell6.setCellStyle(style2);  
                
                Cell cell7 = row.createCell(7);
                cell7.setCellValue(c.getPhotoaccid());
                cell7.setCellStyle(style4); 
                
                Cell cell8 = row.createCell(8);
                cell8.setCellValue(c.getDescriptiondegat());
                cell8.setCellStyle(style4); 
                
                Cell cell9 = row.createCell(9);
                cell9.setCellValue(c.getObservations());
                cell9.setCellStyle(style4);  
                
                Cell cell10 = row.createCell(10);
                cell10.setCellValue(c.getNumcontrat_e());
                cell10.setCellStyle(style3);  
                
                Cell cell11 = row.createCell(11);
                cell11.setCellValue(c.getMail());
                cell11.setCellStyle(style3); 
                
                Cell cell12 = row.createCell(12);
                cell12.setCellValue(c.getDate_creation());
                cell12.setCellStyle(style3); 
            }       // Enregistrer le classeur dans un fichier Excel
        FileOutputStream outputStream = new FileOutputStream("C:/Users/msi/Documents/NetBeansProjects/Integration/Constat.xlsx");
            workbook.write(outputStream);
            outputStream.close();
    // Afficher une boÃ®te de dialogue pour informer l'utilisateur que l'exportation est terminée
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Export Excel");
    alert.setHeaderText(null);
    alert.setContentText("Les détails du constat ont été exportés dans un fichier Excel.");
    alert.showAndWait();
    }


}    
    
    