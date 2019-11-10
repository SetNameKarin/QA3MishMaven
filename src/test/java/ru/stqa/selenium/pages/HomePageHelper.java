package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageHelper extends PageBase {
    @FindBy(id = "idsignin")
    WebElement loginIcon;

    @FindBy(xpath = "//div[@class = 'itemEventInsert']")
    List<WebElement> eventsList;

    @FindBy(id = "idtitletypesearchevents")
    WebElement listEvent;

    @FindBy(name = "selectholidays")
    WebElement filterHolidays;

    @FindBy(xpath = "//div[@id='idbtnclearfilter']")
    WebElement clearFilterButton;

    @FindBy(xpath = "//div[@class = 'holidayItemEvents']")
    List<WebElement> listHolidays;



    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public HomePageHelper waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(loginIcon, 20);
        waitUntilAllElementsVisible(eventsList,20);
        return this;
    }

    public Boolean correctPageIsLoaded(){

        return listEvent.getText().equals("List events");
    }


    public HomePageHelper filterEventsByHoliday(String holiday) {
        // ----- to wait that select-element (filter by holiday) and all options are loaded ---
        waitUntilElementIsVisible(filterHolidays,30);
        waitUntilAllElementsPresent(By.xpath("//select[@name = 'selectholidays']/option"),30);
        // ------ choose filter "shabbat" ------
        selectValueFromList(filterHolidays,holiday);

        // ------ wait that filter "shabbat" is chosen -----
        waitUntilElementIsClickable(clearFilterButton,20);
        waitUntilElementIsPresent(By
                .xpath("//option[@selected][@value = '" + holiday +"']"),20);
        waitUntilAllElementsVisible(eventsList,40);

        return this;
    }



    public Boolean allEventsBelongToHoliday(String holiday) {

        // --- verify that all holidays values are "Shabbat" ----
        int counter = 0;
        for (int i=0; i < listHolidays.size(); i++){
            if (listHolidays.get(i).getText().equals(holiday)) counter++;
        }
        return counter == listHolidays.size();
    }
}
