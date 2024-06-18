package Stock.classes.Product_Departments.Grocery_Departments;

import Stock.classes.Product_Departments.Grocery;

public class Frozen extends Grocery{
    private Number Storage_Temperature = -19;

    public Frozen(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked);
        this.Storage_Temperature = Storage_Temperature;
    }

    // METHODS



    // STATIC METHODS
//    public static Boolean ifReorderable(int ID) {
//        // Frozen products can only be ordered once a month, so check if the product has been ordered in the last week
//
//    }
}
