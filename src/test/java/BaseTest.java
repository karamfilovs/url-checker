import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeAll
    public static void beforeAll() {
        PageNavigator.startBrowser();
    }

    @AfterAll
    public static void afterAll() {
        PageNavigator.browserQuit();
    }
}
