package Stock.classes.Product_Departments;

import Stock.classes.All_Products;
import Stock.classes.Product_Departments.Grocery_Departments.Chilled;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Stock.application.SqliteConnection.Select;
import static Stock.classes.Misc.Clock.getSortableDateInAWeek;

public class Grocery extends All_Products {

    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    private String Parent_Category = "Grocery"; public String getParent_Category() { return Parent_Category; }
    private int Useby_Date; public int getUseby_Date() { return Useby_Date; }

    public Grocery(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, int Department_ID) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID);
        this.Parent_Category = "Grocery";
        this.Useby_Date = getSortableDateInAWeek(Last_Stocked);
    }

    // METHODS
    public void addGrocery(){
        // Add a new grocery product to the database
        query = "insert into products (Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Useby_Date) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.getProduct_Name());
            preparedStatement.setInt(2, super.getProduct_ID());
            preparedStatement.setInt(3, super.getProduct_Restock_Price());
            preparedStatement.setInt(4, super.getProduct_Sale_Price());
            preparedStatement.setInt(5, super.getProduct_Quantity());
            preparedStatement.setInt(6, super.getLast_Stocked());
            preparedStatement.setInt(7, this.Useby_Date);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // STATIC METHODS
    public static List<Grocery> getAllGroceryProducts() {
        query = "select * from products p, departments d where p.Department_ID = d.Department_ID where d.Parent_Category = 'Grocery';";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Grocery> data = new ArrayList<>();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Product_Name"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
