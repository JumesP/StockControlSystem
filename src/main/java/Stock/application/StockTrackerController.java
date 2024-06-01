package Stock.application;

import Stock.application.ProductModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Stock.classes.All_Products;
import Stock.classes.Product_Departments.Grocery_Departments.Ambient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class StockTrackerController implements Initializable {
        public ProductModel productModel = new ProductModel();

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

            System.out.println(productModel.FetchData());

            tableView.getItems().setAll(productModel.FetchData());
        }



//        private List<products> productsList = productModel.FetchData();
}
