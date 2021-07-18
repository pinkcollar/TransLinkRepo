
package com.saucedemo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TranslinkMainPage extends AbstractBasePage<TranslinkMainPage> {
    private static final String URL = "https://new.translink.ca/";

    public TranslinkMainPage(WebDriver driver) {
        super(driver, "translinkPage.properties");
    }

    @Override
    protected void isLoaded() throws Error {
        driver.get(URL);
        super.isLoaded();
    }

    public WebElement getRoutNumberInputField() {
        return getWebElement(getLocator("nextBusTabInput"));
    }

    public WebElement getFavotiteRouteLinkName() {
        return getWebElement(getLocator("favoriteRouteName"));
    }

    public WebElement getAddFavIcon() {
        return getWebElement(getLocator("addFaveIcon"));
    }

    public String getSheduleRouteTitle() {
        try {
            return driver.switchTo().frame(0).getTitle();
        } catch (NoSuchFrameException e){
            throw new RuntimeException("No frame was found", e);
        }
    }

    public WebElement getEditFavoriteNameField() {
        return getWebElement(getLocator("editNameTextArea"));
    }

    public WebElement getAddToFavoritesButton() {
        return getWebElement(getLocator("addToFavouritesButton"));
    }
    public WebElement getMyFavsIcon() {
        return getWebElement(getLocator("myFavsIcon"));
    }
    public WebElement getFindMyNextButton() {
        return getWebElement(getLocator("findMyNextBusButton"));
    }

    public void fillInRoutNumber(String routeNumber){
        getRoutNumberInputField().clear();
        getRoutNumberInputField().sendKeys(routeNumber);
    }

    public void clickNextBusTab(){
        WebElement element = driver.findElement(By.id("next-bus"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
        driver.findElement(By.id("next-bus")).click();
    }

    public void clickFindMyNextBusButton(){
        getFindMyNextButton().click();
    }

    public boolean validateLinkName(String expectedLinkName){
        WebElement currentLinkName = getFavotiteRouteLinkName();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(currentLinkName));
        String actualLinkName = currentLinkName.getText();
        return actualLinkName.equals(expectedLinkName);
    }

    public void clickOnAddFavIcon() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOf(getAddFavIcon()));
        } catch (TimeoutException e){
            throw new RuntimeException("Favorite Icon is not visible");
        }
        getAddFavIcon().click();
    }

    public void clickOnFavoriteRouteLinkName() {
        getFavotiteRouteLinkName().click();
    }

    public boolean validateSheduleRouteTitle(String expectedRouteTitle){
        String currentLinkName = getSheduleRouteTitle();
        return currentLinkName.equals(expectedRouteTitle);
    }


    public void editFavoriteRouteName(String newRouteName) {
        getEditFavoriteNameField().clear();
        getEditFavoriteNameField().sendKeys(newRouteName);
    }

    public void clickOnAddToFavouritesButton(){
        getAddToFavoritesButton().click();
    }

    public void clickOnMyFavsIcon(){
        getMyFavsIcon().click();
    }

}
