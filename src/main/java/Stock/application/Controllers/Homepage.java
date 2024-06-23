package Stock.application.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import static Stock.classes.Users.Users.isAdmin;

public class Homepage implements Initializable {
    public Button Signin;
    public Button StockTracker;
    public Button UpcomingDeliveries;
    public Button TransactionAnalytics;
    public Button SalesTracker;
    public Button switchToApproveAdmins;
    public Label loginToAccess;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switch(isAdmin()) {
            case "guest":
                Signin.setText("Sign in");
                StockTracker.setDisable(true);
                UpcomingDeliveries.setDisable(true);
                TransactionAnalytics.setDisable(true);
                SalesTracker.setDisable(true);
                switchToApproveAdmins.setDisable(true);
                loginToAccess.setText("Please login to access the system");
                break;
            case "admin":
                Signin.setText("Signed in as Admin");
                loginToAccess.setVisible(false);
                break;
            case "user":
                Signin.setText("Signed in as User");
                switchToApproveAdmins.setDisable(true);
                loginToAccess.setVisible(false);
                break;
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

    public void switchToSalesTracker(ActionEvent event) { SceneController.switchToSalesTracker(event); }

    public void switchToApproveAdmins(ActionEvent event) {
        SceneController.switchToApproveAdmins(event);
    }

    public void switchToSignin(ActionEvent event) {
        SceneController.switchToSignin(event);
    }
}
