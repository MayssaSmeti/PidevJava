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
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Constat;
import edu.esprit.services.ConstatCRUD;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
=======
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Constat;
import edu.esprit.services.ConstatCRUD;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
>>>>>>> 0326fe5 (api)
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
<<<<<<< HEAD
=======
import javafx.scene.control.TextField;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


>>>>>>> 0326fe5 (api)

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ListConstatController implements Initializable {

    @FXML
<<<<<<< HEAD
    private ListView<String> list = new ListView<String>();
    Connection cnx2;
    @FXML
    private Button btnPDF;
=======
    private ListView<String> list;
    Connection cnx2;
    @FXML
    private Button btnPDF;
    @FXML
    private Button btnExcel;
    @FXML
    private TextField search; 
>>>>>>> 0326fe5 (api)

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
<<<<<<< HEAD
        try {
            cnx2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");  
            String sql = "SELECT * FROM constat";
            Statement statement = cnx2.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String nomclient_e = resultSet.getString("nomclient_e");
                String prenomclient_e = resultSet.getString("prenomclient_e");
                String adresseclient_e = resultSet.getString("adresseclient_e");
                String numcontrat_e = resultSet.getString("numcontrat_e");
                String mail = resultSet.getString("mail");
                String listout = nomclient_e + prenomclient_e + "   \"" + adresseclient_e + "   \"" + numcontrat_e + "   \"" + mail + "\"";
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
            PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\USER\\Documents\\NetBeansProjects\\ConnectionDB\\Constat.pdf"));

=======
            ConstatCRUD ccd = new ConstatCRUD();
            List<Constat> listOfConstat = ccd.afficherConstats();                
            ObservableList<String> items = FXCollections.observableArrayList();

         for(Constat c : listOfConstat){
             items.add(c.getNomclient_e()+ c.getPrenomclient_e() + "  \"" + c.getAdresseclient_e() + " \"" + c.getNumcontrat_e() + "  \"" + c.getMail() + "  \"" + c.getDate_creation());
        }
            list.setItems(items);
        }
    @FXML
    private void Imprimer(ActionEvent event) throws SQLException, DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("C:/Users/USER/Documents/NetBeansProjects/PiDev/Constat.pdf"));
>>>>>>> 0326fe5 (api)
        document.open();
        ConstatCRUD ccd = new ConstatCRUD();
        ObservableList<Constat> liste = ccd.getconstatList();
        for (Constat c : liste) {
<<<<<<< HEAD
        document.add(new Paragraph("le constat :"+c.getId()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("Nom Client :" + c.getNomclient_e()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Prénom Client :" + c.getPrenomclient_e()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Type Véhicule :" + c.getTypevehicule_e()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Marque Véhicule :" + c.getMarquevehicule_e()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Assurance :" + c.getAssuranceclient_e()));
        document.add(new Paragraph("                      ")); 
        document.add(new Paragraph("Adresse :" + c.getAdresseclient_e()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Emplacement Accident :" + c.getEmplacementaccid()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Photo Accident :" + c.getPhotoaccid()));
        document.add(new Paragraph("                      ")); 
        document.add(new Paragraph("Description Degat :" + c.getDescriptiondegat()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Observations :" + c.getObservations()));
        document.add(new Paragraph("                      ")); 
        document.add(new Paragraph("Numéro Contrat :" + c.getNumcontrat_e()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Mail :" + c.getMail()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Date Création :" + c.getDate_creation()));
        document.add(new Paragraph("                      "));
       
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
=======
        String html = "<h1 align='center'><font color='#0F056B'>Informations Client</font></h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<table border='1' cellpadding='5'>" +
              "<tr bgcolor='#74D0F1'><td><b>Nom Client :</b></td><td>" + c.getNomclient_e() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Prénom Client :</b></td><td>" + c.getPrenomclient_e() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Type Véhicule :</b></td><td>" + c.getTypevehicule_e() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Marque Véhicule :</b></td><td>" + c.getMarquevehicule_e() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Assurance :</b></td><td>" + c.getAssuranceclient_e() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Adresse :</b></td><td>" + c.getAdresseclient_e() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Emplacement Accident :</b></td><td>" + c.getEmplacementaccid() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Photo Accident :</b></td><td>" + c.getPhotoaccid() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Description Degat :</b></td><td>" + c.getDescriptiondegat() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Observations :</b></td><td>" + c.getObservations() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Numéro Contrat :</b></td><td>" + c.getNumcontrat_e() + "</td></tr>" +
              "<tr bgcolor='#A9EAFE'><td><b>Mail :</b></td><td>" + c.getMail() + "</td></tr>" +
              "<tr bgcolor='#74D0F1'><td><b>Date Création :</b></td><td>" + c.getDate_creation() + "</td></tr>" +
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
        alert.setContentText("Constat Détails Exported in PDF");
        alert.showAndWait();
        }      
        catch(FileNotFoundException ex){
                        Logger.getLogger(ConstatCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void Excel(ActionEvent event) throws SQLException, IOException {
               Writer writer = null;
               ConstatCRUD ccd = new ConstatCRUD();
               ObservableList<Constat> liste = ccd.getconstatList();
         try {
            File file = new File("C:\\Users\\USER\\Documents\\NetBeansProjects\\ConnectionDB\\Constat.csv");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("ID | Nom Client | Prénom Client | Type | Marque | Assurance | Adresse | Emplacement Accident | Photo Accident | Description Degat | Observations | Numéro Contrat | Id Expert | Id Véhicule | Mail | Date Création\n");
            for (Constat c : liste) {

                String text =c.getId()+" | "+ c.getNomclient_e()+" | " +c.getPrenomclient_e()+ " | " + c.getTypevehicule_e() + " | "+ c.getMarquevehicule_e() + " | "+ c.getAssuranceclient_e()
                        + " | "+ c.getAdresseclient_e() +  " | "+ c.getEmplacementaccid() +  " | "+ c.getPhotoaccid() +  " | "+ c.getDescriptiondegat() +  " | "+  c.getObservations() +  " | "+ c.getNumcontrat_e() + 
                         " | "+ c.getExpert() + " | "+ c.getVehicule() + " | "+ c.getMail() + " | "+ c.getDate_creation()+"\n";
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
            alert.setContentText("Constat Détails Exported in Excel Sheet");
            alert.showAndWait();
        }
    
/*private void Excel(ActionEvent event) throws SQLException, IOException {
    Writer writer = null;
    ConstatCRUD ccd = new ConstatCRUD();
    ObservableList<Constat> liste = ccd.getconstatList();
    try {
        File file = new File("C:\\Users\\USER\\Documents\\NetBeansProjects\\ConnectionDB\\Constat.xlsx");
        FileOutputStream outputStream = new FileOutputStream(file);

        // Create a workbook and sheet
        Workbook workbook = new Workbook();
        Sheet sheet = workbook.createSheet("Constat");

        // Create a font with customized color
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());

        // Create a cell style with customized background and font
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(font);

        // Create a header row with customized style
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Nom Client", "Prénom Client", "Type", "Marque", "Assurance", "Adresse", "Emplacement Accident", "Photo Accident", "Description Degat", "Observations", "Numéro Contrat", "Id Expert", "Id Véhicule", "Mail", "Date Création"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(cellStyle);
        }

        // Create data rows
        int rowNumber = 1;
        for (Constat c : liste) {
            Row row = sheet.createRow(rowNumber++);

            row.createCell(0).setCellValue(c.getId());
            row.createCell(1).setCellValue(c.getNomclient_e());
            row.createCell(2).setCellValue(c.getPrenomclient_e());
            row.createCell(3).setCellValue(c.getTypevehicule_e());
            row.createCell(4).setCellValue(c.getMarquevehicule_e());
            row.createCell(5).setCellValue(c.getAssuranceclient_e());
            row.createCell(6).setCellValue(c.getAdresseclient_e());
            row.createCell(7).setCellValue(c.getEmplacementaccid());
            row.createCell(8).setCellValue(c.getPhotoaccid());
            row.createCell(9).setCellValue(c.getDescriptiondegat());
            row.createCell(10).setCellValue(c.getObservations());
            row.createCell(11).setCellValue(c.getNumcontrat_e());
            row.createCell(12).setCellValue(c.getExpert());
            row.createCell(13).setCellValue(c.getVehicule());
            row.createCell(14).setCellValue(c.getMail());
            row.createCell(15).setCellValue(c.getDate_creation());
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("excel");
        alert.setHeaderText(null);
        alert.setContentText("Constat Détails Exported in Excel Sheet");
        alert.showAndWait();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}*/

    }
=======
        alert.setContentText("Les détails du constat ont été exportés dans un fichier PDF.");
        alert.showAndWait();      
}     
    
    @FXML
        private void Excel(ActionEvent event) throws SQLException, IOException {
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
             style4.setFillForegroundColor(IndexedColors.LIME.getIndex());
             style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             
            // Créer une nouvelle feuille dans le classeur
            Sheet feuille = (Sheet) workbook.createSheet("Constat");
            // Ajouter les en-têtes de colonnes dans la première ligne
            Row headerRow = feuille.createRow(0);
            headerRow.createCell(0).setCellValue("Nom Client");
            headerRow.createCell(1).setCellValue("Prénom Client");
            headerRow.createCell(2).setCellValue("Type");
            headerRow.createCell(3).setCellValue("Marque");
            headerRow.createCell(4).setCellValue("Assurance");
            headerRow.createCell(5).setCellValue("Adresse");
            headerRow.createCell(6).setCellValue("Emplacement Accident");
            headerRow.createCell(7).setCellValue("Photo Accident");
            headerRow.createCell(8).setCellValue("Description Dégât");
            headerRow.createCell(9).setCellValue("Observations");
            headerRow.createCell(10).setCellValue("Numéro Contrat");
            headerRow.createCell(11).setCellValue("Mail");
            headerRow.createCell(12).setCellValue("Date Création");
            for(Cell cell : headerRow) {
                cell.setCellStyle(styleHeader);
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
        FileOutputStream outputStream = new FileOutputStream("C:/Users/USER/Documents/NetBeansProjects/PiDev/Constat.xlsx");
            workbook.write(outputStream);
            outputStream.close();
    // Afficher une boîte de dialogue pour informer l'utilisateur que l'exportation est terminée
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Export Excel");
    alert.setHeaderText(null);
    alert.setContentText("Les détails du constat ont été exportés dans un fichier Excel.");
    alert.showAndWait();
}

    
        
@FXML
    private void Rechercher(ActionEvent event) {
    search.textProperty().addListener((observable, oldValue, newValue) -> {
          // Effectuer une recherche à chaque fois que la valeur du champ change
         ConstatCRUD constat = new ConstatCRUD(); // créer une instance de la classe ConstatCRUD
         List<Constat> constats = constat.afficherConstats();
         List<Constat> chercher = constats.stream().filter(re -> re.getNomclient_e().startsWith(newValue)).collect(Collectors.toList());
         Afficher(chercher);
    });
 }
    
    private void Afficher(List<Constat> constats) {
        // Ajout des constats dans la liste observable
    ObservableList<String> items = FXCollections.observableArrayList();
    for (Constat c: constats) {
        items.add(c.getNomclient_e()+ c.getPrenomclient_e() + "  \"" + c.getAdresseclient_e() + " \"" + c.getNumcontrat_e() + "  \"" + c.getMail() + "  \"" + c.getDate_creation());
    }
    list.setItems(items);
    }

   
>>>>>>> 0326fe5 (api)
    }