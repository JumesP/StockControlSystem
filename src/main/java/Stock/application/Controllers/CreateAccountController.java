package Stock.application.Controllers;

import Stock.classes.Users.Users;
import Stock.classes.Users.Admins;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static Stock.classes.Users.Users.generateUserID;


public class CreateAccountController {
    public TextField Username;
    public TextField Password;
    public CheckBox Admin;
    public Button Submit;
    public Label error;


    public void createAccount() {
        System.out.println(Username.getText());
        System.out.println(Password.getText());
        System.out.println(Admin.isSelected());
        if (Admin.isSelected()) {
            Admins admin = new Admins(Username.getText(), Password.getText());
            if (admin.isCreateAccount(Username.getText(), Password.getText(), Admin.isSelected())) {
                System.out.println("Account created successfully");
                error.setText("Account created successfully");
            } else {
                System.out.println("Account creation failed");
                error.setText("Account creation failed");
            }
        } else {
            Users user = new Users(Username.getText(), Password.getText());
            if (user.isCreateAccount(Username.getText(), Password.getText(), Admin.isSelected())) {
                System.out.println("Account created successfully");
                error.setText("Account created successfully");
            } else {
                System.out.println("Account creation failed");
                error.setText("Account creation failed");
            }
        }
    }

    public void switchToLogin(ActionEvent event) {
        SceneController.switchToSignin(event);
    }

    public void switchToHomepage(ActionEvent event) { SceneController.switchToHomepage(event); }
}
