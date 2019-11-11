import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class LinkCheckerTest extends BaseTest {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    @ParameterizedTest
    @EnumSource(Locale.class)
    public void checkLocalizedLinks(Locale locale) throws Exception {
        PageNavigator.gotoPage("https://pragmatic-qa.com");
        HTTPClient myClient = new HTTPClient();
        myClient.validateUrl(PageNavigator.getAllPageLinks());
        PageNavigator
                .getAllPageLinks()
                .forEach(link -> {
                    if (!link.contains(locale.getLocale())) {
                        System.out.println(ANSI_RED + "Does not contain specified locale:" + locale.getLocale() + ANSI_RESET);
                        System.out.println(link);
                        //Assertions.fail("bad link");
                    }
                });
    }
}
