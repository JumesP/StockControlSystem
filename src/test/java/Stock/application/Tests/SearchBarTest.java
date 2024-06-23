package Stock.application.Tests;

import Stock.classes.All_Products;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.query.NodeQuery;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchBarTest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/StockTracker.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, 900, 650, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void beforeEachTest() {
        clickOn("#Search");
    }

    public void testSearchBar(String search, String expected) {
        write(search);
        push(KeyCode.ENTER);
        // get the table
        TableView<All_Products> table = lookup("#tableView").queryAs(TableView.class);
        // get the items in the table
        ObservableList<All_Products> items = table.getItems();
        // get the first item
        All_Products firstProduct = items.get(0);
        // check if the name of the first product matches the search string
        assertEquals(expected, firstProduct.getProduct_Name());
    }

    @Test
    public void testSearchBar1() {
        testSearchBar("Apple", "Apple");
    }

    @Test
    public void testSearchBar2() {
        testSearchBar("Banana", "Banana");
    }

    @Test
    public void testSearchBar3() {
        // Test for Upper Case Sensitivity
        testSearchBar("BANANA", "Banana");
    }

    @Test
    public void testSearchBar4() {
        // Test for Lower Case Sensitivity
        testSearchBar("banana", "Banana");
    }

    @Test
    public void testSearchBar5() {
        //  Test for partial search
        testSearchBar("pple", "Apple");
    }
}
