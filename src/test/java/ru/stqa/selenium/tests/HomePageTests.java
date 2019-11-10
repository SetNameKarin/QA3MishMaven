package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.util.DataProviders;


public class HomePageTests extends TestBase {
    HomePageHelper homePage;

    @BeforeMethod
    public void initTests(){
        //homePage = new HomePageHelper(driver);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void homePageVerificationTest()  {

        Assert.assertTrue(homePage.correctPageIsLoaded(),
                "Name of the listEvent element is not 'List events'");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "singleFilterByHoliday")
    public void singleFilterHolidaysBy(String holiday)  {
        //String holiday = "Purim";
       // Shabbat
        homePage.filterEventsByHoliday(holiday);
        Assert.assertTrue(homePage.allEventsBelongToHoliday(holiday));
    }



}
