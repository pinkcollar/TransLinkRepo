package com.saucedemo.test;

import com.saucedemo.pages.TranslinkMainPage;
import com.saucedemo.webdriver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TransLinkTests {

    TranslinkMainPage translinkPage;
    WebDriver driver;
    WebDriverWait wait;
    String routeNumber = "99";
    String editedName = "Translink Auto Homework";
    String expectedRouteTitile = "99 Commercial-Broadway / UBC (B-Line)";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        if (driver == null) {
            driver = DriverFactory.createInstance("chrome");
        }
        translinkPage = new TranslinkMainPage(driver).get();
    }

    @Test
    public void validateRouteAddedToFavoriteTab() {
        translinkPage.clickNextBusTab();
        translinkPage.fillInRoutNumber(routeNumber);
        translinkPage.clickFindMyNextBusButton();
        translinkPage.clickOnAddFavIcon();
        translinkPage.editFavoriteRouteName(editedName);
        translinkPage.clickOnAddToFavouritesButton();
        translinkPage.clickOnMyFavsIcon();
        translinkPage.validateLinkName(editedName);
        translinkPage.clickOnFavoriteRouteLinkName();
        translinkPage.validateSheduleRouteTitle(expectedRouteTitile);
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
