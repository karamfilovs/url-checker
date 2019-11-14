import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LinkCheckerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkCheckerTest.class);

    @ParameterizedTest
    @EnumSource(Locale.class)
    public void checkLocalizedLinks(Locale locale) {
        LOGGER.info("*********************** Starting url checks for locale: " + locale + " ***********************");
        List<String> allUrls = FileReader.retrieveLinksFromFile("src/main/resources/us-links.txt");
        allUrls.forEach(url -> {
            url = url.replace("en-us", locale.getLocale());
            PageNavigator.gotoPage(url);
            PageNavigator.acceptCookies();
            PageNavigator.takeScreenshot(url);
            HTTPClient httpClient = new HTTPClient();
            int statusCode = httpClient.validateUrl(url);
            LOGGER.info("***********************");
            Assertions.assertTrue(statusCode <= 400, "Status code is BAD: " + statusCode);
        });


    }
}