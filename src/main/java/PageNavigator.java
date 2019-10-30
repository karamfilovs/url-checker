import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class PageNavigator {
    private static WebDriver driver;
    private static final String WINDOWS_CHROME_DRIVER_PATH = "src\\main\\resources\\chromedriver.exe";


    public static void gotoPage(String page) {
        System.out.println("Navigating to page:" + page);
        driver.navigate().to(page);
    }

    public static void startBrowser() {
        System.setProperty("webdriver.chrome.driver", WINDOWS_CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

   public static List<String> getAllPageLinks(){
       System.out.println("Scrapping all hyperlinks from the current page");
       List<WebElement> links = driver.findElements(By.tagName("a"));
       links.addAll(driver.findElements(By.tagName("img")));
       Set<WebElement> pageLinks = links.stream().filter(link -> link.getAttribute("href") != null).collect(Collectors.toSet());
       List<String> hyperlinks = new ArrayList<>();
       pageLinks.forEach(pageLink -> hyperlinks.add(pageLink.getAttribute("href")));
       System.out.println("Links found:" + hyperlinks.size());
       return hyperlinks;
   }

   public static void browserQuit(){
        driver.quit();
   }


}
