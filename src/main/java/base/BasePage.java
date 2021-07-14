package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import supplier.DriverFactory;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BasePage {

    protected static WebDriver driver;
    public static Logger log = Logger.getLogger("devpinoyLogger");

    @Parameters({"browserName" ,"platform"})
    @BeforeMethod
    public void getDriver(@Optional String browser, @Optional String platformName, Method method) {

        String methodName = method.getName();

//        sauceLab configuration
//        MutableCapabilities sauceOpts = new MutableCapabilities();
//        sauceOpts.setCapability("username", "Sneha_Kumari");
//        sauceOpts.setCapability("accessKey", "feb1dd07-2f58-4f63-b9e6-24f3593a7f63");
//        sauceOpts.setCapability("name",methodName);
//
//        DesiredCapabilities dc = new DesiredCapabilities();
//        dc.setCapability("sauce:options", sauceOpts);
//        dc.setCapability("platformName", "windows 10");
//        dc.setCapability("browserName", browser);

//        try {
//            driver = new RemoteWebDriver(new URL("https://ondemand.apac-southeast-1.saucelabs.com:443/wd/hub"), dc);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        driver = DriverFactory.setUpDriver(browser);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

    public static void click(WebElement element) {

        element.click();
        log.debug("Clicking on an Element : " + element);
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