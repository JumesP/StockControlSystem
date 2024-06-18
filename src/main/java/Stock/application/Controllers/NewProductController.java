package Stock.application.Controllers;

import Stock.classes.All_Products;
import Stock.classes.Misc.Clock;
import Stock.classes.Misc.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static Stock.classes.All_Products.*;

public class NewProductController implements Initializable {
    Clock clock = new Clock();
    Image image = new Image();

    public TextField Product_Name;
    public TextField Product_Quantity;
    public TextField Product_Restock_Price;
    public TextField Product_Sale_Price;
    public ChoiceBox<String> Choicebox1;
    public ChoiceBox<String> Choicebox2;
    public Button UploadImage;
    public Button submit;
    public Label error;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Choicebox1.getItems().addAll(listOfDepartments());
        Choicebox1.setOnAction(this::selectDepartment);
        Choicebox2.getItems().addAll("Category 1", "Category 2", "Category 3");
    }

    public void addProduct() {
        // Add product to database
        if (Product_Name.getText().isEmpty() || Product_Quantity.getText().isEmpty() || Product_Restock_Price.getText().isEmpty() || Product_Sale_Price.getText().isEmpty()) {
            error.setText("Please fill in all fields");
            return;
        }

        File selectFile = image.getImage();

        addNewProductToDBStatically(Product_Name.getText(), Integer.parseInt(Product_Quantity.getText()), Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()));

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
        switch (Choicebox1.getValue()) {
            case "Grocery":
                Choicebox2.getItems().clear();
                Choicebox2.getItems().addAll("Chilled", "Frozen", "Fresh");
                break;
            case "Ambient":
                Choicebox2.getItems().clear();
                Choicebox2.getItems().addAll("Cleaning", "Toiletries", "Kitchen");
                break;
        }
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }
}
