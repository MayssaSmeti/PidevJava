package Pidev.Gui;
import javafx.scene.paint.Color;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import Pidev.Entities.Rendezvous;
import Pidev.Services.ServiceRendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AfficherRendezvousController implements Initializable {

    @FXML
    private ListView<Rendezvous> list_rdv;

    ServiceRendezvous rendezvousService = new ServiceRendezvous();

    final private ObservableList<Rendezvous> rdvList = FXCollections.observableArrayList();

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField searchLieuField;
    @FXML
    private ImageView qrCodeImageView;
    @FXML
    private Button deleteBtn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set the cell factory for the list view
        list_rdv.setCellFactory(lv -> new ListCell<Rendezvous>() {
            @Override
           
     public void updateItem(Rendezvous item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
        setText(null);
    } else {
        setText(item.getLieu() + " - " + item.getDate().toString());
        //generateQRCode(item);
        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> editRendezvous(item));
        VBox vbox = new VBox(editButton);
        setGraphic(vbox);
    }
}

        });

        // Load data from the database
        List<Rendezvous> rdv = rendezvousService.getAll();
        rdvList.addAll(rdv);

        // Bind the list view to the observable list
        list_rdv.setItems(rdvList);
    }

    @FXML
    void searchLieu(ActionEvent event) {
        String lieu = searchLieuField.getText().trim();
        if (lieu.isEmpty()) {
            list_rdv.setItems(rdvList);
            return;
        }

        ObservableList<Rendezvous> filteredData = FXCollections.observableArrayList();
        for (Rendezvous rdv : rdvList) {
            if (rdv.getLieu().toLowerCase().contains(lieu.toLowerCase())) {
                filteredData.add(rdv);
            }
        }
        list_rdv.setItems(filteredData);
    }

    @FXML
    private void delete(ActionEvent event) {
        Rendezvous selectedRendezvous = list_rdv.getSelectionModel().getSelectedItem();
        System.out.println(selectedRendezvous);
        if (selectedRendezvous != null) {
            rendezvousService.delete(selectedRendezvous);
            rdvList.remove(selectedRendezvous);
        }
    }
    
    private void editRendezvous(Rendezvous rdv) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditRendezvous.fxml"));
            Parent root = (Parent) loader.load();
            EditRendezvousController controller = loader.getController();
            controller.initData(rdv);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            list_rdv.refresh();
        } catch (IOException ex) {
            Logger.getLogger(AfficherRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

private void generateQRCode(Rendezvous rdv) {
   // Rendezvous selectedRdv = list_rdv.getSelectionModel().getSelectedItem();

     String text=rdv.getDate()+ " - " +rdv.getLieu() ;
   
//String rdvString = selectedRdv.getLieu() + " - " + selectedRdv.getDate().toString();
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    int width = 150;
    int height = 150;
    ByteMatrix byteMatrix = null;
    try {
        byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
    } catch (WriterException ex) {
        Logger.getLogger(AfficherRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
    }
    WritableImage qrCodeImage = new WritableImage(width, height);
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            qrCodeImage.getPixelWriter().setColor(x, y, byteMatrix.get(x, y) == 0 ? Color.WHITE : Color.BLACK);
        }
    }
    qrCodeImageView.setImage(qrCodeImage);
}

    @FXML
    private void generateQRCode(ActionEvent event) {
        Rendezvous selectedRendezvous = list_rdv.getSelectionModel().getSelectedItem();
         System.out.println(selectedRendezvous);
        generateQRCode(selectedRendezvous);
    }


    
    
}
