package Stock.application.Controllers;

import Stock.application.Models.CreateAccountModel;
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


public class CreateAccountController {
    CreateAccountModel createAccountModel = new CreateAccountModel();

    public TextField Username;
    public TextField Password;
    public CheckBox Admin;
    public Button Submit;
    public Label error;


    public void createAccount() {
        System.out.println(Username.getText());
        System.out.println(Password.getText());
        System.out.println(Admin.isSelected());
        if (createAccountModel.isCreateAccount(Username.getText(), Password.getText(), Admin.isSelected())) {
            System.out.println("Account created successfully");
            error.setText("Account created successfully");
        } else {
            System.out.println("Account creation failed");
            error.setText("Account creation failed");
        }
    }

    public void switchToLogin(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login/LoginPage.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
