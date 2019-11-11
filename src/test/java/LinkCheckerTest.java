import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class LinkCheckerTest extends BaseTest {

    @ParameterizedTest
    @EnumSource(Locale.class)
    public void checkLocalizedLinks(Locale locale) {

    }
}