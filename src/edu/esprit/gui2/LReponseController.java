/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui2;

import static com.slack.api.bolt.util.ListenerCodeSuggestion.event;
import edu.esprit.services.ReponseCRUD;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import edu.esprit.entities.Reponse;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class LReponseController implements Initializable {

    @FXML
    private ListView<String> tfListe;
 
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmo;
    @FXML
    private TextField tfre;
    @FXML
    private Button btnRe;
 
    private List<String> tousLesResultats; // la liste complète des résultats
    private ObservableList<String> resultatsCourants; // la liste des résultats qui correspondent à la recherche actuelle

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Récupération de toutes les réponses depuis la base de données
    ReponseCRUD reponses = new ReponseCRUD();
    List<Reponse> re = reponses.afficherReponses();

    
    // Ajout des réponses dans la liste observable
    ObservableList<String> items = FXCollections.observableArrayList();
    for (Reponse r : re) {
        items.add(r.getDescription());
    }

    // Affichage de la liste dans le ListView
    tfListe.setItems(items);
    
  tousLesResultats = re.stream()
                    .map(r ->  r.getDescription() )
                    .collect(Collectors.toList());

        resultatsCourants = FXCollections.observableArrayList(tousLesResultats);
        tfListe.setItems(resultatsCourants);

        tfre.textProperty().addListener((observable, oldValue, newValue) -> {
            resultatsCourants.clear();
            if (newValue.isEmpty()) {
                resultatsCourants.addAll(tousLesResultats);
                
            } else {
               resultatsCourants.addAll(tousLesResultats.stream()
                    .filter(r -> r.toLowerCase().contains(newValue.toLowerCase()))
                    .collect(Collectors.toList()));
            }
        });
    }    

   

    @FXML
    private void supprimer(MouseEvent event) {
    ReponseCRUD reponseCRUD = new ReponseCRUD(); // créer une instance de la classe ReponseCRUD
    List<Reponse> reponses = reponseCRUD.afficherReponses();
    String selectedReponse = tfListe.getSelectionModel().getSelectedItem(); // obtenir l'élément sélectionné dans la liste (supposer qu'il s'agit d'une instance de Reponse)
    int selectedID = 0;
    for (Reponse r : reponses) { // parcourir toutes les réponses pour trouver celle qui correspond à l'élément sélectionné
        if (r.getDescription().equals(selectedReponse)) { // comparer la description de chaque réponse avec l'élément sélectionné
            selectedID = r.getId(); // si la description correspond, obtenir l'ID de cette réponse
            break; // sortir de la boucle une fois que l'ID a été trouvé
        }
    }
    reponseCRUD.supprimer(selectedID); // appeler la méthode deleteReponse avec l'ID sélectionné
    tfListe.getItems().remove(selectedReponse); // supprimer l'élément sélectionné de la liste
   }


    @FXML
    private void rechercher(ActionEvent event) {
       ReponseCRUD reponseCRUD = new ReponseCRUD(); // créer une instance de la classe ReponseCRUD
    List<Reponse> reponses = reponseCRUD.afficherReponses();
        String o = tfre.getText();
         
         List<Reponse> bystatue = reponses.stream().filter(re -> re.getDescription() .startsWith(o) ).collect(toList());
             
        
        
        if (tfre.getText().isEmpty()){
         
        show(reponses);
        }
       
        else {
            show(bystatue);
        
        }
    }
    
    private void show(List<Reponse> reponses) {
        // Ajout des réponses dans la liste observable
    ObservableList<String> items = FXCollections.observableArrayList();
    for (Reponse r : reponses) {
        items.add(r.getDescription());
    }

    // Affichage de la liste dans le ListView
    tfListe.setItems(items);
    }

    @FXML
    private void trie(MouseEvent event) {
    ReponseCRUD reponseCRUD = new ReponseCRUD(); // créer une instance de la classe ReponseCRUD
    List<Reponse> reponses = reponseCRUD.afficherReponses();
    
    // Tri de la liste de réponses dans l'ordre croissant en fonction de leur description
    Collections.sort(reponses, (r1, r2) -> r1.getDescription().compareTo(r2.getDescription()));

    // Affichage de la liste triée dans le ListView
    show(reponses);
    }

   @FXML
private void modifier(MouseEvent event) {
    String selectedReponse = tfListe.getSelectionModel().getSelectedItem(); // obtenir l'élément sélectionné dans la liste (supposer qu'il s'agit d'une instance de Reponse)
    ReponseCRUD reponseCRUD = new ReponseCRUD(); // créer une instance de la classe ReponseCRUD
    List<Reponse> reponses = reponseCRUD.afficherReponses();
    int selectedID = 0;
    for (Reponse r : reponses) { // parcourir toutes les réponses pour trouver celle qui correspond à l'élément sélectionné
        if (r.getDescription().equals(selectedReponse)) { // comparer la description de chaque réponse avec l'élément sélectionné
            selectedID = r.getId(); // si la description correspond, obtenir l'ID de cette réponse
            break; // sortir de la boucle une fois que l'ID a été trouvé
        }
    }
    // vous pouvez ajouter ici le traitement pour modifier la réponse sélectionnée
    
}

    @FXML
    private void stat(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./PieReponse.fxml"));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(((Node)event.getSource()).getScene().getWindow());
        popupStage.setScene(new Scene(root));
        popupStage.showAndWait();
    } catch (IOException ex) {
        Logger.getLogger(javafx.scene.chart.PieChart.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

 


    
}
