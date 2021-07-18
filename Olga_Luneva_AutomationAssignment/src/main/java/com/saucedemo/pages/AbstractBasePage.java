package com.saucedemo.pages;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Predicate;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public abstract class AbstractBasePage<T extends AbstractBasePage<T>> extends LoadableComponent<T> {

    private static final String BASE_LOCATORS_DIR = "src/test/resources/locators";

    protected final WebDriver driver;
    private final File locatorFile;
    private final Properties properties = new Properties();

    public AbstractBasePage(WebDriver driver, String locatorsFile) {
        this.driver = driver;
        this.locatorFile = new File(BASE_LOCATORS_DIR, locatorsFile);
        try {
            Reader reader = new FileReader(this.locatorFile);
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* check whether the page load is complete */
    @Override
    protected void isLoaded() throws Error {
        String state = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
        assertTrue("complete".equalsIgnoreCase(state), "page load status is not complete");
    }

    /* if page not loaded in time or any problem occurred, refresh the page */
    @Override
    protected void load() {
        driver.navigate().refresh();
    }

    protected final By getLocator(String key) {
        Optional<String> locatorKey = properties.keySet().stream().filter(e -> e.toString().startsWith(key))
                .map(Object::toString).findFirst();
        if (!locatorKey.isPresent()) {
            throw new RuntimeException(
                    "unable to find locator with key " + locatorKey + " in " + locatorFile.getAbsolutePath());
        }
        String locatorType = locatorKey.get().substring(locatorKey.get().lastIndexOf('.') + 1);
        String value = properties.getProperty(locatorKey.get());
        switch (locatorType) {
            case "id":
                return By.id(value);
            case "className":
                return By.className(value);
            case "name":
                return By.name(value);
            case "xpath":
                return By.xpath(value);
            case "css":
                return By.cssSelector(value);
            case "tag":
                return By.tagName(value);
            case "link":
                return By.linkText(value);
            case "partialLink":
                return By.partialLinkText(value);
            default:
                return null;
        }
    }

    public WebElement getWebElement(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            throw new RuntimeException(locator + " element was not found", e);
        }
    }

}
