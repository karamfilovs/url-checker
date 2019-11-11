import org.junit.jupiter.api.Test;

public class LinkOnlyChecker {

    @Test
    public void linkCheck(){
        HTTPClient httpClient = new HTTPClient();
        try {
            httpClient.validateUrl(FileReader.retrieveLinksFromFile("src/main/resources/us-links.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}