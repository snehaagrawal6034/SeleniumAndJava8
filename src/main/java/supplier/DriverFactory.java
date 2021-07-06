package supplier;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DriverFactory {

//   Is it possible to do parallel testing making DriverFactory Singleton
//    private static DriverFactory INSTANCE;
//    public static synchronized DriverFactory getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new DriverFactory();
//        }
//        return INSTANCE;
//    }
    private static final Supplier<WebDriver> Chrome=()-> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };

//    private static final Supplier<WebDriver> Chrome1=new Supplier<WebDriver>() {
//        @Override
//        public WebDriver get() {
//            WebDriverManager.chromedriver().setup();
//            return new ChromeDriver();
//        }
//    };

    private static final Supplier<WebDriver> Firefox=()-> {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    private static final Map<String, Supplier<WebDriver>> MAP = new HashMap<>();
    // when to use final vs non-final java
    // static vs non static.

    static{ // static initializer
        MAP.put("chrome",Chrome);
        MAP.put("firefox",Firefox);
    }

    public static WebDriver setUpDriver(String browserName){
        return MAP.get(browserName).get();
    }
}
