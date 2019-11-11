import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPClient.class);


    private boolean verifyUrl(String url) {
        String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher m = pattern.matcher(url);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public void validateUrl(List<String> urls) throws Exception {
        System.out.println("Checking urls");
        urls.forEach((String url) -> {
            if (verifyUrl(url)) {
                try {
                    PageNavigator.gotoPage(url);
                    URL myURL = new URL(url);
                    HttpURLConnection myConnection = (HttpURLConnection) myURL.openConnection();
                    if (myConnection.getResponseCode() == URLStatus.HTTP_OK.getStatusCode()) {
                        LOGGER.info("\n" + url + "****** Status message is : "
                                + URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode()));
                    } else {
                        LOGGER.debug(("\n" + url + " ****** Status message is : "
                                + URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode())));

                    }
                } catch (Exception e) {
                    LOGGER.error("\n" + "For url- " + url + "" + e.getMessage());
                }
            } else {
                System.out.println("\n" + "Incorrect url:" + url);
            }
        });
    }


}

