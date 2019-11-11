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

    public int validateUrl(String url) {
        int statusCode = 0;
        if (verifyUrl(url)) {
            try {
                PageNavigator.gotoPage(url);
                URL myURL = new URL(url);
                HttpURLConnection myConnection = (HttpURLConnection) myURL.openConnection();
                statusCode = myConnection.getResponseCode();
                if (statusCode == 200) {
                    LOGGER.info(url + " - Status message is: " + statusCode);
                } else {
                    LOGGER.debug(url + " - Status message is: " + statusCode);

                }
            } catch (Exception e) {
                LOGGER.error("\n" + "For url - " + url + "" + e.getMessage());
            }
        } else {
            LOGGER.error("Incorrect url: " + url);
        }
        return statusCode;

    }


}

