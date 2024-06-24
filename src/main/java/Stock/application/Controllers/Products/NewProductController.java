package Stock.application.Controllers.Products;

import Stock.application.Controllers.SceneController;
import Stock.classes.Misc.Clock;
import Stock.classes.Misc.Image;
import Stock.classes.Product_Departments.Grocery_Departments.Ambient_Department.Chocolate;
import Stock.classes.Product_Departments.Grocery_Departments.Ambient_Department.Crisps;
import Stock.classes.Product_Departments.Grocery_Departments.Ambient_Department.Sweets;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments.Fruits;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments.Meats;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled_Departments.Vegetable;
import Stock.classes.Product_Departments.Grocery_Departments.Frozen_Department.Ice_Cream;
import Stock.classes.Product_Departments.Non_Foods.Toiletries_and_Beauty_Department.Toilet_Rolls;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static Stock.classes.All_Products.*;
import static Stock.classes.Departments.Departments.listOfParentDepertments;

public class NewProductController implements Initializable {
    Clock clock = new Clock();
    Image image = new Image();

    public TextField Product_Name;
    public TextField Product_Quantity;
    public TextField Product_Restock_Price;
    public TextField Product_Sale_Price;
    public ChoiceBox<String> Choicebox1;
    public ChoiceBox<String> Choicebox2;
    public TextField Rolls;
    public Button UploadImage;
    public Button submit;
    public Label error;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rolls.setDisable(true);
        Choicebox1.getItems().addAll(listOfParentDepertments());
        Choicebox1.setOnAction(this::selectDepartment);
        Choicebox2.setDisable(true);
        Choicebox2.setOnAction(this::ToiletRollsAmount);
    }

    public void addProduct() {
        // Add product to database
        if (Product_Name.getText().isEmpty() || Product_Quantity.getText().isEmpty() || Product_Restock_Price.getText().isEmpty() || Product_Sale_Price.getText().isEmpty()) {
            error.setText("Please fill in all fields");
            return;
        }

        File selectFile = image.getImage();

        switch (Choicebox2.getValue()) {
            case "Fruit":
                Fruits fruit = new Fruits(Product_Name.getText(), generateID(), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.date(), 0);
                fruit.addChilled(Choicebox2.getValue());
                break;
            case "Vegetables":
                Vegetable vegetable = new Vegetable(Product_Name.getText(), generateID(), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.date(), 1);
                vegetable.addChilled(Choicebox2.getValue());
                break;
            case "Meat":
                Meats meat = new Meats(Product_Name.getText(), generateID(), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.date(), 2);
                meat.addChilled(Choicebox2.getValue());
                break;
            case "Ice Cream":
                Ice_Cream ice_cream = new Ice_Cream(Product_Name.getText(), generateID(), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.date(), 5);
                ice_cream.addFrozen(Choicebox2.getValue());
                break;
            case "Crisps":
                Crisps crisps = new Crisps(Product_Name.getText(), generateID(), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.date(), 7);
                crisps.addAmbient(Choicebox2.getValue());
                break;
            case "Sweets":
                Sweets sweets = new Sweets(Product_Name.getText(), generateID(), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.date(), 8);
                sweets.addAmbient(Choicebox2.getValue());
                break;
            case "Chocolate":
                Chocolate chocolate = new Chocolate(Product_Name.getText(), generateID(), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.date(), 9);
                chocolate.addAmbient(Choicebox2.getValue());
                break;
            case "Toilet Rolls":
                int rolls = Integer.parseInt(Rolls.getText());
                Toilet_Rolls toilet_rolls = new Toilet_Rolls(Product_Name.getText(), generateID(), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.date(), 10, rolls);
                toilet_rolls.addToiletRolls(Choicebox2.getValue());
                break;
        }

        try{
            File file = new File("src/main/resources/images/products/" + (generateID()-1) + ".png");
            BufferedImage bi = ImageIO.read(selectFile);
            ImageIO.write(bi, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        error.setText("Product added successfully");
    }


    public void selectFile() {
        // Select image file for product
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("SelectImage");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectFile = fileChooser.showOpenDialog(null);
        if (selectFile != null) {
            UploadImage.setText(selectFile.getName() + " selected");
            image.setImage(selectFile);
        }
    }


    public void selectDepartment(ActionEvent event) {
        // provide input boxes for the selected department
        Choicebox2.setDisable(false);
        switch (Choicebox1.getValue()) {
            case "Chilled":
                Choicebox2.getItems().clear();
                Choicebox2.getItems().addAll("Fruit", "Vegetables", "Meat");
                break;
            case "Frozen":
                Choicebox2.getItems().clear();
                Choicebox2.getItems().addAll("Ice Cream");
                break;
            case "Ambient":
                Choicebox2.getItems().clear();
                Choicebox2.getItems().addAll("Crisps", "Sweets", "Chocolate");
                break;
            case "Household", "Entertainment":
                Choicebox2.getItems().clear();
                break;
            case "Toiletries":
                Choicebox2.getItems().clear();
                Choicebox2.getItems().addAll("Toilet Rolls");
                break;
        }
    }


    public void selectCategory(ActionEvent event) {
        // provide input boxes for the selected category
        switch (Choicebox2.getValue()) {
            case "Fruit":
                break;
            case "Vegetables":
                break;
            case "Meat":
                break;
            case "Ice Cream":
                break;
            case "Crisps":
                break;
            case "Sweets":
                break;
            case "Chocolate":
                break;
            case "Toilet Rolls":
                break;
        }
    }


    public void ToiletRollsAmount(ActionEvent event) {
        // provide input boxes for the selected category
        Choicebox1.setDisable(true);
        Rolls.setDisable(!Choicebox2.getValue().equals("Toilet Rolls"));
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }
}
