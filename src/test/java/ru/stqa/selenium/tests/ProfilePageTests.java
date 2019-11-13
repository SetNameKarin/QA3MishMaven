package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;


public class ProfilePageTests extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;
    ProfilePageHelper profilePage;
    FamilyPageHelper familyPage;

    @BeforeMethod
    public void initTests()  {
       /* homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        homePageAuth = new HomePageAuthHelper(driver);
        profilePage = new ProfilePageHelper(driver);
        familyPage = new FamilyPageHelper(driver);*/

        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        profilePage = PageFactory.initElements(driver, ProfilePageHelper.class);
        familyPage = PageFactory.initElements(driver, FamilyPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage.openLoginPage();
        loginPage.loginToTheSystem(LOGIN,PASSWORD);
        homePageAuth.waitUntilPageIsLoaded();
        profilePage.openProfilePage();

    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "lastFamilyCh")
    public void lastNameOfFamilyChanging(String name1, String name2)  {
        //String name1 = "Petrov";
        //String name2 = "Ivanov";
        profilePage.openEditMode()
                   .enterFamilyName(name1)
                   .saveChanges();
        familyPage.openFamilyPage();

        Assert.assertEquals("My Family: " + name1,familyPage.getTitle());

        profilePage.openProfilePage()
                   .openEditMode()
                   .enterFamilyName(name2)
                   .saveChanges();

        Assert.assertTrue(profilePage.familyNameIsDisplayed(name2));

    }
    @Test
    public void profileAndFamilyPageComparing() {

        String profileConfession = profilePage.getConfession();
        String profileLanguage = profilePage.getLanguage();
        String profileFoodPreference = profilePage.getFood();

        familyPage.openFamilyPage();

        int counter = 0;
        if (familyPage.getConfession().equals(profileConfession)) counter++;
        if (familyPage.getLanguages().equals(profileLanguage)) counter++;
        if (familyPage.getFood().equals(profileFoodPreference)) counter++;

        Assert.assertEquals(counter,3);
    }

}
