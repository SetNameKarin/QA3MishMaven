package ru.stqa.selenium.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;


import com.google.common.io.Files;
import org.openqa.selenium.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;

import ru.stqa.selenium.SuiteConfiguration;
import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.IntroPageHelper;
import ru.stqa.selenium.util.LogLog4j;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;
  public static final String LOGIN = "marinaA";
  public static final String PASSWORD = "marina1!";
  IntroPageHelper introPage;
  public static LogLog4j log = new LogLog4j();

  protected EventFiringWebDriver driver;
  //protected WebDriver driver;
  public static class MyListener extends AbstractWebDriverEventListener{
      @Override
      public void beforeFindBy(By by, WebElement element, WebDriver driver) {
          super.beforeFindBy(by, element, driver);
          log.info("*** Try to find by: " + by);
      }

      @Override
      public void afterFindBy(By by, WebElement element, WebDriver driver) {
          super.afterFindBy(by, element, driver);
          log.info("**** Found by: " + by);
      }

      @Override
      public void onException(Throwable throwable, WebDriver driver) {
          super.onException(throwable, driver);
          log.error("*** Exception: " + throwable);
         File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         File screen = new File("screen - " + System
                 .currentTimeMillis() + ".png");
          try {
              Files.copy(tmp, screen);
          } catch (IOException e) {
              e.printStackTrace();
          }
          log.info("See screen in file " + screen);
      }


  }

  @BeforeSuite
  public void initTestSuite() throws IOException {
    log.info("-- @BeforeSuite-- initTestSuite() was started");
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    log.info("-- @BeforeMethod-- initWebDriver() was started");
    log.info("- driver was defined");
    //driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    driver = new EventFiringWebDriver(WebDriverPool
            .DEFAULT.getDriver(gridHubUrl, capabilities));
    driver.register(new MyListener());
    log.info("- System was opened by url - " + baseUrl);
    driver.get(baseUrl);
    log.info("- Open browser, fullscreen");
    driver.manage().window().fullscreen();
    log.info("- IntoPage was initialized");
    introPage = PageFactory
            .initElements(driver, IntroPageHelper.class);
    log.info("- IntroPage: --waitUntilPageLoaded() was started");
    introPage.waitUntilPageLoaded();
    log.info("- IntroPage: -- closeIntroPage() (close by X button) was started");

    introPage.closeIntroPage();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    log.info("-- @AfterMethod-- tearDown() was started");
    WebDriverPool.DEFAULT.dismissAll();
  }
}
