package Stock.application.Controllers.Delivery;

import Stock.application.Controllers.SceneController;
import Stock.classes.All_Products;
import Stock.classes.Deliveries.Deliveries;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Stock.classes.All_Products.*;
import static Stock.classes.Deliveries.Deliveries.generateDeliveryID;
import static Stock.classes.Misc.Clock.formatDateForUser;

public class NewDeliveryController {

    int counter = 0;

    public TextField Delivery_Name;
    public TextField Delivery_Supplier;
    public TextField everythingQuantity;
    public Button everythingDelivery;
    public ScrollPane productScrollPane;
    public Pane productPane;
    public DatePicker datePicker;
    public DatePicker datePickerEverything;

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }

    public void addAnotherProduct(ActionEvent event) {
        // add another product selector to delivery
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
        LocalDate DeliveryDate = datePicker.getValue(); int FormattedDate = Integer.parseInt(DeliveryDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        String deliveryCompany = Delivery_Supplier.getText();


        System.out.println("DatePicker: " + FormattedDate);

        ArrayList<ArrayList<String>> orderedProducts = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < counter; i++) {
            ArrayList<String> subproduct = new ArrayList<String>();
            ChoiceBox<KeyValuePair> product = (ChoiceBox<KeyValuePair>) productPane.lookup("#product" + i);
            TextField product_quantity = (TextField) productPane.lookup("#product_quantity" + i);

            System.out.println(product.getValue().getKey()); // Product Name
            System.out.println(product_quantity.getText());  // Product Quantity

            subproduct.add(product.getValue().getKey());    // Product Name
            subproduct.add(product_quantity.getText());     // Product Quantity
            orderedProducts.add(subproduct);
        }

        Deliveries delivery = new Deliveries(generateDeliveryID(), deliveryName, FormattedDate, deliveryCompany);
        // Add delivery to the delivery database
        delivery.AddDelivery();

        // Adds each product to the order database, updates Last_Stocked function
        delivery.addProductsToOrder(orderedProducts);

        IncreaseStock(orderedProducts);
        switchToHomepage(event);
    }

    public void everythingDelivery(ActionEvent event) {
        LocalDate DeliveryDate = datePickerEverything.getValue(); int FormattedDate = Integer.parseInt(DeliveryDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        String deliveryName = "Everything Delivery: " + formatDateForUser(FormattedDate); // "Everything Delivery 2021-12-31
        String deliveryCompany = "Everything Men";
        String productQuantity = everythingQuantity.getText();

        Deliveries delivery = new Deliveries(generateDeliveryID(), deliveryName, FormattedDate, deliveryCompany);
        delivery.AddDelivery();

        ArrayList<ArrayList<String>> orderedProducts = new ArrayList<ArrayList<String>>();

        for (All_Products products: All_Products.getAllProducts()) {
            ArrayList<String> subproduct = new ArrayList<String>();
            subproduct.add(products.getProduct_ID() + ": " + products.getProduct_Name());
            subproduct.add(productQuantity);
            orderedProducts.add(subproduct);
        }

        delivery.addProductsToOrder(orderedProducts);

        IncreaseStock(orderedProducts);
        switchToHomepage(event);
    }

    public void IncreaseStock(ArrayList<ArrayList<String>> orderedProducts) {
        // Increase stock of product
        for (ArrayList<String> product : orderedProducts) {

            String productIDandName = product.get(0);
            List<String> productIDandNameArray = IDandProductSplit(productIDandName);


            int productID = Integer.parseInt(productIDandNameArray.get(0));
            String productName = productIDandNameArray.get(1);
            int productNewQuantity = Integer.parseInt(product.get(1));

            bulkAddStock(productID, productNewQuantity);
        }
    }

    public void pickDate(ActionEvent event) {
        System.out.println("Date picked");
        String FormattedDate2 = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("DatePicker: " + FormattedDate2);
    }
}
