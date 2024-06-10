package Stock.application.Controllers;

import Stock.application.Models.ApproveAdminsModel;
import Stock.classes.Users.PendingAdmins;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApproveAdminsController implements Initializable {
    public ApproveAdminsModel approveAdminsModel = new ApproveAdminsModel();

    Stage stage;
    Scene scene;
    Parent root;


    @FXML
    TableView<PendingAdmins> unApprovedAdminsTable;
    @FXML
    TableColumn<PendingAdmins, Integer> user_id;
    @FXML
    TableColumn<PendingAdmins, String> username;
    @FXML
    TableColumn<PendingAdmins, String> password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user_id.setCellValueFactory(new PropertyValueFactory<PendingAdmins, Integer>("User_ID"));
        username.setCellValueFactory(new PropertyValueFactory<PendingAdmins, String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<PendingAdmins, String>("password"));

        unApprovedAdminsTable.getItems().setAll(approveAdminsModel.FetchData());
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

    public void Approve() {
        PendingAdmins pendingAdmins = unApprovedAdminsTable.getSelectionModel().getSelectedItem();
        System.out.println(pendingAdmins.getUser_ID());
        System.out.println(pendingAdmins.getUsername());
        System.out.println(pendingAdmins.getPassword());

        approveAdminsModel.ApproveAdmins(pendingAdmins.getUser_ID());
        unApprovedAdminsTable.getItems().setAll(approveAdminsModel.FetchData());
    }
}
