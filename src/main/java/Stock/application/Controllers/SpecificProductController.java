package Stock.application.Controllers;

import Stock.application.Models.SpecificProductModel;
import Stock.classes.All_Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SpecificProductController implements Initializable {
    @FXML private Label Product_Name;
    @FXML private Label Product_Price;
    @FXML private Label Product_Quantity;
    @FXML private Label Last_Stocked;
    @FXML private ImageView ProductImage;

    SpecificProductModel specificProductModel = new SpecificProductModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        All_Products product = specificProductModel.FetchData();
//        setProduct(product);
    }

    public void setProduct(All_Products product) {


        Product_Name.setText(product.getProduct_Name());
        Product_Price.setText("£ " + product.getProduct_Price().toString());
        Product_Quantity.setText(product.getProduct_Quantity().toString() + " units");
        Last_Stocked.setText(product.getLast_Stocked());
        try {
            ProductImage.setImage(product.getImage());
        } catch (IllegalArgumentException e) {
            ProductImage.setImage(new Image("/images/placeholder.png"));
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

