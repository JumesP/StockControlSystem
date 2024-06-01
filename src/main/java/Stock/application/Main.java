package Stock.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Homepage.fxml"));
            Scene scene = new Scene(root, 900, 650, Color.BLACK);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("Homepage.css").toExternalForm());
            primaryStage.setTitle("Supermarket Stock Management System");




            // Logo
          //  Image icon = new Image("/main/java/org/example/application/icon.png");
          //  primaryStage.getIcons().add(icon);

//            Button button = new Button("Button 1");
//            EventHandler listener = new MyEventHandler();
//            button.setOnAction(listener);
//
//            public class MyEventHandler implements EventHandler{
//                @Override
//                public void handle(ActionEvent event) {
//                    System.out.println("Button clicked");
//                }
//            }


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
