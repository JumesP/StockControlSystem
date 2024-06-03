package Stock.application.Controllers;

import Stock.application.Models.ProductModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Stock.classes.All_Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StockTrackerController implements Initializable {
    public ProductModel productModel = new ProductModel();
    public TextField Search;

    Stage stage;
    Scene scene;
    Parent root;

    @FXML private TableView<All_Products> tableView;
    @FXML private TableColumn<All_Products, String> Product_ID;
    @FXML private TableColumn<All_Products, String> Product_Name;
    @FXML private TableColumn<All_Products, String> Product_Price;
    @FXML private TableColumn<All_Products, String> Product_Quantity;
    @FXML private TableColumn<All_Products, String> Last_Stocked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        Product_ID.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Product_ID"));
        Product_Name.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Product_Name"));
        Product_Price.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Product_Price"));
        Product_Quantity.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Product_Quantity"));
        Last_Stocked.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Last_Stocked"));

        tableView.getItems().setAll(productModel.FetchData());
    }

    public void search(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            tableView.getItems().setAll(productModel.FetchSearchedData(Search.getText()));
        }
    }

    public void viewSpecificProduct(MouseEvent event) throws IOException {
        All_Products product = tableView.getSelectionModel().getSelectedItem();
        System.out.println(product.getProduct_ID());
        System.out.println(product.getProduct_Name());

        // Switch to the specific product page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SpecificProduct.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpecificProductController specificProductController = loader.getController();
        specificProductController.setProduct(product);

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomepage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Homepage.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToNewProduct(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/NewProduct.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
