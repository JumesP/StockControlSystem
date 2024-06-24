import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.*;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;

public class LoginButtonTest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Homepage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, 900, 650, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }



    @BeforeEach
    public void beforeEachTest() {
        clickOn("#Signin");
    }

    public void testLoginButton(String username, String password, String expected) {
        clickOn("#Username");
        write(username);
        clickOn("#Password");
        write(password);
        clickOn("#Submit");
        FxAssert.verifyThat("#Result", LabeledMatchers.hasText(expected));
    }

    @Test
    public void testLoginButton1() {
        testLoginButton("admin", "admin", "Login successful");
    }

    @Test
    public void testLoginButton2() {
        testLoginButton("user", "user", "Login successful");
    }

    @Test
    public void testLoginButton3() {
        testLoginButton("John", "Smith", "Login failed\nPlease enter a valid Username and Password");
    }
}


//    public <T extends Node> T find(final String query) {
//        return (T) lookup(query).queryAll().iterator().next();
//    }