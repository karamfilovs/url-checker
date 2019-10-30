import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class LinkCheckerTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(Locale.class)
    public void checkUSLinks(Locale locale) {
        PageNavigator.gotoPage("https://pragmatic.bg");
        PageNavigator
                .getAllPageLinks()
                .forEach(link -> {
                    if (!link.contains(Locale.US.getLocale())) {
                        System.out.println("Does not contain specified locale:" + locale.US.getLocale());
                        System.out.println(link);
                        //Assertions.fail("bad link");
                    }
                });
    }
}
