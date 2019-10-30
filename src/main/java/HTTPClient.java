import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPClient {
    private String failedURLS = "";
    private String succeededURLS = "";
    private String incorrectURLS = "";

    public String getFailedURLS() {
        return failedURLS;
    }

    public String getSucceededURLS() {
        return succeededURLS;
    }


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
        urls.forEach((String url) -> {
            if (verifyUrl(url)) {
                try {
                    URL myURL = new URL(url);
                    HttpURLConnection myConnection = (HttpURLConnection) myURL.openConnection();
                    if (myConnection.getResponseCode() == URLStatus.HTTP_OK.getStatusCode()) {
                        succeededURLS = succeededURLS + "\n" + url + "****** Status message is : "
                                + URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode());
                    } else {
                        failedURLS = failedURLS + "\n" + url + " ****** Status message is : "
                                + URLStatus.getStatusMessageForStatusCode(myConnection.getResponseCode());
                    }
                } catch (Exception e) {
                    System.out.print("For url- " + url + "" + e.getMessage());
                }
            } else {
                incorrectURLS += "\n" + url;
            }
        });
    }


}

