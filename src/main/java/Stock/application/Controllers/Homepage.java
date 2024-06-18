package Stock.application.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Homepage implements Initializable {
    public Button Signin;
    public String User;
    public Button StockTracker;
    public Button UpcomingDeliveries;
    public Button TransactionAnalytics;
    public Button Button4;
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
            TransactionAnalytics.setDisable(true);
            Button4.setDisable(true);
        }
    }

    public void switchToStockTracker(ActionEvent event) {
        SceneController.switchToStockTracker(event);
    }

    public void switchToUpcomingDeliveries(ActionEvent event) {
        SceneController.switchToUpcomingDeliveries(event);
    }

    public void switchToTransactionAnalytics(ActionEvent event) {
        SceneController.switchToTransactionAnalytics(event);
    }

    public void switchToApproveAdmins(ActionEvent event) {
        SceneController.switchToApproveAdmins(event);
    }

    public void switchToSignin(ActionEvent event) {
        SceneController.switchToSignin(event);
    }
}
