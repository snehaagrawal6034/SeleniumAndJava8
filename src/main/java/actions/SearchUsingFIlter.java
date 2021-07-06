package actions;


import base.BasePage;
import locators.SearchUsingFIlterLocators;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Hashtable;

public class SearchUsingFIlter extends BasePage {

    public SearchUsingFIlterLocators searchFilter;

    public SearchUsingFIlter(){
        this.searchFilter = new SearchUsingFIlterLocators();
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(factory, this.searchFilter);
    }

    public SearchUsingFIlter clickCountry(){

        try {
            safeJavaScriptClick(searchFilter.clickCountry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}

