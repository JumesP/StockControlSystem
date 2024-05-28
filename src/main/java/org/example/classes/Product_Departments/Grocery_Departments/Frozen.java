package org.example.classes.Product_Departments.Grocery_Departments;

import org.example.classes.Product_Departments.Grocery;

public class Frozen extends Grocery{
    private Number Storage_Temperature = -19;

    public Frozen(Number Storage_Temperature) {
        super();
        this.Storage_Temperature = Storage_Temperature;
    }
}
