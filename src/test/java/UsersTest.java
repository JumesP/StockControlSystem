import Stock.classes.Users.Users;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UsersTest {

    Users user = new Users("admin", "admin");

    @Test
    void getUsername() {
        assertEquals("admin", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("admin", user.getPassword());
    }

    @Test
    void setUsername() {
        user.setUsername("user");
        assertEquals("user", user.getUsername());
    }

    @Test
    void setPassword() {
        user.setPassword("user");
        assertEquals("user", user.getPassword());
    }

    @Test
    void isLogin() {
        user.setUsername("admin");
        user.setPassword("admin");
        assertTrue(user.isLogin());
    }

    @Test
    void isAdmin() {
        user.setUsername("admin");
        user.setPassword("admin");
        assertEquals("admin", Users.isAdmin());
    }
}