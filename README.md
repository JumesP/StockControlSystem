# Supermarket Stock Control System (SSCS)
is a revolutionary Stock Management system, allowing users to view, add and control quantities of different products sold by the company.

## Features


### Restocking Products
Adding fresh stock to the database is done via deliveries, where the user can input the amount of stock being delivered, This will then update the database with the new stock amount.

### Selling Products
Selling products to customers could have simply been completed using a "-1 Stock" button or an entry for removing stock, but for more realistic functionality, random amounts of stock is generated from the Tills of the store.

This functionality will prove useful for this project for the additional feature, 



## Running the Program

To run the application

<img width="304" alt="Screenshot 2024-06-23 at 13 24 37" src="https://github.com/JumesP/StockControlSystem/assets/118614403/e99f6571-6e3a-4623-b731-361d3d7dec79">



### Tests

When running the program, by default the tests will be disabled to allow the application to run.

To enable the tests, simply uncomment the following line in the build.gradle to run the tests

```
//test {
//    useJUnitPlatform()
//}
```


### Device Functionality

Depending on the operation system being used, different code is required to access the database.

<img width="979" alt="Screenshot 2024-06-06 at 16 07 29" src="https://github.com/JumesP/StockControlSystem/assets/118614403/dea14325-4319-49b6-91b9-3e373a7f81de">

Inside of the SqliteConnections.java file, simply comment out the incorrect operating system and uncomment the correct one to ensure database functionality.

### Image Loading

Images are stored within the Resources folder, placed there when adding a new product the range and called using the Product_ID.

Often images might not load due to unknown reasons relating to the images, when this happens, a placeholder image is displayed.
