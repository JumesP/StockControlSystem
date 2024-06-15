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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

public class NewProductController {
    NewProductModel newProductModel = new NewProductModel();

    public TextField Product_Name;
    public TextField Product_Quantity;
    public TextField Product_Price;
    public Button UploadImage;
    public Button submit;
    public Label error;

    public void addProduct() {
        // Add product to database
        if (Product_Name.getText().isEmpty() || Product_Quantity.getText().isEmpty() || Product_Price.getText().isEmpty()) {
            error.setText("Please fill in all fields");
            return;
        }
        File selectFile = newProductModel.getImage();

        newProductModel.Add(Product_Name.getText(), Integer.parseInt(Product_Quantity.getText()), Integer.parseInt(Product_Price.getText()));
        try{
            File file = new File("src/main/resources/images/products/" + (newProductModel.generateProductID()-1) + ".png");
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
            newProductModel.setImage(selectFile);
        }
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
