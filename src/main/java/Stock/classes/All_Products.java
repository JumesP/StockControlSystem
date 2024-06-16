package Stock.classes;

import Stock.application.SqliteConnection;
import Stock.classes.Misc.Clock;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class All_Products {
    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    private String Product_Name;
    private int Product_ID;
    private Integer Product_Restock_Price;
    private Integer Product_Sale_Price;
    private int Product_Quantity;
    private int Last_Stocked;
    private String Viewable_Last_Stocked;


    public static void connection() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }


    public All_Products(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked) {
        this.Product_Name = Product_Name;
        this.Product_ID = Product_ID;
        this.Product_Restock_Price = Product_Restock_Price;
        this.Product_Sale_Price = Product_Sale_Price;
        this.Product_Quantity = Product_Quantity;
        this.Last_Stocked = Last_Stocked;
        this.Viewable_Last_Stocked = Clock.formatDateForUser(Last_Stocked);

        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    // GETTERS
    public String getProduct_Name() {
        return Product_Name;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public Integer getProduct_Restock_Price() {
        return Product_Restock_Price;
    }

    public Integer getProduct_Sale_Price() {
        return Product_Sale_Price;
    }

    public int getProduct_Quantity() {
        return Product_Quantity;
    }

    public int getLast_Stocked() {
        return Last_Stocked;
    }

    public String getViewable_Last_Stocked() {
        return Viewable_Last_Stocked;
    }

//    public String getViewable_Last_Stocked() { return Viewable_Last_Stocked; }

    // SETTERS

//    public void setProduct_Name(String Product_Name) { this.Product_Name = Product_Name; }

//    public void setProduct_ID(Number Product_ID) { this.Product_ID = Product_ID; }

//    public void setProduct_Restock_Price(Number Product_Price) { this.Product_Restock_Price = Product_Price; }

//    public void setProduct_Sale_Price(Number Product_Price) {this.Product_Sale_Price = Product_Price;}

    public void setProduct_Quantity(int Product_Quantity) {
        this.Product_Quantity = Product_Quantity;
    }

//    public void setLast_Stocked(String Last_Stocked) {this.Last_Stocked = Last_Stocked;}

    public void setViewable_Last_Stocked(String Viewable_Last_Stocked) {
        this.Viewable_Last_Stocked = Viewable_Last_Stocked;
    }

    // METHODS

    public Image getImage() {
        Image image = new Image("/images/products/" + this.getProduct_ID() + ".png");
        if (image.errorProperty().getValue()) {
            return new Image("/images/placeholder.png");
        } else {
            return image;
        }
    }

    public String ImagePath() {
        return "/images/products/" + this.getProduct_ID() + ".png";
    }

    public void printOneToFile() {
        String FilePath = "Printed Stock Tracker/Products/" + this.Product_Name + ".txt";
        try {
            // Create new File
            File file = new File(FilePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            // Write to file
            FileWriter writer = new FileWriter(FilePath);
            writer.write("Product Name: " + this.Product_Name + "\n");
            writer.write("Product ID: " + this.Product_ID + "\n");
            writer.write("Product Restock Price: " + this.Product_Restock_Price + "\n");
            writer.write("Product Sale Price: " + this.Product_Sale_Price + "\n");
            writer.write("Product Quantity: " + this.Product_Quantity + "\n");
            writer.write("Last Stocked: " + this.Last_Stocked + "\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stockAddOne() {
        query = "UPDATE Products SET Product_Quantity = Product_Quantity + 1 WHERE Product_ID = " + this.Product_ID;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void StockRemoveOne() {
        query = "UPDATE Products SET Product_Quantity = Product_Quantity - 1 WHERE Product_ID = " + this.Product_ID;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bulkDeleteStock(int amount) {
        query = "UPDATE Products SET Product_Quantity = Product_Quantity - " + amount + " WHERE Product_ID = " + this.Product_ID;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bulkAddStock(int amount) {
        query = "UPDATE Products SET Product_Quantity = Product_Quantity + " + amount + " WHERE Product_ID = " + this.Product_ID;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewProductToDB() {
        query = "INSERT INTO Products (Product_Name, Product_ID, Product_Price, Product_Quantity, Last_Stocked) VALUES ('" + this.Product_Name + "', " + this.Product_ID + ", " + this.Product_Restock_Price + ", " + this.Product_Sale_Price + ", " + this.Product_Quantity + ", '" + this.Last_Stocked + "')";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // STATICS
    public static List<All_Products> getAllProducts() {
        connection();
        query = "SELECT * FROM Products ORDER BY Product_Name ASC";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<All_Products> data= new ArrayList<>();

            while (resultSet.next()) {
                data.add(new All_Products(resultSet.getString("Product_Name"), resultSet.getInt("Product_ID"), resultSet.getInt("Product_Restock_Price"), resultSet.getInt("Product_Sale_Price"), resultSet.getInt("Product_Quantity"), resultSet.getInt("Last_Stocked")));
            }
            connection.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<All_Products> searchProducts(String search) {
        connection();
        query = "SELECT * FROM Products WHERE Product_Name LIKE '%" + search + "%' ORDER BY Product_Name ASC";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<All_Products> data= new ArrayList<>();

            if (resultSet.next()) {
                System.out.println(resultSet.getString("Product_Name"));

                data.add(new All_Products(resultSet.getString("Product_Name"), resultSet.getInt("Product_ID"), resultSet.getInt("Product_Restock_Price"), resultSet.getInt("Product_Sale_Price"), resultSet.getInt("Product_Quantity"), resultSet.getInt("Last_Stocked")));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static All_Products getProductByID(int id) {
        query = "SELECT * FROM Products WHERE Product_ID = " + id;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return new All_Products(resultSet.getString("Product_Name"), resultSet.getInt("Product_ID"), resultSet.getInt("Product_Restock_Price"), resultSet.getInt("Product_Sale_Price"), resultSet.getInt("Product_Quantity"), resultSet.getInt("Last_Stocked"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int generateID() {
        query = "SELECT MAX(Product_ID) FROM Products";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return resultSet.getInt("Product_ID") + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void printAllToFile() throws IOException {
        String FilePath = "Printed Stock Tracker/Stock Details.txt";

        query = "SELECT * FROM Products";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<All_Products> data= new ArrayList<>();
            int TotalStockCost = 0;
            int TotalStockValue = 0;

            // Create new File
            File file = new File(FilePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }

            FileWriter writer = new FileWriter(FilePath);

            while (resultSet.next()) {
                TotalStockCost += resultSet.getInt("Product_Restock_Price") * resultSet.getInt("Product_Quantity");
                TotalStockValue += resultSet.getInt("Product_Sale_Price") * resultSet.getInt("Product_Quantity");
                if (resultSet.getInt("Product_Quantity") < 5) {
                    writer.write("Low Stock: " + resultSet.getString("Product_Name") + "\n");
                }
                // Write to file
                writer.write("Product Name: " + resultSet.getString("Product_Name") + "\n");
                writer.write("Product ID: " + resultSet.getInt("Product_ID") + "\n");
                writer.write("Product Price: " + resultSet.getInt("Product_Price") + "\n");
                writer.write("Product Quantity: " + resultSet.getInt("Product_Quantity") + "\n");
                writer.write("Last Stocked: " + resultSet.getString("Last_Stocked") + "\n");
                writer.write("\n");
            }
            writer.write("========================================\n");
            writer.write("Total Stock Cost: " + TotalStockCost + "\n");
            writer.write("Total Stock Value: " + TotalStockValue + "\n");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bulkDeleteStock(int ID, int amount) {
        query = "UPDATE Products SET Product_Quantity = Product_Quantity - " + amount + " WHERE Product_ID = " + ID;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bulkAddStock(int ID, int amount) {
        query = "UPDATE Products SET Product_Quantity = Product_Quantity + " + amount + " WHERE Product_ID = " + ID;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
