package Stock.application.Controllers;

import Stock.classes.Deliveries.Deliveries;
import Stock.classes.Deliveries.Ordered_Items;
import Stock.classes.Sales.Sales;
import Stock.classes.Sales.Transaction_Items;
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
import static Stock.classes.Sales.Transaction_Items.FetchTransactionItemsBySaleID;

public class SpecificSaleController implements Initializable {


    @FXML private TableView<Transaction_Items> tableView;
    @FXML private TableColumn<Transaction_Items, String> Product_ID;
    @FXML private TableColumn<Transaction_Items, String> Product_Name;
    @FXML private TableColumn<Transaction_Items, String> Product_Quantity;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        Product_ID.setCellValueFactory(new PropertyValueFactory<Transaction_Items, String>("Product_ID"));
        Product_Name.setCellValueFactory(new PropertyValueFactory<Transaction_Items, String>("Product_Name"));
        Product_Quantity.setCellValueFactory(new PropertyValueFactory<Transaction_Items, String>("Viewable_Quantity"));
    }

    @FXML private Label Sale_ID;
    @FXML private Label Sale_Date;

    public void setSale(Sales sale) {
        Sale_ID.setText(String.valueOf(sale.getSale_ID()));
        Sale_Date.setText(formatDateForUser(sale.getSale_Date()));

        tableView.getItems().setAll(FetchTransactionItemsBySaleID(sale.getSale_ID()));
    }

    public void switchToSaleTracker(ActionEvent event) {
        SceneController.switchToSalesTracker(event);
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }
}
