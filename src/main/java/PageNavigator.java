import com.google.common.collect.Sets;
import com.sun.deploy.util.SystemUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.plugin2.util.SystemUtil;


import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class PageNavigator {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageNavigator.class);
    private static WebDriver driver;
    private static final String LINUX_CHROME_DRIVER_PATH = "src/main/resources/linux-chromedriver-78";
    private static final String WINDWOS_CHROME_DRIVER_PATH = "src/main/resources/chromedriver.exe";


    public static void gotoPage(String page) {
        System.out.println("Navigating to page:" + page);
        driver.navigate().to(page);
    }

    public static void acceptCookies() {
        driver.switchTo().frame(1);
        driver.findElement(By.xpath("//a[@class='submit']")).click();
        driver.switchTo().parentFrame();
    }

    public static void takeScreenshot(String url) {
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotTakingDriver = (TakesScreenshot) driver;
            try {
                File localScreenshots = new File(new File("target"), "screenshots");
                if (!localScreenshots.exists() || !localScreenshots.isDirectory()) {
                    localScreenshots.mkdirs();
                }
                File screenshot = new File(localScreenshots, url.replace(".", "-") + "_" + new Date().getTime() +".png");
                FileUtils.copyFile(screenshotTakingDriver.getScreenshotAs(OutputType.FILE), screenshot);
                LOGGER.info("Screenshot for class={} method={} saved in: {}", screenshot.getAbsolutePath());
            } catch (Exception e1) {
                LOGGER.info("Unable to take screenshot", e1);
            }
        } else {
            LOGGER.error("Driver '{}' can't take screenshots so skipping it.", driver.getClass());
        }

}


    public static void startBrowser() {
        System.setProperty("webdriver.chrome.driver", WINDWOS_CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static List<String> getAllPageLinks() {
        System.out.println("Scrapping all hyperlinks from the current page");
        Set<WebElement> links = Sets.newHashSet(driver.findElements(By.tagName("a")));
        links.addAll(driver.findElements(By.tagName("img")));
        Set<WebElement> pageLinks = links.stream().filter(link -> link.getAttribute("href") != null).collect(Collectors.toSet());
        List<String> hyperlinks = new ArrayList<>();
        pageLinks.forEach(pageLink -> hyperlinks.add(pageLink.getAttribute("href")));
        System.out.println("Links found:" + hyperlinks.size());
        return hyperlinks;
    }

    public static void browserQuit() {
        driver.quit();
    }


}
