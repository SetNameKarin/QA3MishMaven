package ru.stqa.selenium.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.util.DataProviders;


public class HomePageTests extends TestBase {
    HomePageHelper homePage;

    @BeforeMethod(alwaysRun = true)
    public void initTests(){
        log.info("-- HomePageTests - @BeforeMethod - initTests() was started");
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        log.info("--- HomePage was loaded--");
        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void homePageVerificationTest()  {
        log.startTestCase("homePageVerificationTest()");
        log.info("-- Assert - Verification that name of the listEvent element is 'List events'");

        Assert.assertTrue(homePage.correctPageIsLoaded(),
                "Name of the listEvent element is not 'List events'");

    }


    @Test(groups = {"sanity"}, dataProviderClass = DataProviders.class, dataProvider = "singleFilterByHoliday")
    public void singleFilterHolidaysBy(String holiday)  {
        //String holiday = "Purim";
       // Shabbat
        log.startTestCase("singleFilterHolidaysBy, TestData: holiday - " + holiday );

        homePage.filterEventsByHoliday(holiday);
        Assert.assertTrue(homePage.allEventsBelongToHoliday(holiday));
    }


}
