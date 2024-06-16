package Stock.classes.Product_Departments.Non_Foods.Toiletries_and_Beauty_Department;

import Stock.classes.Product_Departments.NonFood;

public class Toilet_Rolls extends NonFood {
    private int Number_of_rolls;

    public Toilet_Rolls(String Product_Name, int Product_ID, Integer Product_Restock_Price, Integer Product_Sale_Price, int Product_Quantity, int Last_Stocked, int Number_of_rolls) {
        super(Product_Name, Product_ID, Product_Restock_Price, Product_Sale_Price, Product_Quantity, Last_Stocked);
        this.Number_of_rolls = Number_of_rolls;
    }
}
