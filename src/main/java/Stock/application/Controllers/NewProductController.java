package Stock.application.Controllers;

import Stock.application.Models.NewProductModel;
import Stock.application.Models.ProductModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewProductController {
    NewProductModel newProductModel = new NewProductModel();

    public TextField Product_Name;
    public TextField Product_Quantity;
    public TextField Product_Price;
    public TextField Last_Stocked;
    public Button submit;
    public Label error;

    public void addProduct() {
        // Add product to database
        newProductModel.Add(Product_Name.getText(), Integer.parseInt(Product_Quantity.getText()), Integer.parseInt(Product_Price.getText()), Last_Stocked.getText());
        error.setText("Product added successfully");
    }

    public void switchToHomepage(ActionEvent event) {
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
