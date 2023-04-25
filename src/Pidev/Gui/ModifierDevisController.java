package Pidev.Gui;


import Pidev.Entities.Devis;
import Pidev.Entities.DevisItem;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModifierDevisController implements Initializable {

    private TableView<DevisItem> devisItemsTable;

    private Devis selectedDevis;
    @FXML
    private VBox itemsContainer;
    @FXML
    private GridPane formContainer;
    @FXML
    private TextField itemDescriptionTextField;
    @FXML
    private TextField itemPrixUTextField;
    @FXML
    private TextField itemQuantiteTextField;

    
    private List<DevisItem> items = new ArrayList<>();

    public void initData(Devis devis) {
        this.selectedDevis = devis; 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(selectedDevis);
        items = selectedDevis.getDevisItems();
        itemDescriptionTextField.setText("" + items.get(0).getDescription());
        itemPrixUTextField.setText("" + items.get(0).getUnitprice());
        itemQuantiteTextField.setText("" + items.get(0).getQte());

    }

    /*private void addItem() {
        // create new devis item with data from fields
        String newItem = newItemField.getText();
        double newPrice = Double.parseDouble(newPriceField.getText());
        int newQuantity = Integer.parseInt(newQuantityField.getText());
        DevisItem newItemObj = new DevisItem(newItem, newPrice, newQuantity);

        // add new item to table and to selectedDevis
        devisItemsTable.getItems().add(newItemObj);
        selectedDevis.getDevisItems().add(newItemObj);

        // clear input fields
        newItemField.clear();
        newPriceField.clear();
        newQuantityField.clear();
    }*/

 /* private void updateDevis() throws IOException {
        // update selectedDevis with items from table
        System.out.println(selectedDevis);
        selectedDevis.setDevisItems(devisItemsTable.getItems());

        // redirect back to devis list view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListDevis.fxml"));
        Parent root = loader.load();
        ListDevisController controller = loader.getController();
        Scene scene = new Scene(root);

        // set the new scene on the stage
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }*/
}
