package Utils;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;

public class CustomAssert {
    public static void assertEquals(String fetureArg, WebDriver driver){
        String [] s = fetureArg.split(" ");
        List<String> ls = new LinkedList<>();
        for(String el: s){
            ls.add(el);
        }
        try {
            for (String el: ls){
                Assert.assertEquals("Failed", true, (driver.getPageSource().contains(el)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            driver.navigate().refresh();
        }
    }
}
