package Stock.application.Controllers;

import Stock.classes.All_Products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
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
        dateRange.getItems().addAll("13-05-2024 - 19-05-2024", "20-05-2024 - 26-05-2024", "27-05-2024 - 02-06-2024");
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

//        Chart.getData().clear();

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

//        for (int i = Week_start; i < Week_end; i++) {
//            int waste = product.listOfWasteBetweenTwoDates(i, i+1);
//        }

        XYChart.Series sold = new XYChart.Series();
        XYChart.Series deliveries = new XYChart.Series();

        for (int i = 0; i < 7; i++) {
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
    }

    public void switchToHomepage(ActionEvent event) {
        SceneController.switchToHomepage(event);
    }


}
