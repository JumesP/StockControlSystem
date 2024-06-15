package Stock.application.Controllers;

import Stock.classes.All_Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class SpecificProductController {
    @FXML private Label Product_Name;
    @FXML private Label Product_Price;
    @FXML private Label Product_Quantity;
    @FXML private Label Last_Stocked;
    @FXML private ImageView ProductImage;

    public void setProduct(All_Products product) {

        System.out.println("Product ID: " + product.getProduct_ID());
        System.out.println("Product Image: " + product.getImage());


        Product_Name.setText(product.getProduct_Name());
        Product_Price.setText("£ " + product.getProduct_Price().toString());
        Product_Quantity.setText(product.getProduct_Quantity().toString() + " units");
        Last_Stocked.setText(product.getLast_Stocked());
//        ProductImage.setImage(product.getImage());
//
//        System.out.println(ProductImage);

        Image image = new Image("/images/" + product.getProduct_ID() + ".png");
        System.out.println(image.errorProperty());
        if (image.errorProperty().getValue() == true) {
            ProductImage.setImage(new Image("/images/placeholder.png"));
        } else {
            ProductImage.setImage(image);
        }
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

