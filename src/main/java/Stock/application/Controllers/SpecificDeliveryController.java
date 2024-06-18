package Stock.application.Controllers;

import Stock.classes.Deliveries.Deliveries;
import Stock.classes.Deliveries.Ordered_Items;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static Stock.classes.Deliveries.Ordered_Items.FetchOrderedItemsByDeliveryID;
import static Stock.classes.Misc.Clock.formatDateForUser;

public class SpecificDeliveryController implements Initializable {

    @FXML private TableView<Ordered_Items> tableView;
    @FXML private TableColumn<Ordered_Items, String> Product_ID;
    @FXML private TableColumn<Ordered_Items, String> Product_Name;
    @FXML private TableColumn<Ordered_Items, String> Product_Quantity;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        Product_ID.setCellValueFactory(new PropertyValueFactory<Ordered_Items, String>("Product_ID"));
        Product_Name.setCellValueFactory(new PropertyValueFactory<Ordered_Items, String>("Product_Name"));
        Product_Quantity.setCellValueFactory(new PropertyValueFactory<Ordered_Items, String>("Product_Quantity"));

//        tableView.getItems().setAll(specificDeliveryModel.FetchData(delivery.getDelivery_ID()));
    }

    @FXML private Label Delivery_ID;
    @FXML private Label Delivery_Name;
    @FXML private Label Delivery_Date;
    @FXML private Label Delivery_Supplier;

    public void setDelivery(Deliveries delivery) {
        System.out.println("Delivery_ID: " + delivery.getDelivery_ID());
        System.out.println("Delivery_Name: " + delivery.getDelivery_Name());
        System.out.println("Delivery_Date: " + formatDateForUser(delivery.getDelivery_Date()));
        System.out.println("Delivery_Supplier: " + delivery.getDelivery_Company());

        Delivery_ID.setText(String.valueOf(delivery.getDelivery_ID()));
        Delivery_Name.setText(delivery.getDelivery_Name());
        Delivery_Date.setText(String.valueOf(delivery.getDelivery_Date()));
        Delivery_Supplier.setText(delivery.getDelivery_Company());

        tableView.getItems().setAll(FetchOrderedItemsByDeliveryID(delivery.getDelivery_ID()));
    }

    public void switchToUpcomingDeliveries(ActionEvent event) {
        SceneController.switchToUpcomingDeliveries(event);
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }
}
