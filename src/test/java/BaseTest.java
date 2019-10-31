import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    public void beforeEach() {
        PageNavigator.startBrowser();
    }

    @AfterEach
    public void afterEach() {
        PageNavigator.browserQuit();
    }
}
