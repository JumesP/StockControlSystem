package Stock.classes.Deliveries;

public class Deliveries {
    private Integer Delivery_ID;
    private String Delivery_Name;
    private String Delivery_Date;
    private String Delivery_Company;

    public Deliveries(Integer Delivery_ID, String Delivery_Name, String Delivery_Date, String Delivery_Company) {
        this.Delivery_ID = Delivery_ID;
        this.Delivery_Name = Delivery_Name;
        this.Delivery_Date = Delivery_Date;
        this.Delivery_Company = Delivery_Company;
    }

    public Integer getDelivery_ID() {
        return Delivery_ID;
    }

    public String getDelivery_Name() {
        return Delivery_Name;
    }

    public String getDelivery_Date() {
        return Delivery_Date;
    }

    public String getDelivery_Company() {
        return Delivery_Company;
    }

    public void setDelivery_ID(Integer Delivery_ID) {
        this.Delivery_ID = Integer.valueOf(Delivery_ID);
    }

    public void setDelivery_Name(String Delivery_Name) {
        this.Delivery_Name = Delivery_Name;
    }

    public void setDelivery_Date(String Delivery_Date) {
        this.Delivery_Date = Delivery_Date;
    }

    public void setDelivery_Company(String Delivery_Company) {
        this.Delivery_Company = Delivery_Company;
    }
}
