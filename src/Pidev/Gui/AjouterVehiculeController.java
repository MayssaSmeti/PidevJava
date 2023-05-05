/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.Client;
import Pidev.Entities.CurrentUser;
import Pidev.Entities.Vehicule;
import Pidev.Services.VehiculeCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterVehiculeController implements Initializable {

    @FXML
    private TextField tfmatricule;
    @FXML
    private ComboBox tftype;
    @FXML
    private ComboBox tfmarque;
    @FXML
    private Button btnAjouter;
    @FXML
    private Spinner<Integer> tfnb_ch;
    @FXML
    private Text messagemat;
    @FXML
    private Text messagenb;
    @FXML
    private Text messagetype;
    @FXML
    private Text messagemarque;
    @FXML
    private Label t1;
    @FXML
    private TextField t2;
    @FXML
    private Text messageCaptcha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> listtype = FXCollections.observableArrayList("Voiture","Moto","Camion");
         tftype.setItems(listtype);
         ObservableList<String> listmarque = FXCollections.observableArrayList("Kia","Symbole","Clio","BMW","Range Rover","Ibiza","Mercedes","Toyota","Ford","Honda");
         tfmarque.setItems(listmarque);
         SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
         valueFactory.setValue(1);
         tfnb_ch.setValueFactory(valueFactory);
         t1.setText(generateCaptcha());
         
    }    

    @FXML
    private void EnregistrerVehicule(ActionEvent event) throws SQLException, IOException {
      
            String matricule = tfmatricule.getText();
            String type = tftype.getSelectionModel().getSelectedItem().toString();
            String marque = tfmarque.getSelectionModel().getSelectedItem().toString();
            String nb_ch = tfnb_ch.getValue().toString();
            String s1 = t1.getText();
            String s2 = t2.getText();

            
            if(!matricule.matches("[0-9]+") || matricule.isEmpty()){
                 messagemat.setText("Saisie invalide! Veuillez saisir uniquement des chiffres.");
                 return;
             }
             if(type.isEmpty() ){
                 messagetype.setText("Veuillez choisir le type de votre véhicule!");
                 return;
             }
             if(marque.isEmpty()){
                  messagemarque.setText("Veuillez choisir la marque de votre véhicule!");
                  return;
             }
             if(!nb_ch.matches("[0-9]+") || nb_ch.isEmpty()){
                 messagenb.setText("Saisie invalide! Veuillez saisir uniquement des chiffres.");
                 return;
             }
                  if(s2.isEmpty() || !s1.equals(s2)){
                     messageCaptcha.setText("Please enter valid captcha!!");
              }
                  
          
            //Client client = new Client(1);
            // Vehicule v = new Vehicule(matricule,type,marque,nb_ch,client);           
            Vehicule v = new Vehicule(matricule,type,marque,nb_ch,CurrentUser.getLoggedInUser());
            VehiculeCRUD vcd = new VehiculeCRUD();
            vcd.ajouterVehicule2(v);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Véhicule ajouter avec succées!",ButtonType.OK);
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsVehicule.fxml"));
        
        try {    
            Parent root = loader.load();
            DetailsVehiculeController dvc = loader.getController();
            dvc.setTfMatricule(v.getMatricule());
            dvc.setTfType(v.getType());
            dvc.setTfMarque(v.getMarque());
            dvc.setTfNb_Ch(v.getNb_ch());
            
            ((Pane)tfmatricule.getParent()).getChildren().setAll(root);
            
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
    }
    
        private static String generateCaptcha() {
        String captchaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String captcha = "";
        for(int i = 0; i < 5; i++) {
             int L = (int) (Math.random()*captchaString.length());
             captcha = captcha + captchaString.charAt(L);
         }
           return captcha;
    }
    
    
}
 