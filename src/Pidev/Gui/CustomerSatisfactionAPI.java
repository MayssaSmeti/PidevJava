package Pidev.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class CustomerSatisfactionAPI extends Application {

    private Label statusLabel;
    private Pane customerSatisfactionPane;

    public CustomerSatisfactionAPI() {
        // Initialisez votre graphique ici
        customerSatisfactionPane = new Pane();
        // Ajoutez tous les éléments nécessaires au pane
    }

    public Pane getCustomerSatisfactionPane() {
        return customerSatisfactionPane;
    }

    @Override
    public void start(Stage stage) {
        // Créer les éléments de l'interface utilisateur
        Label feedbackLabel = new Label("Veuillez entrer votre commentaire ci-dessous :");
        TextArea feedbackArea = new TextArea();
        feedbackArea.setWrapText(true);

        Label ratingLabel = new Label("Veuillez entrer votre évaluation (sur 10) ci-dessous :");
        TextArea ratingArea = new TextArea();
        ratingArea.setWrapText(true);

        Button submitButton = new Button("Soumettre");

        // Mettre en place la disposition de la scène
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        gridPane.add(feedbackLabel, 0, 0);
        gridPane.add(feedbackArea, 0, 1);
        gridPane.add(ratingLabel, 0, 2);
        gridPane.add(ratingArea, 0, 3);
        gridPane.add(submitButton, 0, 4);

        // Ajouter un écouteur d'événements pour le bouton Soumettre
        submitButton.setOnAction(event -> {
            String feedback = feedbackArea.getText();
            String rating = ratingArea.getText();

            // Valider que les champs ne sont pas vides
            if (feedback.isEmpty() || rating.isEmpty()) {
                statusLabel.setText("Veuillez remplir tous les champs !");
                return;
            }

            // Valider que l'évaluation est un nombre valide
            try {
                int ratingValue = Integer.parseInt(rating);
                if (ratingValue < 0 || ratingValue > 10) {
                    statusLabel.setText("L'évaluation doit être un nombre entre 0 et 10 !");
                    return;
                }
            } catch (NumberFormatException e) {
                statusLabel.setText("L'évaluation doit être un nombre valide !");
                return;
            }

            // Enregistrer le commentaire et l'évaluation dans un fichier
            String feedbackText = String.format("[%s] Commentaire : %s | Évaluation : %s%n",
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    feedback,
                    rating);
            saveFeedbackToFile(feedbackText);
            statusLabel.setText("Merci pour votre commentaire et votre évaluation !");
        });

        // Ajouter une étiquette pour afficher le statut
        statusLabel = new Label("");
        HBox statusBox = new HBox(statusLabel);
        statusBox.setAlignment(Pos.CENTER);

        // Mettre en place la scène
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        root.add(gridPane, 0, 0);
        root.add(statusBox, 0, 1);

        Scene scene = new Scene(root, 400, 400);
stage.setScene(scene);
stage.show();
}
    private void saveFeedbackToFile(String feedback) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("feedback.txt", true))) {
        writer.write(feedback);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   


public static void main(String[] args) {
    launch(args);
}
}
