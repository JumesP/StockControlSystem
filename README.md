# Supermarket Stock Control System (SSCS)
is a revolutionary Stock Management system, allowing users to view, add and control quantities of different products sold by the company.

## Features

### Restocking Products
Adding fresh stock to the database is done via deliveries, where the user can input the amount of stock being delivered, This will then update the database with the new stock amount.

### Selling Products
Selling products to customers could have simply been completed using a "-1 Stock" button or an entry for removing stock, but for more realistic functionality, random amounts of stock is generated from the Tills of the store.

This functionality will prove useful for this project for the additional feature, 

### Data Visualisation

As the application is designed to be used over a long period of time, the ability to view the data in a visual format is formatted as such.

Currently a button collects sales data based on the amount of products avaliable, this data is then displayed in a bar chart as sold, spaced out evenly thoroughout the week.
Equally the delivery data is spread out over the week. This is designed as such so that the client can order a shipment on monday, and as it gones out of stock on sunday, it will reflect within the waste.

All functions can be adjusted once the data starts to be collected, and the data can be viewed in a more detailed format. but for now, the current design is to display functionality.

## Running the Program

To run the application using Intellij, simply select the elephant on the side of the application.
Select **build**, once built, select **run** to run the application.

one ran, the application will open up to the main menu, where the user can select the desired operation.

if any errors occur, simply attempt the stage you were at again, if the error persists, please contact the developer.

<img width="304" alt="Screenshot 2024-06-23 at 13 24 37" src="https://github.com/JumesP/StockControlSystem/assets/118614403/e99f6571-6e3a-4623-b731-361d3d7dec79">

## Tests

When running the program, by default the tests will be disabled to allow the application to run.

To enable the tests, simply uncomment the following line in the build.gradle to run the tests

```
//test {
//    useJUnitPlatform()
//}
```


## Device Functionality

Depending on the operation system being used, different code is required to access the database.

<img width="979" alt="Screenshot 2024-06-06 at 16 07 29" src="https://github.com/JumesP/StockControlSystem/assets/118614403/dea14325-4319-49b6-91b9-3e373a7f81de">

Inside of the SqliteConnections.java file, simply comment out the incorrect operating system and uncomment the correct one to ensure database functionality.

## Image Loading

Images are stored within the Resources folder, placed there when adding a new product the range and called using the Product_ID.

Often images might not load due to unknown reasons relating to the uploads, when this happens, a placeholder image is displayed, and the image will be dispayed upon refresh
