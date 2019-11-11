import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("log.name", "wacom_link_checker");
        PageNavigator.startBrowser();
    }

    @AfterAll
    public static void afterAll() {
        PageNavigator.browserQuit();
    }
}
