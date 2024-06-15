package Stock.application.Models;

import Stock.application.SqliteConnection;
import Stock.classes.All_Products;
import javafx.scene.image.Image;

import java.sql.Connection;

public class SpecificProductModel {
    Connection connection;

    public SpecificProductModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) System.exit(1);
    }

//    public All_Products FetchData() {
//        query = "SELECT * FROM products WHERE Product_ID = ?";
//    }

//    public Image FetchImage(int ID) {
//        query = "SELECT * FROM products WHERE Product_ID = ?";
//
//    }
}
