package Stock.application.Controllers;

import Stock.application.Models.DeliveriesModel;
import Stock.application.Models.SpecificDeliveryModel;
import Stock.classes.Deliveries.Deliveries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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

public class UpcomingDeliveriesController implements Initializable {
    public DeliveriesModel deliveriesModel = new DeliveriesModel();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        Delivery_ID.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_ID"));
        Delivery_Name.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_Name"));
        Delivery_Date.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_Date"));
        Delivery_Company.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Delivery_Company"));
//        Ordered_Products.setCellValueFactory(new PropertyValueFactory<Deliveries, String>("Ordered_Products"));

        tableView.getItems().setAll(deliveriesModel.FetchDeliveryData());

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

        int date = date();
        System.out.println(date);
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

    public int date() {
        Calendar calendar = Calendar.getInstance();

        Date currentDate = calendar.getTime();

        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) { day = "0" + day; }
        String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
        if (month.length() == 1) { month = "0" + month; }
        String currentDateTime = currentDate.toString();
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        int sortableDate = Integer.parseInt(year + month + day);

        System.out.println("Day: " + day);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        System.out.println(currentDateTime);
        System.out.println("Sortable Date: " + sortableDate);

        date.setText(day + "/" + month + "/" + year);

        return sortableDate;
    }
}
