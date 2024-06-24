import Stock.classes.All_Products;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class All_ProductsTest {

    All_Products product = new All_Products("Apple", 0, 1, 2, 1, 20240519, 0);

    @Test
    void getProduct_Name() {
        assertEquals("Apple", product.getProduct_Name());
    }

    @Test
    void getProduct_ID() {
        assertEquals(0, product.getProduct_ID());
    }

    @Test
    void getProduct_Restock_Price() {
        assertEquals(1, product.getProduct_Restock_Price());
    }

    @Test
    void getProduct_Sale_Price() {
        assertEquals(2, product.getProduct_Sale_Price());
    }

    @Test
    void getProduct_Quantity() {
        assertEquals(1, product.getProduct_Quantity());
    }

    @Test
    void getLast_Stocked() {
        assertEquals(20240519, product.getLast_Stocked());
    }

    @Test
    void getDepartment_ID() {
        assertEquals(0, product.getDepartment_ID());
    }

    @Test
    void getViewable_Restock_Price() {
        assertEquals("£1", product.getViewable_Restock_Price());
    }

    @Test
    void getViewable_Sale_Price() {
        assertEquals("£2", product.getViewable_Sale_Price());
    }

    @Test
    void getViewable_Quantity() {
        assertEquals("1 units", product.getViewable_Quantity());
    }

    @Test
    void getViewable_Last_Stocked() {
        assertEquals("19-05-2024", product.getViewable_Last_Stocked());
    }

    @Test
    void setProduct_Quantity() { product.setProduct_Quantity(2); assertEquals(2, product.getProduct_Quantity()); }
}