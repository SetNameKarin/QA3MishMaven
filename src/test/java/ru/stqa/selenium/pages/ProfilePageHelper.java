package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePageHelper extends PageBase {
    @FindBy(id = "profile")
    WebElement profileButton;

    @FindBy(id = "idbtneditprofile")
    WebElement editButton;

    @FindBy(id = "titleprofile")
    WebElement titleProfile;

    @FindBy(id = "imgavatarinprofilefamily")
    WebElement avatarFamily;

    @FindBy(id = "idbtnsaveprofile")
    WebElement saveButton;

    @FindBy(xpath = "//span[@id='fieldobjfamilyName']/input")
    WebElement inputFamilyName;


    @FindBy(xpath = "//span[@id='fieldobjconfession']")
    WebElement confessionField;

    @FindBy(css = "#fieldobjlanguages")
    WebElement languageField;

    @FindBy(xpath = "//*[@id='fieldobjfoodPreferences']")
    WebElement foodField;



    public ProfilePageHelper(WebDriver driver) {
        super(driver);
    }

    public ProfilePageHelper openProfilePage() {
        profileButton.click();
        waitUntilPageLoaded();
        return this;
    }

    public ProfilePageHelper waitUntilPageLoaded(){

        waitUntilElementIsClickable(editButton,20);
        waitUntilTextPresentInElement(titleProfile,"My Profile:",20);
        waitUntilElementIsVisible(avatarFamily,20);
        return this;
    }

    public ProfilePageHelper openEditMode() {
        editButton.click();
        waitUntilElementIsClickable(inputFamilyName,20);
        waitUntilElementIsClickable(saveButton,20);
        return this;
    }

    public ProfilePageHelper enterFamilyName(String name) {
        enterValueToField(inputFamilyName,name);
        return this;
    }

    public ProfilePageHelper saveChanges() {
        scrollPageUp();
        waitUntilElementIsClickable(saveButton,20);
        saveButton.click();
        waitUntilElementIsClickable(editButton,20);
        return this;
    }



    public String getFamilyName() {
        return driver.findElement(By.xpath("//span[@id='fieldobjfamilyName']/a")).getText();
    }

    public String getConfession() {
        //return driver.findElement(By.xpath("//span[@id='fieldobjconfession']")).getText();
        return confessionField.getText();
    }

    public String getLanguage() {
        //return driver.findElement(By.cssSelector("#fieldobjlanguages")).getText();
        return languageField.getText();
    }

    public String getFood() {
        //return driver.findElement(By.xpath("//*[@id='fieldobjfoodPreferences']")).getText();
        return foodField.getText();
    }

    public boolean familyNameIsDisplayed(String name) {
        waitUntilElementIsClickable(By.linkText(name),20);
        return driver.findElement(By.linkText(name)).isDisplayed();
    }
}
