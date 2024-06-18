package Stock.application.Controllers;

import Stock.application.SqliteConnection;
import Stock.classes.All_Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static Stock.classes.Misc.Clock.formatDateForUser;

public class SpecificProductController {
    @FXML private Label Product_Name;
    @FXML private Label Product_Price;
    @FXML private Label Product_Quantity;
    @FXML private Label Last_Stocked;
    @FXML private ImageView ProductImage;
    @FXML private Button exportData;

    All_Products product;

    public void setProduct(All_Products product) {
        this.product = product;

        Product_Name.setText(product.getProduct_Name());
        Product_Price.setText("£ " + product.getProduct_Restock_Price().toString());
        Product_Quantity.setText(product.getProduct_Quantity() + " units");
        Last_Stocked.setText(formatDateForUser(product.getLast_Stocked()));
        try {
            ProductImage.setImage(product.getImage());
        } catch (IllegalArgumentException e) {
            ProductImage.setImage(new Image("/images/placeholder.png"));
        }
    }

    public void plusOneStock(ActionEvent event) {
        product.stockAddOne();
        product.setProduct_Quantity(product.getProduct_Quantity() + 1);
        Product_Quantity.setText((product.getProduct_Quantity()) + " units");
    }

    public void minusOneStock(ActionEvent event) {
        product.stockRemoveOne();
        product.setProduct_Quantity(product.getProduct_Quantity() - 1);
        Product_Quantity.setText((product.getProduct_Quantity()) + " units");
    }

    public void exportData(ActionEvent event) {
        product.printOneToFile();
        exportData.setText("Exported Data");
    }

    public void switchToStockTracker(ActionEvent event) {
        SceneController.switchToStockTracker(event);
    }
}

