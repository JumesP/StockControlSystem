package Stock.classes.Deliveries;

public class Order {
    private int Order_ID;
    private int Delivery_ID;
    private int Product_ID;
    private int Product_Quantity;

    public Order(int Order_ID, int Delivery_ID, int Product_ID, int Product_Quantity) {
        this.Order_ID = Order_ID;
        this.Delivery_ID = Delivery_ID;
        this.Product_ID = Product_ID;
        this.Product_Quantity = Product_Quantity;
    }

    public int getOrder_ID() {
        return Order_ID;
    }

    public int getDelivery_ID() {
        return Delivery_ID;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public int getProduct_Quantity() {
        return Product_Quantity;
    }

    public void setOrder_ID(int Order_ID) {
        this.Order_ID = Order_ID;
    }

    public void setDelivery_ID(int Delivery_ID) {
        this.Delivery_ID = Delivery_ID;
    }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setProduct_Quantity(int Product_Quantity) {
        this.Product_Quantity = Product_Quantity;
    }
}
