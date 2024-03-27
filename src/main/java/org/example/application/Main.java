package org.example.application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import javax.swing.plaf.ProgressBarUI;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
//            BorderPane root = new BorderPane();
            Group root = new Group();
            Scene scene = new Scene(root, 400, 400, Color.BLACK);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Supermarket Stock Management System");

            // Logo
          //  Image icon = new Image("/main/java/org/example/application/icon.png");
          //  primaryStage.getIcons().add(icon);

            Button button = new Button("Button 1");
            EventHandler listener = new MyEventHandler();
            button.setOnAction(listener);

            public class MyEventHandler implements EventHandler{
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Button clicked");
                }
            }


            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
