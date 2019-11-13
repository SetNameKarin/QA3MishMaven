package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.util.LogLog4j;

import java.util.List;

public class PageBase {
    WebDriver driver;
    public static final String LOGIN = "marinaA";
    public static final String PASSWORD = "marina1!";
    public static LogLog4j log = new LogLog4j();

    PageBase(WebDriver driver){
        this.driver = driver;
    }

    public void waitUntilElementIsVisible(By locator, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsPresent(By locator, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilAllElementsVisible(List<WebElement> listOptions, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOfAllElements(listOptions));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilAllElementsPresent(By locator, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public void waitUntilElementIsClickable(By locator, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsClickable(WebElement element, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilTextPresentInElement(By locator, String text, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.textToBePresentInElementLocated(locator,text));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilTextPresentInElement(WebElement element, String text, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void enterValueToField(WebElement field, String text) {
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    public void selectValueFromList(WebElement element, String value) {
        Select selector;
        try{
            selector = new Select(element);
            selector.selectByValue(value);
        }catch(Exception e){
            try {
                Thread.sleep(20000);
                System.out.println("Exception: " + e);
                selector = new Select(element);
                selector.selectByValue(value);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }
}
