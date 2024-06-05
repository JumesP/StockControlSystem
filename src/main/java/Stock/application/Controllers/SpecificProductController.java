package Stock.application.Controllers;

import Stock.classes.All_Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class SpecificProductController {
    @FXML private Label Product_Name;
    @FXML private Label Product_Price;
    @FXML private Label Product_Quantity;
    @FXML private Label Last_Stocked;

    public void setProduct(All_Products product) {
        Product_Name.setText(product.getProduct_Name());
        Product_Price.setText("£ " + product.getProduct_Price().toString());
        Product_Quantity.setText(product.getProduct_Quantity().toString() + " units");
        Last_Stocked.setText(product.getLast_Stocked());
    }

    public void switchToStockTracker(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/StockTracker.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

