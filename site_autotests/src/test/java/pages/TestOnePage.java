package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestOnePage {

    public static By mainMenuItems = By.className("home-tabs__link");
    public static By marketMenuItems = By.className("topmenu__item");
    public static By tv = By.xpath("/html/body/div/div[4]/div[2]/div[1]/div/div[1]/div[1]/a");
    public static By searchPriceInp = By.xpath("//*[@id=\"glf-pricefrom-var\"]");
    public static By samsungVend = By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div[8]/a");
    public static By LgVend = By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div[5]/a/span");
    public static By applyBtn = By.className("n-filter-panel-aside__apply");
    public static By tvItems = By.className("n-snippet-card2");
    public static By searchInp = By.id("header-search");
    public static By getFirst = By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div[1]/div[1]");

    public static List<WebElement> allMenu(WebDriver driver) {
        List<WebElement> list = driver.findElements(mainMenuItems);
        return list;
    }
    public static List<WebElement> allMenuMarket(WebDriver driver) {
        List<WebElement> list = driver.findElements(marketMenuItems);
        return list;
    }
    public static List<WebElement> searchedTv(WebDriver driver){
        List<WebElement> list = driver.findElements(tvItems);
        return list;
    }

    public static void clickMarketBtn(WebDriver driver){
        allMenu(driver).get(4).click();
    }

    public static void clickElectronictCateg(WebDriver driver){
        allMenuMarket(driver).get(0).click();
    }

    public static void clickTv(WebDriver driver){
        driver.findElement(tv).click();
    }

    public static void passPrice(WebDriver driver){
        driver.findElement(searchPriceInp).sendKeys("20000");
    }
    public static void clickSamsung(WebDriver driver){
        driver.findElement(samsungVend).click();
    }
    public static void clickLg(WebDriver driver){
        driver.findElement(LgVend).click();
    }
    public static void clickApply(WebDriver driver){
        driver.findElement(applyBtn).click();
    }

    public static void passToSearch(WebDriver driver){
        String [] s = searchedTv(driver).get(0).getText().split(" ");
        driver.findElement(searchInp).sendKeys(s[0] + " " + s[1]);
    }
    public static String getSearchVal(WebDriver driver) {
        return driver.findElement(searchInp).getText();
    }
    public static String getFirstTv(WebDriver driver){
        return driver.findElement(getFirst).getText();

    }
}
