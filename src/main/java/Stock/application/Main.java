package Stock.application;

import Stock.classes.Sales.Sales;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileWriter;

import static Stock.application.SqliteConnection.connection;
import static Stock.classes.Misc.Clock.getSortableDateInAdjust;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        String FilePath = "src/main/java/Stock/backend/cookie.txt";
        try {
            // Create Cookie File
            File file = new File(FilePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            //clear file
            FileWriter myWriter = new FileWriter(FilePath);
            myWriter.write("");
            myWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Homepage.fxml"));
            Scene scene = new Scene(root, 900, 650, Color.BLACK);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("css/Homepage.css").toExternalForm());
            primaryStage.setTitle("Supermarket Stock Management System");

            // Logo
            Image icon = new Image("/images/logo.jpeg");
            primaryStage.getIcons().add(icon);

            connection();
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
