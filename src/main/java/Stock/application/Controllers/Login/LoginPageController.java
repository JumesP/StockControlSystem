package Stock.application.Controllers.Login;

import Stock.application.Controllers.SceneController;
import Stock.classes.Users.Users;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LoginPageController {

    public AnchorPane AnchorPane;
    public TextField Username;
    public TextField Password;
    public Button Submit;
    public Label Result;

    public void login(ActionEvent event) {
        Users user = new Users(Username.getText(), Password.getText());
        if (user.isLogin()) {
            Result.setText("Login successful");
        } else {
            System.out.println("Login failed");
            Result.setText("Login failed\nPlease enter a valid Username and Password");
        }
    }

    public void switchToCreateAnAccount(ActionEvent event) {
        SceneController.switchToCreateAccount(event);
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }
}
