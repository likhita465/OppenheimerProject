package com.govtech.sg.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public WebDriver driver;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	/**
	 * This method is used to initialize the threadlocal driver on the basis of given browser
	 * @param browser
	 * @return this will return tldriver
	 */
	
	public WebDriver initializeDriver(String browser) {
		
		System.out.println("browser value is:" + browser);
		if(browser.equalsIgnoreCase("chrome")) {
			tlDriver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver());
		}
		else {
			System.out.println("invalid browser value");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
		
	}
	
	/**
	 * this is used to get the driver with ThreadLocal
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

}
