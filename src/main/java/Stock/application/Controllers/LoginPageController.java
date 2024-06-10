package Stock.application.Controllers;

import Stock.application.Models.LoginPageModel;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileWriter;

public class LoginPageController {
    LoginPageModel loginPageModel = new LoginPageModel();

    public AnchorPane AnchorPane;
    public TextField Username;
    public TextField Password;
    public Button Submit;
    public Label Result;

    public void login(ActionEvent event) {
        if (loginPageModel.isLogin(Username.getText(), Password.getText())) {
            System.out.println("Login successful");
            Result.setText("Login successful");

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            switchToHomepage(event);
        } else {
            System.out.println("Login failed");
            Result.setText("Incorrect Username and Password combination.\nPlease enter a valid Username and Password");
        }
    }

    public void switchToCreateAnAccount(ActionEvent event) {

        Stage stage;
        Scene scene;
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login/CreateAccountPage.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Homepage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
