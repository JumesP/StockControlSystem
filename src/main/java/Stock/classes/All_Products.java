package Stock.classes;

import Stock.application.SqliteConnection;
import Stock.classes.Misc.Clock;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Stock.application.SqliteConnection.*;
import static Stock.classes.Misc.Clock.formatDateForUser;

public class All_Products{
    static Connection connection;
    static String query;
    static ResultSet resultSet;
    static Statement statement;
    static PreparedStatement preparedStatement;

    static {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    private String Product_Name;
    private int Product_ID;
    private Integer Product_Restock_Price;
    private Integer Product_Sale_Price;
    private int Product_Quantity;
    private int Last_Stocked;
    private String Viewable_Last_Stocked;
    private File image;

    public All_Products(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked) {
        this.Product_Name = Product_Name;
        this.Product_ID = Product_ID;
        this.Product_Restock_Price = Product_Restock_Price;
        this.Product_Sale_Price = Product_Sale_Price;
        this.Product_Quantity = Product_Quantity;
        this.Last_Stocked = Last_Stocked;
        this.Viewable_Last_Stocked = formatDateForUser(Last_Stocked);
        this.image = null;
    }

    static Clock clock = new Clock();

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

    public File getFile() {
        return image;
    }

//    public File getImage() { return image; }

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

    public void setImage(File image) { this.image = image; }

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
        String Date = clock.getDate();
        String Time = clock.getTime();
        try {
            // Create new File
            File file = new File(FilePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            // Write to file
            FileWriter writer = new FileWriter(FilePath);
            writer.write("Stock Details - Dated: " + Date + " at " + Time + "\n");
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
        Update(query);
    }

    public void stockRemoveOne() {
        query = "UPDATE Products SET Product_Quantity = Product_Quantity - 1 WHERE Product_ID = " + this.Product_ID;
        Update(query);
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
        query = "INSERT INTO Products (Product_Name, Product_ID, Product_Price, Product_Quantity, Last_Stocked) VALUES ('" + this.Product_Name + "', " + generateID() + ", " + this.Product_Restock_Price + ", " + this.Product_Sale_Price + ", " + this.Product_Quantity + ", '" + clock.sortableDate() + "')";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // STATIC METHODS
    public static Image ImageByID(int ID) {
        Image image = new Image("/images/products/" + ID + ".png");
        if (image.errorProperty().getValue()) {
            return new Image("/images/placeholder.png");
        } else {
            return image;
        }
    }

    public static List<All_Products> getAllProducts() {
        connection();
        query = "SELECT * FROM Products ORDER BY Product_ID ASC";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<All_Products> data= new ArrayList<>();

            while (resultSet.next()) {
                data.add(new All_Products(resultSet.getString("Product_Name"), resultSet.getInt("Product_ID"), resultSet.getInt("Product_Restock_Price"), resultSet.getInt("Product_Sale_Price"), resultSet.getInt("Product_Quantity"), resultSet.getInt("Last_Stocked")));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<All_Products> searchProducts(String search) {
        if (search.equals("")) { return getAllProducts(); }
        query = "SELECT * FROM Products WHERE Product_Name LIKE '%" + search + "%' ORDER BY Product_ID ASC";
        try (ResultSet results = Select(query)) {
            List<All_Products> data = new ArrayList<>();
            while (results.next()) {
                data.add(new All_Products(results.getString("Product_Name"), results.getInt("Product_ID"), results.getInt("Product_Restock_Price"), results.getInt("Product_Sale_Price"), results.getInt("Product_Quantity"), results.getInt("Last_Stocked")));
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
        try (ResultSet results = Select(query)) {
            if (results.next()) {
                return new All_Products(resultSet.getString("Product_Name"), resultSet.getInt("Product_ID"), resultSet.getInt("Product_Restock_Price"), resultSet.getInt("Product_Sale_Price"), resultSet.getInt("Product_Quantity"), resultSet.getInt("Last_Stocked"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int generateID() {
        query = "SELECT COUNT(Product_ID) FROM Products";
        try (ResultSet results = Select(query)) {
            if (results.next()) { return results.getInt(1); }
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    public static void printAllToFile() throws IOException {
        String FilePath = "Printed Stock Tracker/Stock Details.txt";
        String Date = clock.getDate();
        String Time = clock.getTime();
        query = "SELECT * FROM Products";
        try {
            connection();
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

            writer.write("Stock Details - Dated: " + Date + " at " + Time + "\n");

            while (resultSet.next()) {
                TotalStockCost += resultSet.getInt("Product_Restock_Price") * resultSet.getInt("Product_Quantity");
                TotalStockValue += resultSet.getInt("Product_Sale_Price") * resultSet.getInt("Product_Quantity");
                if (resultSet.getInt("Product_Quantity") < 5) {
                    writer.write("Low Stock: " + resultSet.getString("Product_Name") + "\n");
                }
                // Write to file
                writer.write("Product Name: " + resultSet.getString("Product_Name") + "\n");
                writer.write("Product ID: " + resultSet.getInt("Product_ID") + "\n");
                writer.write("Product Restock Price: " + resultSet.getInt("Product_Restock_Price") + "\n");
                writer.write("Product Sale Price: " + resultSet.getInt("Product_Sale_Price") + "\n");
                writer.write("Product Quantity: " + resultSet.getInt("Product_Quantity") + "\n");
                writer.write("Last Stocked: " + formatDateForUser(resultSet.getInt("Last_Stocked")) + "\n");
                writer.write("Total Stock Cost:  £" + TotalStockCost + "\n");
                writer.write("Total Stock Value: £" + TotalStockValue + "\n");
                writer.write("\n");
            }
            writer.write("========================================\n");
            writer.write("Total Stock Cost:  £" + TotalStockCost + "\n");
            writer.write("Total Stock Value: £" + TotalStockValue + "\n");

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

    public static void addNewProductToDBStatically(String name, int quantity, int restock_price, int sale_price) {
        query = "INSERT INTO Products (Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked) VALUES ('" + name + "', " + generateID() + ", " + restock_price + ", " + sale_price + ", " + quantity + ", '" + clock.sortableDate() + "')";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> listOfDepartments() {
        query = "SELECT * FROM Departments";
        List<String> departments = new ArrayList<>();
        try (ResultSet results = Select(query)) {
            while (results.next()) {
                departments.add(results.getString("Department_Name"));
            }
            return departments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<All_Products> getProductsByDepartment(String department) {
        query = "SELECT * FROM Products p, Departments d WHERE p.Department_ID = d.Department_ID AND d.Department_Name = '" + department + "'";
        try (ResultSet results = Select(query)) {
            List<All_Products> data = new ArrayList<>();
            while (results.next()) {
                data.add(new All_Products(results.getString("Product_Name"), results.getInt("Product_ID"), results.getInt("Product_Restock_Price"), results.getInt("Product_Sale_Price"), results.getInt("Product_Quantity"), results.getInt("Last_Stocked")));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
