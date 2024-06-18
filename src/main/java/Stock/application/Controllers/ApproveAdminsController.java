package Stock.application.Controllers;

//import Stock.application.Models.ApproveAdminsModel;
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

import static Stock.classes.Users.Admins.approveAdminByID;
import static Stock.classes.Users.PendingAdmins.FetchPending;

public class ApproveAdminsController implements Initializable {
//    public ApproveAdminsModel approveAdminsModel = new ApproveAdminsModel();

    @FXML TableView<PendingAdmins> unApprovedAdminsTable;
    @FXML TableColumn<PendingAdmins, Integer> user_id;
    @FXML TableColumn<PendingAdmins, String> username;
    @FXML TableColumn<PendingAdmins, String> password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user_id.setCellValueFactory(new PropertyValueFactory<PendingAdmins, Integer>("User_ID"));
        username.setCellValueFactory(new PropertyValueFactory<PendingAdmins, String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<PendingAdmins, String>("password"));

        unApprovedAdminsTable.getItems().setAll(FetchPending());
    }


    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }

    public void Approve() {
        PendingAdmins pendingAdmin = unApprovedAdminsTable.getSelectionModel().getSelectedItem();

        pendingAdmin.approveAdmin();
        unApprovedAdminsTable.getItems().setAll(FetchPending());
    }
}
