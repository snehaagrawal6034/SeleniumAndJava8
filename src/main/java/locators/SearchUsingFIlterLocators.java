package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchUsingFIlterLocators {

    @FindBy(xpath = "//a[@href='country/us/'][1]")
    public WebElement clickCountry;
}
