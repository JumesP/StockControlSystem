package Stock.application.Controllers;

import Stock.classes.All_Products;
import Stock.classes.Deliveries.Deliveries;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Stock.classes.All_Products.bulkAddStock;
import static Stock.classes.Deliveries.Deliveries.IDandProduct;
//import static Stock.classes.Deliveries.Deliveries.addProductsToOrder;
import static Stock.classes.Deliveries.Deliveries.generateDeliveryID;
import static javafx.scene.control.DatePicker.*;

public class NewDeliveryController {
    int counter = 0;

    public TextField Delivery_Name;
//    public TextField Delivery_Date;
    public TextField Delivery_Supplier;
//    public TextField Ordered_Products;
//    public Button submit;
    public ScrollPane productScrollPane;
    public Pane productPane;
//    public Label error;
    public DatePicker datePicker;

//    public void addDelivery() {
//        // Add delivery to database
//        newDeliveryModel.Add(Delivery_Name.getText(), Delivery_Date.getText(), Delivery_Company.getText(), orderedProducts);
////        error.setText("Delivery added successfully");
//    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }

    public void addAnotherProduct(ActionEvent event) {
        List<String> products = IDandProduct();

        ChoiceBox<KeyValuePair> product = new ChoiceBox<KeyValuePair>();

        for (String p : products) {
            product.getItems().add(new KeyValuePair(p, p));
        }

        product.idProperty().set("product" + counter);
        product.setLayoutX(0);
        product.setLayoutY((counter * 30));
        product.prefHeight(31);
        product.prefWidth(249);

        TextField product_quantity = new TextField();
        product_quantity.setPromptText("Product Quantity");
        product_quantity.idProperty().set("product_quantity" + counter);
        product_quantity.setLayoutX(110);
        product_quantity.setLayoutY((counter * 30));
        product_quantity.prefWidth(75);
        product_quantity.prefHeight(28);

        productPane.getChildren().add(product);
        productPane.getChildren().add(product_quantity);
        counter++;
    }

    public class KeyValuePair {
        public String key;
        public String value;
        public KeyValuePair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String toString() {
            return value;
        }
    }

    public void submitDelivery(ActionEvent event) {
        String deliveryName = Delivery_Name.getText();
        LocalDate DeliveryDate2 = datePicker.getValue(); int FormattedDate = Integer.parseInt(DeliveryDate2.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        String deliveryCompany = Delivery_Supplier.getText();


        System.out.println("DatePicker: " + FormattedDate);

        ArrayList<ArrayList<String>> orderedProducts = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < counter; i++) {
            ArrayList<String> subproduct = new ArrayList<String>();
            ChoiceBox<KeyValuePair> product = (ChoiceBox<KeyValuePair>) productPane.lookup("#product" + i);
            TextField product_quantity = (TextField) productPane.lookup("#product_quantity" + i);

            System.out.println(product.getValue().getKey()); // Product Name
            System.out.println(product_quantity.getText()); // Product Quantity

            subproduct.add(product.getValue().getKey());
            subproduct.add(product_quantity.getText());
            orderedProducts.add(subproduct);
        }

//        newDeliveryModel.Add(deliveryName, deliveryCompany, FormattedDate, orderedProducts);
        Deliveries delivery = new Deliveries(generateDeliveryID(), deliveryName, FormattedDate, deliveryCompany);
        // Add delivery to the delivery database
        delivery.AddDelivery();

        // Adds each product to the order database, updates Last_Stocked function
        delivery.addProductsToOrder(orderedProducts);

        IncreaseStock(orderedProducts);
        switchToHomepage(event);
    }

    public void IncreaseStock(ArrayList<ArrayList<String>> orderedProducts) {
        // Increase stock of product
        for (ArrayList<String> product : orderedProducts) {

            String productIDandName = product.get(0);
            String[] productIDandNameArray = productIDandName.split(": ");

            int productID = Integer.parseInt(productIDandNameArray[0]);
            int productNewQuantity = Integer.parseInt(product.get(1));

//            newDeliveryModel.ProductAdd(productID, productNewQuantity);
            bulkAddStock(productID, productNewQuantity);
//            All_Products
        }
    }

    public void pickDate(ActionEvent event) {
        System.out.println("Date picked");
        String FormattedDate2 = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("DatePicker: " + FormattedDate2);
    }

}
