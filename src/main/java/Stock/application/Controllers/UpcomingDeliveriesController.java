package Stock.application.Controllers;

import Stock.classes.Deliveries.Deliveries;
import Stock.classes.Deliveries.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import Stock.classes.Misc.Clock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Stock.classes.Deliveries.Deliveries.*;

public class UpcomingDeliveriesController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;

    @FXML private Button NewDeliveries;
    @FXML private TableView<Deliveries> tableView;
    @FXML private TableColumn<Deliveries, String> Delivery_ID;
    @FXML private TableColumn<Deliveries, String> Delivery_Name;
    @FXML private TableColumn<Deliveries, String> Delivery_Date;
    @FXML private TableColumn<Deliveries, String> Delivery_Company;
    @FXML private Label date;
    @FXML private ChoiceBox<String> DeliverySelect;

    public String[] DeliveryOptions = {"All Deliveries", "Todays Deliveries", "Recent Deliveries", "Upcoming Deliveries"};
    Clock clock = new Clock();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        Delivery_ID.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_ID"));
        Delivery_Name.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_Name"));
        Delivery_Date.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Viewable_Delivery_Date"));
        Delivery_Company.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_Company"));

        tableView.getItems().setAll(FetchAllDeliveryData());

        File file = new File("src/main/java/Stock/backend/cookie.txt");
        Scanner scanner = null;
        try { scanner = new Scanner(file); } catch (FileNotFoundException e) { throw new RuntimeException(e); }

        if (scanner.hasNext()) {
            String data = scanner.nextLine();
            if (!data.equals("admin")) {
                NewDeliveries.setDisable(true);
                NewDeliveries.setCursor(Cursor.CLOSED_HAND);
            }
        }

        date.setText(clock.getDate());
        DeliverySelect.getItems().addAll(DeliveryOptions);
        DeliverySelect.setOnAction(this::switchDelivery);
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }

    public void switchToNewDeliveries(ActionEvent event) {
        SceneController.switchToNewDelivery(event);
    }

    public void viewSpecificDelivery(MouseEvent event) throws IOException {
        Deliveries deliveries = tableView.getSelectionModel().getSelectedItem();
        System.out.println(deliveries.getDelivery_ID());
        System.out.println(deliveries.getDelivery_Name());

        // Switch to the specific delivery page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SpecificDelivery.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpecificDeliveryController specificDeliveryController = loader.getController();
        specificDeliveryController.setDelivery(deliveries);

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchDelivery(ActionEvent event) {
        System.out.println("Switching delivery");
        // get data from menu
        String delivery = DeliverySelect.getValue();

        if (delivery == null) {
            System.out.println("No delivery selected");
            return;
        }

        switch (delivery) {
            case "Recent Deliveries" -> tableView.getItems().setAll(FetchPastDeliveryData());
            case "Upcoming Deliveries" -> tableView.getItems().setAll(FetchUpcomingDeliveryData());
            case "Todays Deliveries" -> tableView.getItems().setAll(FetchTodaysDeliveryData());
            case "All Deliveries" -> tableView.getItems().setAll(FetchAllDeliveryData());
        }

        System.out.println(delivery);
    }


}
