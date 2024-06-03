package Stock.application.Controllers;

import Stock.classes.All_Products;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SpecificProductController {
    @FXML private Label Product_Name;
    @FXML private Label Product_Price;
    @FXML private Label Product_Quantity;
    @FXML private Label Last_Stocked;

    public void setProduct(All_Products product) {
        String Name = product.getProduct_Name();
        String Price = product.getProduct_Price().toString();
        String Quantity = product.getProduct_Quantity().toString();
        String LastStocked = product.getLast_Stocked();
        System.out.println(Name + " " + Price + " " + Quantity + " " + LastStocked);
        Product_Name.setText(Name);
        Product_Price.setText(Price);
        Product_Quantity.setText(Quantity);
        Last_Stocked.setText(LastStocked);
    }
}
