package Stock.application.Controllers.Products;

import Stock.application.Controllers.SceneController;
import Stock.classes.All_Products;
import Stock.classes.Product_Departments.Grocery_Departments.Ambient_Department.Chocolate;
import Stock.classes.Product_Departments.Grocery_Departments.Ambient_Department.Crisps;
import Stock.classes.Product_Departments.Grocery_Departments.Ambient_Department.Sweets;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments.Fruits;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments.Meats;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments.Vegetable;
import Stock.classes.Product_Departments.Grocery_Departments.Frozen;
import Stock.classes.Product_Departments.Grocery_Departments.Ambient;
import Stock.classes.Product_Departments.Grocery_Departments.Frozen_Department.Ice_Cream;
import Stock.classes.Product_Departments.Non_Foods.Toiletries_and_Beauty_Department.Toilet_Rolls;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import static Stock.classes.Misc.Clock.formatDateForUser;
import static Stock.classes.Misc.Clock.getSortableDateInAWeek;
import static Stock.classes.Product_Departments.Grocery_Departments.Ambient.getAmbientByID;
import static Stock.classes.Product_Departments.Grocery_Departments.Chilled.getChilledByID;
import static Stock.classes.Product_Departments.Grocery_Departments.Frozen.getFrozenByID;
import static Stock.classes.Product_Departments.Non_Foods.Toiletries_and_Beauty_Department.Toilet_Rolls.getToiletRollsByID;

public class SpecificProductController {
    @FXML private Label Product_Name;
    @FXML private Label Product_Price;
    @FXML private Label Product_Quantity;
    @FXML private Label Last_Stocked;
    @FXML private Label Useby_Date;
    @FXML private Label Description;
    @FXML private Label Unique;
    @FXML private Label Rolls;
    @FXML private Label RollsLabel;
    @FXML private ImageView ProductImage;
    @FXML private Button exportData;

    All_Products product;

    public void setProduct(All_Products product) {
        this.product = product;
        int Product_ID = product.getProduct_ID();
        int DeptId = product.getDepartment_ID();
        RollsLabel.setVisible(false);

        if (DeptId >=0 && DeptId <= 2) {
            Chilled chilled = getChilledByID(Product_ID);
            Unique.setText("Please keep at " + chilled.getStorage_Temperature() + "°C");
        } else if (DeptId == 5) {
            Frozen frozen = getFrozenByID(Product_ID);
            Unique.setText("Please keep at " + frozen.getStorage_Temperature() + "°C");
        } else if (DeptId == 9) {
            Ambient ambient = getAmbientByID(Product_ID);
            Unique.setText("These Contain " + ambient.getSugar_Content() + "g of sugar per 100g");
        } else if (DeptId == 10) {
            Toilet_Rolls toilet_rolls = getToiletRollsByID(Product_ID);
            Unique.setText("Containing " + toilet_rolls.getNumber_of_rolls() + " rolls");
            RollsLabel.setVisible(true);
            Rolls.setText(toilet_rolls.getNumber_of_rolls() + " rolls");
        }

        switch (product.getDepartment_ID()) {
            case 0:
                Description.setText(Fruits.getDescription()); break;
            case 1:
                Description.setText(Vegetable.getDescription()); break;
            case 2:
                Description.setText(Meats.getDescription()); break;
            case 5:
                Description.setText(Ice_Cream.getDescription()); break;
            case 7:
                Description.setText(Crisps.getDescription()); break;
            case 8:
                Description.setText(Sweets.getDescription()); break;
            case 9:
                Description.setText(Chocolate.getDescription()); break;
            case 10:
                Description.setText(Toilet_Rolls.getDescription());break;

        }

        Product_Name.setText(product.getProduct_Name());
        Product_Price.setText("£ " + product.getProduct_Restock_Price().toString());
        Product_Quantity.setText(product.getProduct_Quantity() + " units");
        Last_Stocked.setText(formatDateForUser(product.getLast_Stocked()));
        Useby_Date.setText(formatDateForUser(getSortableDateInAWeek(product.getLast_Stocked())));

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

