package com.saucedemo.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static final Logger LOG = LoggerFactory.getLogger(DriverFactory.class);

	/**
	 * Create WebDriver Instance
	 */
	public static WebDriver createInstance(String browser) {
		WebDriver driver = null;

		if(browser.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		LOG.info("creating webdriver {}", driver);
		return driver;
	}
}