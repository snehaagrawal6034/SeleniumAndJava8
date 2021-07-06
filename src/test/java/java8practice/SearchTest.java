package java8practice;

import actions.SearchUsingFIlter;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import predicate.Filters;

import java.util.function.Predicate;

import static predicate.Filters.*;

public class SearchTest extends BasePage {

    Filters f;

    @BeforeMethod
    public void myFirstTest() {
        String url = "https://www.worldometers.info/coronavirus/";
        driver.get(url);
    }

    @Test (dataProvider = "data-provider")
    public void stateSearchTest(Predicate<WebElement> condition) {
//        Hashtable<String, String> data
        SearchUsingFIlter searchUsingFIlter = new SearchUsingFIlter();
        searchUsingFIlter.clickCountry();
        driver.findElements(By.className("mt_a")).stream().filter(condition).forEach(s-> System.out.println(s.getText()));
    }


   @DataProvider (name = "data-provider")
    public Object[][] getData(){
        return new Object[][] {{STARTWITH},{CONTAINS},{HASLENGTH}};
    }
}
