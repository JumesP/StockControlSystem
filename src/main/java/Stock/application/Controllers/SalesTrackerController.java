package Stock.application.Controllers;

import Stock.classes.All_Products;
import Stock.classes.Deliveries.Deliveries;
import Stock.classes.Sales.Sales;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Stock.classes.Misc.Clock.formatDateForUser;
import static Stock.classes.Sales.Sales.generateSalesID;
import static Stock.classes.Sales.Sales.nextSaleDate;

public class SalesTrackerController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;

    @FXML Button newSale;
    @FXML Label generatingDate;
    @FXML TableView<Sales> tableView;
    @FXML TableColumn<Sales, String> Sale_ID;
    @FXML TableColumn<Sales, String> Sale_Date;
    @FXML TableColumn<Sales, String> Total_Profit;
    @FXML TableColumn<Sales, String> Most_Sold_Products;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generatingDate.setText(formatDateForUser(nextSaleDate()));

        Sale_ID.setCellValueFactory(new PropertyValueFactory<Sales, String>("Sale_ID"));
        Sale_Date.setCellValueFactory(new PropertyValueFactory<Sales, String>("Viewable_Sales_Date"));
        Total_Profit.setCellValueFactory(new PropertyValueFactory<Sales, String>("Viewable_Total_Profit"));
        Most_Sold_Products.setCellValueFactory(new PropertyValueFactory<Sales, String>("Most_Sold_Products"));

        tableView.getItems().setAll(Sales.getAllSales());
    }


    public void generateSale() {
        int saleID = generateSalesID();
        int saleDate = nextSaleDate();
        Sales sale = new Sales(saleID, saleDate);
        sale.addSales();

        System.out.println("Sale generated for " + saleDate);

        tableView.getItems().setAll(Sales.getAllSales());
    }

    public void viewSpecificSale(MouseEvent event) throws IOException {
        Sales sale = tableView.getSelectionModel().getSelectedItem();

        // Switch to the specific delivery page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SpecificSale.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpecificSaleController specificSaleController = loader.getController();
        specificSaleController.setSale(sale);

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }


}
