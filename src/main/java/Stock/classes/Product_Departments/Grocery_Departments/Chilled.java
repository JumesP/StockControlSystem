package Stock.classes.Product_Departments.Grocery_Departments;

import Stock.classes.Product_Departments.Grocery;

public class Chilled extends Grocery {
    private int Storage_Temperature = 4;

    public Chilled(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked);
        this.Storage_Temperature = Storage_Temperature;
    }

    public int getStorage_Temperature() {
        return Storage_Temperature;
    }


    // METHODS
//    public Boolean

    // STATIC METHODS
//    public static Boolean ifReorderable(int ID) {
//        // Chilled products can only be ordered once a week, so check if the product has been ordered in the last week
//
//    }
}
