package Stock.classes.Sales;

import Stock.application.SqliteConnection;
import Stock.classes.All_Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Stock.application.SqliteConnection.Select;

public class Sales {
    static Connection connection;
    static String query;
    static ResultSet resultSet;
    static Statement statement;
    static PreparedStatement preparedStatement;

    static {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    int Sale_ID;
    int Sale_Date;
    int Total_Sale_Profit;

    public Sales(int Sale_Date) {
        this.Sale_ID = generateSalesID();
        this.Sale_Date = Sale_Date; // every sunday, generated by the system a week after the last
        this.Total_Sale_Profit = 0;
    }

    public int getSale_ID() { return Sale_ID; }; public void setSale_ID(int Sale_ID) { this.Sale_ID = Sale_ID; }
    public int getSale_Date() { return Sale_Date; }; public void setSale_Date(int Sale_Date) { this.Sale_Date = Sale_Date; }

    public void addSales() {
        // Add sales to database

        List<All_Products> products = All_Products.getAllProducts();

        for (All_Products product : products) {
            Transactions transaction = new Transactions(this.Sale_ID, product.getProduct_ID());
            transaction.addTransaction();
            this.Total_Sale_Profit += transaction.getTotal_Profit();
        }

        query = "INSERT INTO Sales (Sale_ID, Sale_Date, Total_Sale_Profit) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.Sale_ID);
            preparedStatement.setInt(2, 20240519);
            preparedStatement.setInt(3, this.Total_Sale_Profit);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // STATIC METHODS
    public static int generateSalesID() {
        query = "SELECT COUNT(*) FROM Sales";
        try (ResultSet results = Select(query)) {
            if (results.next()) { return results.getInt(1); }
        } catch (Exception e) {
            e.printStackTrace();
        }; return 0;
    }

    public static int nextSaleDate() {
        query = "SELECT MAX(Sale_Date) FROM Sales";
        try (ResultSet results = Select(query)) {
            if (results.next()) { return results.getInt(1) + 7; }
        } catch (Exception e) {
            e.printStackTrace();
        }; return 20240519;
    }



}