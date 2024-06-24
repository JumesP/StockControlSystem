package Stock.classes.Product_Departments.Non_Foods.Toiletries_and_Beauty_Department;

import Stock.application.SqliteConnection;
import Stock.classes.Misc.Clock;
import Stock.classes.Product_Departments.NonFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static Stock.application.SqliteConnection.Select;
import static Stock.application.SqliteConnection.connection;

public class Toilet_Rolls extends NonFood {
    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    Clock clock = new Clock();

    static {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

    public int Department_ID = 10;
    public String Department_Name = "Toiletries and Beauty";
    public static String Description = "Soft and strong toilet rolls";
    private int Number_of_rolls;

    public Toilet_Rolls(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, int Department_ID, int Number_of_rolls) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID);
        this.Number_of_rolls = Number_of_rolls;
    }

    public int getNumber_of_rolls() {
        return Number_of_rolls;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public static String getDescription() {
        return Description;
    }

    public void addToiletRolls(String department) {

        int Department_ID = 0;
        if (department.equals("Toilet Rolls")) {
            Department_ID = 10;
        }
        // Add a new fruit product to the database
        query = "insert into products (Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID, Quantity_Per_Pack) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.getProduct_Name());
            preparedStatement.setInt(2, generateID());
            preparedStatement.setInt(3, super.getProduct_Restock_Price());
            preparedStatement.setInt(4, super.getProduct_Sale_Price());
            preparedStatement.setInt(5, super.getProduct_Quantity());
            preparedStatement.setInt(6, clock.sortableDate());
            preparedStatement.setInt(7, this.Department_ID);
            preparedStatement.setInt(8, this.Number_of_rolls);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // STATIC METHODS
    public static Toilet_Rolls getToiletRollsByID(int id) {
        query = "SELECT * FROM Products WHERE Product_ID = " + id;
        try (ResultSet results = Select(query)) {
            if (results.next()) {
                return new Toilet_Rolls(results.getString("Product_Name"), results.getInt("Product_ID"), results.getInt("Product_Restock_Price"), results.getInt("Product_Sale_Price"), results.getInt("Product_Quantity"), results.getInt("Last_Stocked"), results.getInt("Department_ID"), results.getInt("Quantity_Per_Pack"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
