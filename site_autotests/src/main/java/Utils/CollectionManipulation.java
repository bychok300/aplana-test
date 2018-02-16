package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CollectionManipulation {

    public String formatData(List<WebElement> list, int elementIndex){
        return list.get(elementIndex).getText().toLowerCase().replaceAll("\n", " ");
    }
}
