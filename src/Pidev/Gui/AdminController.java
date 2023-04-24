/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.Gui;

import Pidev.Entities.User;
import Pidev.Services.UserCrud;
import Pidev.Utilis.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mayssa
 */
public class AdminController implements Initializable {

    @FXML
    private Button btndeconnexion;
    @FXML
    private TableColumn<?, ?> Nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> cin;
    @FXML
    private TableColumn<?, ?> numtel;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;

    @FXML
    private TableView<User> tableviewUser;
    @FXML
    private TableColumn<?, ?> roles;
    @FXML
    private Button actualiser;
    /**
     *
     * /**
     * Initializes the controller class.
     */

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    User user = null;
    @FXML
    private TextField fxRecherche;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showRec();
    }

    @FXML
    public void showRec() //affiche une liste utilisateur fe table view 
    {

        ObservableList<User> list = getUserList();
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));

        tableviewUser.setItems(list);

    }

    public ObservableList<User> getUserList() { //methode affichage mtaa liste utilisateur  fe table view 
        cnx = MyConnection.getInstance().getCnx();

        ObservableList<User> UserList = FXCollections.observableArrayList();
        try {
            String query2 = "SELECT * FROM  user ";
            PreparedStatement smt = cnx.prepareStatement(query2);
            User user;
            ResultSet rs = smt.executeQuery();
            while (rs.next()) { //parcour les enregistrement retoune par la requette sql 
                user = new User(rs.getInt("id"), rs.getString("email"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("cin"), rs.getString("adresse"), rs.getInt("num_tel"), rs.getString("roles"));
                UserList.add(user);//ajout utilisateur fe liste 
            }
            System.out.println(UserList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return UserList;

    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void suprimer(ActionEvent event) {
        UserCrud u = new UserCrud();

        User user = (User) tableviewUser.getSelectionModel().getSelectedItem();

        u.supprimerUtilisateur(user);
        refresh();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("4 roues assurrances  :: Succes Message ");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur supprimé");
        alert.showAndWait();
    }

    @FXML
    private void modifier(ActionEvent event) {
        user = (User) tableviewUser.getSelectionModel().getSelectedItem(); //recuperation du utilisateur selectionné 
        FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("ModifierUtilisateur.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        ModifierUtilisateurController muc = loader.getController(); //recuperation deu controller de modification 
        //mrc.setUpdate(true);
        muc.setTextFields(user); //bech taabili les text field eli hachty bihom 
        //tkhajlk fenetre mtaa modification 
        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        showRec();
    }

    private void refresh() //mettre a jour du continue du tableView 
    {
        ObservableList<User> list = getUserList();
        // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prennom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));

        tableviewUser.setItems(list);

    }

    private void Ajouter(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();//creation de FXMLLoader 
        loader.setLocation(getClass().getResource("Ajouter.fxml")); //emplacement du fichier fxml 
        try {
            loader.load();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        AjouterController muc = loader.getController(); //recuperation deu controller de modification 
        //mrc.setUpdate(true);

        Parent parent = loader.getRoot();
        Stage stage = new Stage(); //affichage de la fenetre 
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        showRec();
    }

    @FXML
    private void Ajouter(MouseEvent event) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setNom(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setPrenom(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void Recherche(KeyEvent event) {
      
    }
    
   
}
