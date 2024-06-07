package Stock.application.Controllers;

import Stock.application.Models.LoginPageModel;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;

public class LoginPageController {
    LoginPageModel loginPageModel = new LoginPageModel();
//    LoginPageModel loginPageModel = new LoginPageModel();

    public TextField Username;
    public PasswordField Password;
    public Button submit;

    public void login(ActionEvent event) {
        if (loginPageModel.isLogin(Username.getText(), Password.getText())) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
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
}
