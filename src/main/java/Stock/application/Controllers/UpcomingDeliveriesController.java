package Stock.application.Controllers;

import Stock.application.Models.DeliveriesModel;
import Stock.classes.Deliveries.Deliveries;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpcomingDeliveriesController implements Initializable {
    public DeliveriesModel deliveriesModel = new DeliveriesModel();
//    public TextField Search;

    @FXML
    private TableView<Deliveries> tableView;
    @FXML private TableColumn<Deliveries, String> Delivery_ID;
    @FXML private TableColumn<Deliveries, String> Delivery_Name;
    @FXML private TableColumn<Deliveries, String> Delivery_Date;
    @FXML private TableColumn<Deliveries, String> Delivery_Company;
    @FXML private TableColumn<Deliveries, String> Ordered_Products;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        Delivery_ID.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_ID"));
        Delivery_Name.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_Name"));
        Delivery_Date.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_Date"));
        Delivery_Company.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_Company"));
        Ordered_Products.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Ordered_Products"));

//        System.out.println(DeliveriesModel.FetchDeliveryData());

        tableView.getItems().setAll(deliveriesModel.FetchDeliveryData());
    }

    public void switchToHomepage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;


        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Homepage.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToNewDeliveries(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/NewDelivery.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
