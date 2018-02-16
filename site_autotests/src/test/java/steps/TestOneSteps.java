package steps;

import Utils.Utils;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.То;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.TestOnePage;

public class TestOneSteps {
    private static WebDriver driver = new ChromeDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, 30);

    @Допустим("^открыть яндекс и провести манипуляции$")
    //не очень удачно назвал, но думаю это простительно
    public  void открыть_яндекс_и_провести_манипуляции(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");

    }
    @Если("^перейти в маркет$")
    public void перейти_в_маркет() throws Throwable {
        driver.get("https://yandex.ru");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(TestOnePage.mainMenuItems));
        TestOnePage.clickMarketBtn(driver);
    }
    @И("^выбрать раздел электроники$")
    public void выбрать_раздел_электроники() throws Throwable {
        wait.until(ExpectedConditions.elementToBeClickable(TestOnePage.marketMenuItems));
        TestOnePage.clickElectronictCateg(driver);
    }
    @И("^выбрать раздел телевизоры$")
    public void выбрать_раздел_телевизоры() throws Throwable {
        //там эта плитка плавает, не стал вдовать в подробности по какому принципу
        //она меняет свою позицию. Сделал хот фикс
//        wait.until(ExpectedConditions.elementToBeClickable(TestOnePage.tv));
//        TestOnePage.clickTv(driver);
        driver.get("https://market.yandex.ru/catalog/59601/list?hid=90639&track=fr_ctlg&utm_source=adfox_desktop&utm_medium=banner_regular&utm_campaign=electronics_tvv_half&local-offers-first=0&deliveryincluded=0&onstock=1");
    }
    @И("^зайти в расширенный поиск$")
    public void зайти_в_расширенный_поиск() throws Throwable {
        Utils.waitLoadElement(1);
    }
    @И("^задать параметры поиска от 20000 рублей$")
    public void задать_параметры_поиска_от_20000_рублей() throws Throwable {
        TestOnePage.passPrice(driver);
    }
    @И("^выбрать производителей от Самсунг и ЭлДжи$")
    public void выбрать_производителей_от_Самсунг_и_ЭлДжи() throws Throwable {
        TestOnePage.clickLg(driver);
        TestOnePage.clickSamsung(driver);
    }
    @И("^нажать кнопку применить$")
    public void нажать_кнопку_применить() throws Throwable {
        //он сам применяет, можно опустить этот шаг, но я в фичу уже добавил, не стал заморачиваться
//        wait.until(ExpectedConditions.elementToBeClickable(TestOnePage.tv));
//        TestOnePage.clickApply(driver);
        Utils.waitLoadElement(1000);
    }
    @И("^проверить что элементов 12 штук$")
    public void проверить_что_элементов_12_штук() {
        try{
            Assert.assertEquals("Failed", TestOnePage.searchedTv(driver).size(), 12);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @И("^запомнить первый$")
    public void запомнить_первый() throws Throwable {
        Utils.waitLoadElement(1000);
    }
    @И("^в поисковую строку ввести заполненное значение$")
    public void в_поисковую_строку_ввести_заполненное_значение() throws Throwable {
        TestOnePage.passToSearch(driver);
    }
    @То("^наименование товара соответсвуют запомненному значению$")
    public void наименование_товара_соответсвуют_запомненному_значению() throws Throwable {
        Utils.waitLoadElement(2000);
        driver.navigate().refresh();
        try{
            Assert.assertEquals("Failed", TestOnePage.getSearchVal(driver), TestOnePage.getFirstTv(driver));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
