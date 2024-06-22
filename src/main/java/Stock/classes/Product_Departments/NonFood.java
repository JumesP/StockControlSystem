package Stock.classes.Product_Departments;

import Stock.classes.All_Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NonFood extends All_Products {

    static Connection connection;
    static String query;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;

    public NonFood(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, int Department_ID) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked, Department_ID);
    }


    // METHODS



    // STATIC METHODS
    public static List<All_Products> getAllNonFoodProducts() {
        query = "SELECT * FROM Products";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<All_Products> data= new ArrayList<>();

            if (resultSet.next()) {
                System.out.println(resultSet.getString("Product_Name"));
                data.add(new NonFood(resultSet.getString("Product_Name"), resultSet.getInt("Product_ID"), resultSet.getInt("Product_Restock_Price"), resultSet.getInt("Product_Sale_Price"), resultSet.getInt("Product_Quantity"), resultSet.getInt("Last_Stocked"), resultSet.getInt("Department_ID")));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
