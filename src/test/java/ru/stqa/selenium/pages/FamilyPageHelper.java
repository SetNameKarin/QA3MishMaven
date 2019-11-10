package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FamilyPageHelper extends PageBase{
    @FindBy(id = "titleprofile")
    WebElement titleFamily;

    @FindBy(id = "family")
    WebElement familyIcon;

    @FindBy(xpath = "//span[@id='fieldobjconfession']")
    WebElement confessionField;

    @FindBy(xpath = "//span[@id='fieldobjlanguages']")
    WebElement languagesField;

    @FindBy(css = ".itemprofilefit > #fieldobjfoodPreferences")
    WebElement foodField;

    public FamilyPageHelper(WebDriver driver) {
        super(driver);
    }

    public FamilyPageHelper waitUntilPageLoaded(){
        //waitUntilTextPresentInElement(By.id("titleprofile"), "My Family:",30);
        waitUntilTextPresentInElement(titleFamily, "My Family:",30);
        return this;

    }

    public FamilyPageHelper openFamilyPage(){
        //waitUntilElementIsClickable(By.id("family"),20);
       // driver.findElement(By.id("family")).click();
        waitUntilElementIsClickable(familyIcon,30);
        familyIcon.click();
        waitUntilPageLoaded();
        return this;
    }

    public String getTitle() {
        //return driver.findElement(By.id("titleprofile")).getText();
        return  titleFamily.getText();
    }

    public String getConfession(){
        //return driver.findElement(By.xpath("//span[@id='fieldobjconfession']")).getText();
        return confessionField.getText();
    }

    public String getLanguages(){
        //return driver.findElement(By.xpath("//span[@id='fieldobjlanguages']")).getText();
        return languagesField.getText();
    }

    public String getFood(){
        //return driver.findElement(By.cssSelector(".itemprofilefit > #fieldobjfoodPreferences")).getText();
        return foodField.getText();
    }
}
