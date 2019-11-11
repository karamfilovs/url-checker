import org.junit.jupiter.api.Test;

public class LinkOnlyChecker extends BaseTest {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    @Test
    public void linkTestWacom() {
        FileReader.retrieveLinksFromFile("src/main/resources/us-links.txt")
                .forEach(url -> {
                    PageNavigator.gotoPage(url);
                    PageNavigator.acceptCookies();
                    PageNavigator.takeScreenshot(url);
                });


    }

}