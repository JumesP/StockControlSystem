package Stock.application.Controllers.MainPages;

import Stock.application.Controllers.SceneController;
import Stock.classes.All_Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static Stock.classes.All_Products.*;
import static Stock.classes.Misc.Clock.sortableDateHyphen;
import static Stock.classes.Misc.Clock.splitDates;
import static Stock.classes.Sales.Sales.getAllSalesDates;

public class TransactionAnalyticsController implements Initializable {
    @FXML Label orderedThisWeek;
    @FXML Label soldThisWeek;
    @FXML Label wasteThisWeek;
    @FXML ChoiceBox<String> productRange;
    @FXML ChoiceBox<String> dateRange;
    @FXML Pane ChartPane;
    LineChart<Number, Number> Chart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productRange.getItems().addAll(IDandProduct());
        dateRange.getItems().addAll(getAllSalesDates());
        productRange.setOnAction(this::generateGraph);
        dateRange.setOnAction(this::generateGraph);

        NumberAxis xAxis = new NumberAxis(1, 7, 1);
        NumberAxis yAxis = new NumberAxis(0, 300, 25);
        Chart = new LineChart<Number,Number>(xAxis,yAxis);

        Chart.prefWidth(650);
        Chart.prefHeight(600);
        Chart.setMinWidth(650);
        Chart.setMinHeight(600);

        xAxis.setLabel("Days of the Week");
        yAxis.setLabel("Amount of Product");

        XYChart.Series sold = new XYChart.Series();
        XYChart.Series deliveries = new XYChart.Series();

        // Sample data, before main data arrives
        deliveries.getData().add(new XYChart.Data(3, 140));
        deliveries.getData().add(new XYChart.Data(4, 150));
        deliveries.getData().add(new XYChart.Data(5, 180));
        sold.getData().add(new XYChart.Data(2, 250));
        sold.getData().add(new XYChart.Data(3, 160));
        sold.getData().add(new XYChart.Data(4, 120));

        sold.setName("Sales");
        deliveries.setName("Deliveries");

        ChartPane.getChildren().add(Chart);

        Chart.getData().add(deliveries);
        Chart.getData().add(sold);
    }


    public void generateGraph(ActionEvent event) {
        // Generate graph on change of productRange and dateRange
        if (productRange.getValue() == null || dateRange.getValue() == null) {
            return;
        }

        int Product_ID = Integer.parseInt(IDandProductSplit(productRange.getValue()).get(0));
        String Product_Name = IDandProductSplit(productRange.getValue()).get(1);

        All_Products product = getProductByID(Product_ID);
        List<String> dates = splitDates(dateRange.getValue());


        int Week_start = sortableDateHyphen(dates.get(0));
        int Week_end = sortableDateHyphen(dates.get(1));

        int ordered = product.listOfDeliveriesBetweenTwoDates(Week_start, Week_end);
        int sales = product.listOfSalesBetweenTwoDates(Week_start, Week_end);

        int orderAveragePerWeek = ordered/7;
        int salesAveragePerWeek = sales/7;

        System.out.println("Ordered: " + ordered);
        System.out.println("Sales: " + sales);

        XYChart.Series sold = new XYChart.Series();
        XYChart.Series deliveries = new XYChart.Series();

        for (int i = 1; i < 8; i++) {
            deliveries.getData().add(new XYChart.Data(i, orderAveragePerWeek));
            sold.getData().add(new XYChart.Data(i, salesAveragePerWeek));
            System.out.println("Day " + i + ": Plotted!");
        }

        sold.setName("Sales");
        deliveries.setName("Deliveries");

        ChartPane.getChildren().clear();
        ChartPane.getChildren().add(Chart);

        Chart.getData().clear();
        Chart.getData().add(deliveries);
        Chart.getData().add(sold);

        orderedThisWeek.setText(ordered + " units");
        soldThisWeek.setText(sales + " units");
        wasteThisWeek.setText((ordered - sales) + " units");
    }

    public void exportToFile(ActionEvent event) {
        // Export data to file
        if (productRange.getValue() == null || dateRange.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText("Export Failed");
            alert.setContentText("Please select a product and date range to export the waste report.");
            alert.showAndWait();
            return;
        }

        int Product_ID = Integer.parseInt(IDandProductSplit(productRange.getValue()).get(0));
        All_Products product = getProductByID(Product_ID);
        String dates = dateRange.getValue();

        if (product.printWasteReport(dates)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText("Waste Report Exported Successfully");
            alert.setContentText("The waste report for " + productRange.getValue() + " between " + dates + " has been exported successfully.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText("Waste Report Export Failed");
            alert.setContentText("The waste report for " + productRange.getValue() + " between " + dates + " could not be exported.");
            alert.showAndWait();
        }
    }

    public void switchToHomepage(ActionEvent event) {
        ChartPane.getChildren().clear();
        Chart.getData().clear();
        SceneController.switchToHomepage(event);
    }
}
