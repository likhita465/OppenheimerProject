package com.govtech.sg.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookKeeperDashboardPage {
	
	public WebDriver driver;
	//Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@FindBy(xpath="//h1[text()='Book Keeper Dashboard']")
	WebElement bookKprHeaderMsg;
	
	@FindBy(id="tax_relief_btn")
	WebElement generateTaxFileButton;
	
	public BookKeeperDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getBookKeeperHeaderMsg() {
		return bookKprHeaderMsg.getText();
	}
	
	public void clickOnTaxReliefFileButton() {
		generateTaxFileButton.click();
	}
	
	public boolean taxReliefFileButtonVisibility() {
		return generateTaxFileButton.isEnabled();
	}
	
	public void taxReliefFileButtonState() {
		//return generateTaxFileButton;
		try {
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(generateTaxFileButton));
			System.out.println("Tax relief file button is clickable");
		}
		catch(Exception e) {
			System.out.println("Tax relief file button is not clickable");
		}
	}
	

}
