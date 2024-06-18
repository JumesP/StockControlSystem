package Stock.application.Controllers;

import Stock.classes.All_Products;
import Stock.classes.Misc.Clock;
import Stock.classes.Misc.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import static Stock.classes.All_Products.addNewProductToDBStatically;
import static Stock.classes.All_Products.generateID;

public class NewProductController {
//    NewProductModel newProductModel = new NewProductModel();
//    All_Products products = new All_Products();
    Clock clock = new Clock();
    Image image = new Image();

    public TextField Product_Name;
    public TextField Product_Quantity;
    public TextField Product_Restock_Price;
    public TextField Product_Sale_Price;
    public Button UploadImage;
    public Button submit;
    public Label error;

//    All_Products products = new All_Products("a", 10000, 1, 2, 1, clock.sortableDate());
//    All_Products products;

    public void addProduct() {
        // Add product to database
        if (Product_Name.getText().isEmpty() || Product_Quantity.getText().isEmpty() || Product_Restock_Price.getText().isEmpty() || Product_Sale_Price.getText().isEmpty()) {
            error.setText("Please fill in all fields");
            return;
        }
//        All_Products products = new All_Products(Product_Name.getText(), 0, Integer.parseInt(Product_Restock_Price.getText()), Integer.parseInt(Product_Sale_Price.getText()), Integer.parseInt(Product_Quantity.getText()), clock.sortableDate());
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

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }
}
