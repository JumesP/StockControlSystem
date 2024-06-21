package Stock.application.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import Stock.classes.All_Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Stock.classes.All_Products.*;
import static Stock.classes.Departments.Departments.listOfDepartments;

public class StockTrackerController implements Initializable {
    public TextField Search;
    public Button add;

    Stage stage;
    Scene scene;
    Parent root;

    @FXML private TableView<All_Products> tableView;
    @FXML private TableColumn<All_Products, String> Product_ID;
    @FXML private TableColumn<All_Products, String> Product_Name;
    @FXML private TableColumn<All_Products, String> Product_Restock_Price;
    @FXML private TableColumn<All_Products, String> Product_Sale_Price;
    @FXML private TableColumn<All_Products, String> Product_Quantity;
    @FXML private TableColumn<All_Products, String> Last_Stocked;
    @FXML private ChoiceBox<String> Departments;
    @FXML private Button exportData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        Product_ID.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Product_ID"));
        Product_Name.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Product_Name"));
        Product_Restock_Price.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Viewable_Restock_Price"));
        Product_Sale_Price.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Viewable_Sale_Price"));
        Product_Quantity.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Viewable_Quantity"));
        Last_Stocked.setCellValueFactory(new PropertyValueFactory<All_Products, String>("Viewable_Last_Stocked"));

        tableView.getItems().setAll(getAllProducts());

        File file = new File("src/main/java/Stock/backend/cookie.txt");
        Scanner scanner = null;
        try { scanner = new Scanner(file); } catch (FileNotFoundException e) { throw new RuntimeException(e); }

        if (scanner.hasNext()) {
            String data = scanner.nextLine();
            if (!data.equals("admin")) {
                add.setDisable(true);
            }
        }

        List<String> departments = listOfDepartments();
        departments.add(0, "All Departments");
        Departments.getItems().addAll(departments);
        Departments.setOnAction(this::switchDepartment);
    }

    public void search(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            System.out.println(Search.getText());
            tableView.getItems().setAll(searchProducts(Search.getText()));
        }
    }

    public void exportData(ActionEvent event) {
        try {
            printAllToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exportData.setText("Stock Data Exported");
    }

    public void viewSpecificProduct(MouseEvent event) throws IOException {
        All_Products product = tableView.getSelectionModel().getSelectedItem();

        // Switch to the specific product page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SpecificProduct.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpecificProductController specificProductController = loader.getController();
        specificProductController.setProduct(product);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchDepartment(ActionEvent event) {
        System.out.println("Switching department");
        // get data from menu
        String department = Departments.getValue().toString();

        if (department == null) {
            tableView.getItems().setAll(getAllProducts());
        } else if (department.equals("All Departments")) {
            tableView.getItems().setAll(getAllProducts());
        } else {
            tableView.getItems().setAll(getProductsByDepartment(department));
        }
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }

    public void switchToNewProduct(ActionEvent event) {
        SceneController.switchToNewProduct(event);
    }


}
