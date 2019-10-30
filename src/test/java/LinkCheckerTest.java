import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class LinkCheckerTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(Locale.class)
    public void checkUSLinks(Locale locale) throws Exception {
        PageNavigator.gotoPage("https://pragmatic.bg");
        HTTPClient myClient = new HTTPClient();
        myClient.validateUrl(PageNavigator.getAllPageLinks());
        PageNavigator
                .getAllPageLinks()
                .forEach(link -> {
                    if (!link.contains(locale.getLocale())) {
                        System.out.println("Does not contain specified locale:" + locale.getLocale());
                        System.out.println(link);
                        //Assertions.fail("bad link");
                    }
                });
    }
}
