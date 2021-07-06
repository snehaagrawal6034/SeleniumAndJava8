package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import supplier.DriverFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class BasePage {

    protected static WebDriver driver;
    public static Logger log = Logger.getLogger("devpinoyLogger");

    @Parameters({"browserName"})
    @BeforeTest
    public void getDriver(@Optional("chrome") String browserName) {

        MutableCapabilities sauceOpts = new MutableCapabilities();
//        sauceOpts.setCapability("seleniumVersion", "3.141.59");
        sauceOpts.setCapability("username", "Sneha_Kumari");
        sauceOpts.setCapability("accessKey", "feb1dd07-2f58-4f63-b9e6-24f3593a7f63");
//        sauceOpts.setCapability("build","Java-W3C-Examples");
//        sauceOpts.setCapability("tags","w3c-chrome-tests");

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("sauce:options", sauceOpts);
//        dc.setCapability("browserVersion", "91");
        dc.setCapability("platformName", "windows 10");

//        driver = DriverFactory.setUpDriver(browserName);
        dc.setCapability("browserName", browserName);
//        driver.manage().window().maximize();

        try {
            driver = new RemoteWebDriver(new URL("https://ondemand.apac-southeast-1.saucelabs.com:443/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    public static void click(WebElement element) {

        element.click();
        log.debug("Clicking on an Element : " + element);
//        test.log(LogStatus.INFO, "Clicking on : " + element);
    }

    public void safeJavaScriptClick(WebElement element) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click");

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on element " + e.getStackTrace());
        }
    }
}
