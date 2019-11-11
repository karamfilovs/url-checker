import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

public class LinkCheckerTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(Locale.class)
    public void checkLocalizedLinks(Locale locale) {
        List<String> allUrls = FileReader.retrieveLinksFromFile("src/main/resources/test.txt");
        allUrls.forEach(url -> {
            url = url.replace("en-us", locale.getLocale());
            PageNavigator.gotoPage(url);
            PageNavigator.acceptCookies();
            PageNavigator.takeScreenshot(url);
            HTTPClient httpClient = new HTTPClient();
            int statusCode = httpClient.validateUrl(url);
            System.out.println("***********************");
            Assertions.assertTrue(statusCode <= 400, "Status code is BAD: " + statusCode);
        });



    }
}