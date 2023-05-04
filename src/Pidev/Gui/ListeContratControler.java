
package Pidev.Gui;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Pidev.Entities.Contrat;
import Pidev.Entities.Offre;
import Pidev.Services.ContratService;
import Pidev.Services.IContratService;
import Pidev.Services.IOffreService;
import Pidev.Services.OffreService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * FXML Controller class
 *
 * @author zaghd
 */
public class ListeContratControler implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private GridPane offreListContainer;
    
     @FXML
    private HBox Excel;
     
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Instancier le service de produit
        IContratService contratService = new ContratService();


             List<Contrat> contrats= contratService.getAll();
            
   
      

        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < contrats.size() ; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Pidev/Gui/OneContratListCard.fxml"));
                HBox oneOffreCard = fxmlLoader.load();
               OneContratListCardControler contratCardController = fxmlLoader.getController();
                contratCardController.setContratData(contrats.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                offreListContainer.add(oneOffreCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(oneOffreCard, new Insets(0, 10, 25, 10));
                oneOffreCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   @FXML
void excelBtn(MouseEvent event) throws FileNotFoundException, IOException {
    // Créer un nouveau classeur
    Workbook workbook = new XSSFWorkbook();

    // Définir un style pour les en-têtes de colonnes
    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    headerStyle.setAlignment(HorizontalAlignment.CENTER);
    Font headerFont = workbook.createFont();
    headerFont.setColor(IndexedColors.WHITE.getIndex());
    headerFont.setBold(true);
    headerStyle.setFont(headerFont);

    // Définir un style pour les données de chaque ligne
    CellStyle dataStyle = workbook.createCellStyle();
    dataStyle.setAlignment(HorizontalAlignment.CENTER);

    // Créer une nouvelle feuille de calcul
    Sheet sheet = workbook.createSheet("Liste Des Contrats");

    // Récupérer la liste des produits
    IContratService produitService = new ContratService();
    List<Contrat> productList = produitService.getAll();

    // Créer la première ligne pour les en-têtes des colonnes
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(1).setCellValue("Validité du");
    headerRow.createCell(2).setCellValue("Validité au");
    headerRow.createCell(3).setCellValue("Matricule");
    headerRow.createCell(4).setCellValue("Cin");

    // Appliquer le style aux en-têtes de colonnes
    for (Cell cell : headerRow) {
        cell.setCellStyle(headerStyle);
    }

    // Remplir les données des produits
    int rowNum = 1;
    for (Contrat produit : productList) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(1).setCellValue(produit.getValiditedu());
        row.createCell(2).setCellValue(produit.getValiditeau());
        row.createCell(3).setCellValue(produit.getId_vehicule());
        row.createCell(4).setCellValue(produit.getId_client());

        // Appliquer le style aux données de chaque ligne
        for (Cell cell : row) {
            cell.setCellStyle(dataStyle);
        }
    }

    // Auto-ajuster la largeur des colonnes
    for (int i = 0; i < headerRow.getLastCellNum(); i++) {
        sheet.autoSizeColumn(i);
    }

    // Ouvrir une boîte de dialogue de sélection de fichier
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Enregistrer le fichier Excel");
    fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers Excel", "*.xlsx"));
    File selectedFile = fileChooser.showSaveDialog(null);

    if (selectedFile != null) {
        // Enregistrer le fichier dans l'emplacement choisi par l'utilisateur
        try (FileOutputStream outputStream = new FileOutputStream(selectedFile)) {
            workbook.write(outputStream);
        }
    }
}

    
    
      @FXML
    void chart(MouseEvent event) throws IOException {
       Parent fxml = FXMLLoader.load(getClass().getResource("Liststat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

}    
    

