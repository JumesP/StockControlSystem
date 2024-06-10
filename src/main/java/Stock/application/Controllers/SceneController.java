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

public class SceneController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public Button Signin;

    public String User;

    public Button StockTracker;
    public Button UpcomingDeliveries;
    public Button RecentDeliveries;
    public Button DepartmentEditor;
    public Button switchToApproveAdmins;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File file = new File("src/main/java/Stock/backend/cookie.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (!scanner.hasNext()) {
            Signin.setText("Sign in");
            User = "guest";
        } else {
            String data = scanner.nextLine();
            if (data.equals("admin")) {
                Signin.setText("Signed in as Admin");
                User = "admin";
            } else if (data.equals("user")) {
                Signin.setText("Signed in as User");
                User = "user";
            }
        }

        if (!User.equals("admin")) {
            switchToApproveAdmins.setDisable(true);
        }

        if (User.equals("guest")) {
            StockTracker.setDisable(true);
            UpcomingDeliveries.setDisable(true);
            RecentDeliveries.setDisable(true);
            DepartmentEditor.setDisable(true);
        }


    }

    public void switchToStockTracker(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/StockTracker.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToHomepage(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToUpcomingDeliveries(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/UpcomingDeliveries.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchToDepartmentEditor(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/DepartmentEditor.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToRecentDeliveries(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/RecentDeliveries.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToSignin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login/LoginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToApproveAdmins(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login/ApproveAdmins.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
