package Stock.application.Controllers;

import Stock.application.Models.NewProductModel;
import Stock.application.Models.ProductModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewProductController {
    NewProductModel newProductModel = new NewProductModel();

    public TextField Product_Name;
    public TextField Product_Quantity;
    public TextField Product_Price;
    public TextField Last_Stocked;
    public Button submit;

    public void addProduct() {
        // Add product to database
        newProductModel.Add(Product_Name.getText(), Integer.parseInt(Product_Quantity.getText()), Integer.parseInt(Product_Price.getText()), Last_Stocked.getText());

    }
}
