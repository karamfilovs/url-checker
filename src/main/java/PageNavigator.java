import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
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
    private static final String WINDOWS_CHROME_DRIVER_PATH = "src/main/resources/chromedriver.exe";


    public static void gotoPage(String page) {
        System.out.println("Navigating to page:" + page);
        driver.navigate().to(page);
    }

    private static boolean isPresent(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.findElement(by);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return false;
        }
    }

    public static void acceptCookies() {
        if(isPresent(By.xpath("//iframe[contains(@id,'pop-frame')]"))) {
            System.out.println("Entering to accept the cookies.");
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'pop-frame')]")));
            driver.findElement(By.cssSelector("div.pdynamicbutton a.submit")).click();
            Wait<WebDriver> wait = new WebDriverWait(driver, 60);
            System.out.println("Waiting for Close button to appear in order to click it.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pdynamicbutton a.close"))).click();
            System.out.println("Clicking the close button after the cookies are accepted.");
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//iframe[contains(@id,'pop-frame')]")));
            System.out.println("Cookies accepted!");
        }
    }

    public static void takeScreenshot(String url) {
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotTakingDriver = (TakesScreenshot) driver;
            try {
                File localScreenshots = new File(new File("target"), "screenshots");
                if (!localScreenshots.exists() || !localScreenshots.isDirectory()) {
                    localScreenshots.mkdirs();
                }
                File screenshot = new File(localScreenshots, url.replace("https://", "tls") + "_" + new Date().getTime() +".png");
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
        System.setProperty("webdriver.chrome.driver", WINDOWS_CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public static void browserQuit() {
        driver.quit();
    }


}
