/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Devis;
import Pidev.Entities.DevisItem;
import Pidev.Services.ServiceDevis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author SCORPIO-12
 */
public class EditDevisController implements Initializable {

    @FXML
    private Label totalLabel;

    @FXML
    private TableView<DevisItem> itemsTable;

    @FXML
    private TableColumn<DevisItem, String> descriptionColumn;

    @FXML
    private TableColumn<DevisItem, Integer> quantityColumn;

    @FXML
    private TableColumn<DevisItem, Double> priceColumn;

    @FXML
    private TableColumn<DevisItem, Double> totalColumn;

    @FXML
    private Button modifyButton;

    private Devis currentDevis;
    private int selectedDevisItemId;

    ServiceDevis sd = new ServiceDevis();
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set up table columns
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQte()).asObject());
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getUnitprice()).asObject());
        totalColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalprice()).asObject());

        itemsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                descriptionField.setText(String.valueOf(newSelection.getDescription()));
                quantityField.setText(String.valueOf(newSelection.getQte()));
                priceField.setText(String.valueOf(newSelection.getUnitprice()));
            }
        });

    }

    public void setSelectedDevisItemId(int id) {
        selectedDevisItemId = id;
    }

    public void setDevis(Devis devis) throws SQLException {
        currentDevis = devis;

        // set total label
        totalLabel.setText(String.valueOf(currentDevis.getTotal_ht()));
        

        // set table items
        itemsTable.getItems().setAll(currentDevis.getDevisItems(currentDevis.getId()));

    }

    public void updateTotal() {
        double total = 0;
        for (DevisItem item : itemsTable.getItems()) {
            total += item.getTotalprice();
        }
        totalLabel.setText(String.valueOf(total));
        currentDevis.setTotal_ht((float) total);
    }

    public void setUpdatedDevisItem(DevisItem updatedDevisItem) throws SQLException {
        // Find the DevisItem object with the same ID as the updatedDevisItem
        for (DevisItem item : itemsTable.getItems()) {
            if (item.getId() == updatedDevisItem.getId()) {
                // Update the DevisItem object
                item.setDescription(updatedDevisItem.getDescription());
                item.setQte(updatedDevisItem.getQte());
                item.setUnitprice((float) updatedDevisItem.getUnitprice());
                item.setTotalprice((float) updatedDevisItem.getTotalprice());
                sd.updateDevisItem(item);
                break;
            }
        }
        // Update the total label
        updateTotal();

    }

    public void handleAddItem() {
        DevisItem newItem = new DevisItem();
        itemsTable.getItems().add(newItem);
        currentDevis.getDevisItems().add(newItem);
    }

    public void handleDeleteItem() {
        DevisItem selectedItem = itemsTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            itemsTable.getItems().remove(selectedItem);
            currentDevis.getDevisItems().remove(selectedItem);
            updateTotal();
        }
    }

    @FXML
    public void handleModifyItem() {
        // Get the data from the input fields
        String description = descriptionField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        

        // Create a new DevisItem object with the updated data
        DevisItem updatedItem = new DevisItem(currentDevis.getId(), description, quantity, price);

        try {
            System.out.println(currentDevis);
            // Call the setUpdatedDevisItem method to update the item in the database and the table view
            System.out.println(updatedItem);
            sd.updateDevisItem(updatedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
