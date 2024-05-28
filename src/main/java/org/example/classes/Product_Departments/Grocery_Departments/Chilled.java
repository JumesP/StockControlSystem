package org.example.classes.Product_Departments.Grocery_Departments;

import org.example.classes.Product_Departments.Grocery;

public class Chilled extends Grocery {
    private Number Storage_Temperature = 4;

    public Chilled(Number Storage_Temperature) {
        super();
        this.Storage_Temperature = Storage_Temperature;
    }
}
