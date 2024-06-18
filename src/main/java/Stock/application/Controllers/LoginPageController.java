package Stock.application.Controllers;

import Stock.classes.Users.Users;
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
//    LoginPageModel loginPageModel = new LoginPageModel();

    public AnchorPane AnchorPane;
    public TextField Username;
    public TextField Password;
    public Button Submit;
    public Label Result;

    public void login(ActionEvent event) {
        Users user = new Users(Username.getText(), Password.getText());
        if (user.isLogin()) {
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
        SceneController.switchToCreateAccount(event);
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }
}
