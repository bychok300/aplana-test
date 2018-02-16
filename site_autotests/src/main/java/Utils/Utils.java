package Utils;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static boolean isLoging = false;
    public static int count = 2;
    public static void waitLoadElement(int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public static int getCount(){
        return count;
    }
    public static void shot(WebDriver driver, String filename) {
        waitLoadElement(500);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(filename + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * выбираем какую-то группу анализов рандомно и так же рандомно выбираем анализ
     * TODO: может это надо дописать не тут, но было бы круто проверять, что с добавлением анализов меняется цена
     * @param driver
     */
    public static void getRandomChoise(WebDriver driver){
        List<WebElement> left = driver.findElements(By.className("popupFormAnalyses__leftItem"));
        Random r = new Random();
        int randNumForLeft = r.nextInt(left.size());
        left.get(randNumForLeft).click();

        waitLoadElement(500);
        List<WebElement> right = driver.findElements(By.className("rightItem__checkbox"));
        int randNumForRight = r.nextInt(right.size());
        right.get(randNumForRight).click();
    }
    public static void assertEq(String fetureArg, WebDriver driver){
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

    public static void isPageContain(String url, String text, WebDriver driver){
        driver.get(url);
        waitLoadElement(2000);
        String [] s = text.split(" ");
        List<String> ls = new LinkedList<>();
        for(String el: s){
            ls.add(el);
        }

        try {
            for (String el: ls){
                Assert.assertEquals("Failed", true, (driver.getPageSource().contains(el)));
            }
            System.out.println("\nEverything is OK!");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("\nYour text didn't finded");
        }
        finally {
            driver.navigate().refresh();
        }
    }


}
