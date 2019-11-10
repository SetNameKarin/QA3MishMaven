package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IntroPageHelper extends PageBase {
    @FindBy(id = "closedIntro")
    WebElement closedIntro;

    public IntroPageHelper(WebDriver driver) {
        super(driver);
    }
    public  IntroPageHelper waitUntilPageLoaded(){
        waitUntilElementIsClickable(closedIntro,30);
        return this;
    }

    public IntroPageHelper closeIntroPage(){
        closedIntro.click();
        return this;
    }
}
