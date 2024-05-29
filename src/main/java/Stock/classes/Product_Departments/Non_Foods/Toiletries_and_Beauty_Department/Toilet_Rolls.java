package Stock.classes.Product_Departments.Non_Foods.Toiletries_and_Beauty_Department;

import Stock.classes.Product_Departments.NonFood;

public class Toilet_Rolls extends NonFood {
    private Number Number_of_rolls;

    public Toilet_Rolls(String Product_Name, Number Product_ID, Number Product_Price, Number Product_Quantity, String Last_Stocked, Number Number_of_rolls) {
        super(Product_Name, Product_ID, Product_Price, Product_Quantity, Last_Stocked);
        this.Number_of_rolls = Number_of_rolls;
    }
}
