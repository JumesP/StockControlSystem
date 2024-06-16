package Stock.application.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SceneController {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;


    // General
    public static void switchToHomepage(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/Homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToStockTracker(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/StockTracker.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToUpcomingDeliveries(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/UpcomingDeliveries.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // Account Based
    public static void switchToSignin(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/Login/LoginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToApproveAdmins(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/Login/ApproveAdmins.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToCreateAccount(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/Login/CreateAccount.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Products
    public static void switchToNewProduct(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/Products/NewProduct.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToSpecificProduct(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/Products/SpecificProduct.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delivery
    public static void switchToNewDelivery(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/Delivery/NewDelivery.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToSpecificDelivery(ActionEvent event) {
        try {
            root = FXMLLoader.load(SceneController.class.getClassLoader().getResource("fxml/Delivery/SpecificDelivery.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
