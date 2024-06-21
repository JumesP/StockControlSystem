package Stock.classes.Deliveries;

import Stock.application.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Stock.application.SqliteConnection.Select;

public class Ordered_Items {

    private int Product_ID;
    private String Product_Name;
    private int Product_Quantity;
    private String Viewable_Quantity;

    public Ordered_Items (int Product_ID, String Product_Name, int Product_Quantity) {
        this.Product_ID = Product_ID;
        this.Product_Name = Product_Name;
        this.Product_Quantity = Product_Quantity;
        this.Viewable_Quantity = Product_Quantity + " units";
    }


    public int getProduct_ID() {
        return Product_ID;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public int getProduct_Quantity() {
        return Product_Quantity;
    }

    public String getViewable_Quantity() { return Viewable_Quantity; }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public void setProduct_Quantity(int Product_Quantity) {
        this.Product_Quantity = Product_Quantity;
    }


    // STATIC METHODS
    public static List<Ordered_Items> FetchOrderedItemsByDeliveryID(int ID) {
        String query = "SELECT * FROM Orders WHERE Delivery_ID = " + ID + " ORDER BY Product_Quantity DESC";
        try (ResultSet results = Select(query)){
            List<Ordered_Items> data = new ArrayList<>();

            while (results.next()) {
                data.add(new Ordered_Items(results.getInt("Product_ID"), results.getString("Product_Name"), results.getInt("Product_Quantity")));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
