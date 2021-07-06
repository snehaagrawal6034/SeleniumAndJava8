package predicate;

import org.openqa.selenium.WebElement;

import java.util.function.Predicate;

public class Filters {

//    public static final Predicate<WebElement> STARTWITH=s->s.getText().startsWith("M");
    public static final Predicate<WebElement> STARTWITH=new Predicate<WebElement>() {
    @Override
    public boolean test(WebElement s) {
//        System.out.println("Elements starting with M");
        return s.getText().startsWith("M");
    }
};
    public static final Predicate<WebElement> CONTAINS=c->{
//        System.out.println("Elements which doesnt contains");
        return (!c.getText().toLowerCase().contains("t"));
    };
    public static final Predicate<WebElement> HASLENGTH=l->{
//        System.out.println("Elements having 5 chars");
        return l.getText().length()==5;
    };
}
