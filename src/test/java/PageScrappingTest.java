import org.junit.jupiter.api.Test;

public class PageScrappingTest extends BaseTest {
    private static final String BASE_URL = "https://www-qa.wacom.com/";

    @Test
    public void scrapePageHyperlinks(){
        PageNavigator.gotoPage(BASE_URL);
        PageNavigator.acceptCookies();
        PageNavigator.getAllPageLinks().forEach(link -> {
            PageNavigator.gotoPage(link);
            PageNavigator.takeScreenshot(link);
        });
    }
}
