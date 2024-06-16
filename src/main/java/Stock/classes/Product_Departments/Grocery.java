package Stock.classes.Product_Departments;

import Stock.classes.All_Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Grocery extends All_Products {

    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    private String Parent_Category = "Grocery"; public String getParent_Category() { return Parent_Category; }
    private String Useby_Date; public String getUseby_Date() { return Useby_Date; }

    public Grocery(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, String Useby_Date) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked);
        this.Parent_Category = "Grocery";
        this.Useby_Date = Useby_Date;
    }

    // METHODS



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
