package Stock.classes.Sales;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static Stock.application.SqliteConnection.Select;

public class Transaction_Items {

    int Product_ID;
    String Product_Name;
    int Product_Quantity;
    String Viewable_Quantity;

    public Transaction_Items(int Product_ID, String Product_Name, int Product_Quantity) {
        this.Product_ID = Product_ID;
        this.Product_Name = Product_Name;
        this.Product_Quantity = Product_Quantity;
        this.Viewable_Quantity = Product_Quantity + " units";
    }

    public int getProduct_ID() { return Product_ID; }; public void setProduct_ID(int Product_ID) { this.Product_ID = Product_ID; }
    public String getProduct_Name() { return Product_Name; }; public void setProduct_Name(String Product_Name) { this.Product_Name = Product_Name; }
    public int getProduct_Quantity() { return Product_Quantity; }; public void setProduct_Quantity(int Product_Quantity) { this.Product_Quantity = Product_Quantity; }
    public String getViewable_Quantity() { return Viewable_Quantity; }; public void setViewable_Quantity(String Viewable_Quantity) { this.Viewable_Quantity = Viewable_Quantity; }

    // STATIC METHODS
    public static List<Transaction_Items> FetchTransactionItemsBySaleID(int ID) {
        String query = "SELECT * FROM Transactions, products WHERE Transactions.Product_ID = products.Product_ID and Sale_ID = " + ID + " ORDER BY Product_Quantity_Sold DESC";
        try (ResultSet results = Select(query)){
            List<Transaction_Items> data = new ArrayList<>();
            while (results.next()) {
                data.add(new Transaction_Items(results.getInt("Product_ID"), results.getString("Product_Name"), results.getInt("Product_Quantity_Sold")));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
