package Stock.classes.Sales;

import Stock.application.SqliteConnection;
import Stock.classes.All_Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static Stock.classes.All_Products.bulkDeleteStock;

public class Transactions {
    static Connection connection;
    static String query;
    static ResultSet resultSet;
    static Statement statement;
    static PreparedStatement preparedStatement;

    static {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    int Transaction_ID;
    int Sale_ID;
    int Product_ID;
    int Product_Quantity_Sold;
    int Total_Profit;

    public Transactions(int Sale_ID, int Product_ID) {
        this.Transaction_ID = generateTransactionID();
        this.Sale_ID = Sale_ID;
        this.Product_ID = Product_ID;
        this.Product_Quantity_Sold = 0;
        this.Total_Profit = 0;
    }

//    public int getTransaction_ID() { return Transaction_ID; }; public void setTransaction_ID(int Transaction_ID) { this.Transaction_ID = Transaction_ID; }
//    public int getSale_ID() { return Sale_ID; }; public void setSale_ID(int Sale_ID) { this.Sale_ID = Sale_ID; }
//    public int getProduct_ID() { return Product_ID; }; public void setProduct_ID(int Product_ID) { this.Product_ID = Product_ID; }
//    public int getProduct_Quantity() { return Product_Quantity; }; public void setProduct_Quantity(int Product_Quantity) { this.Product_Quantity = Product_Quantity; }
    public int getTotal_Profit() { return Total_Profit; }; public void setTotal_Profit(int Total_Profit) { this.Total_Profit = Total_Profit; }



    public void addTransaction() {
        // Add transaction to database
        generateTransaction();

        query = "INSERT INTO Transactions (Transaction_ID, Sale_ID, Product_ID, Product_Quantity_Sold, Total_Profit) VALUES (?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, this.Transaction_ID);
            preparedStatement.setInt(2, this.Sale_ID);
            preparedStatement.setInt(3, this.Product_ID);
            preparedStatement.setInt(4, this.Product_Quantity_Sold);
            preparedStatement.setInt(5, this.Total_Profit);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Transaction ID: " + this.Transaction_ID);
        System.out.println("Sale ID: " + this.Sale_ID);
        System.out.println("Product ID: " + this.Product_ID);
        System.out.println("Product Quantity Sold: " + this.Product_Quantity_Sold);
        System.out.println("Total Profit: " + this.Total_Profit);
    }


    public void generateTransaction() {
        // Generate a weeks worth of sale data for one product
        All_Products product = All_Products.getProductByID(this.Product_ID);

        int soldQuantity = (int) (Math.random() * product.getProduct_Quantity());
        int profit = product.getProduct_Sale_Price() * soldQuantity;


        System.out.println("========================================");
        System.out.println("Quantity of " + product.getProduct_Name() + " Before Sale: " + product.getProduct_Quantity());
        System.out.println("Quantity of " + product.getProduct_Name() + " Sold: " + soldQuantity);
        System.out.println("Quantity of " + product.getProduct_Name() + " After Sale: " + (product.getProduct_Quantity() - soldQuantity));
        System.out.println("Profit from " + product.getProduct_Name() + " Sold: " + (product.getProduct_Sale_Price() * soldQuantity));
        System.out.println("========================================");


        this.Product_Quantity_Sold = soldQuantity;
        this.Total_Profit = profit;

//        bulkDeleteStock(this.Product_ID, soldQuantity);
    }

    public static int generateTransactionID() {
        query = "SELECT COUNT(*) FROM Transactions";
        try (ResultSet results = SqliteConnection.Select(query)) {
            if (results.next()) { return results.getInt(1); }
        } catch (Exception e) {
            e.printStackTrace();
        }; return 0;
    }

}
