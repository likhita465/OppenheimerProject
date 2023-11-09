package com.govtech.sg.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClerkDashboardPage {
	
	public WebDriver driver;
	
	@FindBy(xpath="//h1[text()='Clerk Dashboard']")
	WebElement headerMessage;
	
	@FindBy(id="dropdownMenuButton2")
	WebElement addHeroButton;

	@FindBy(xpath="//div[@class='dropdown']//ul//li[1]//a")
	WebElement addOption;
	
	@FindBy(xpath="//div[@class='dropdown']//ul//li[2]//a")
	WebElement uploadCsvFileOption;
	
	@FindBy(id="upload-csv-file")
	WebElement uploadCsvFile;
	
	@FindBy(xpath="//button[text()='Create']")
	WebElement createButton;
	
	@FindBy(xpath="//div[@id='notification-block']//div//h3")
	WebElement successMessage;

	
	public ClerkDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getClerkHeaderMessage() {
		return headerMessage.getText();
	}
	
	public boolean isAddHeroButtonExists() {
		return addHeroButton.isDisplayed();
	}
	
	public void clickAddHeroButton() {
		addHeroButton.click();
	}
	
	public void clickAddOptionFromDropdown() {
		addOption.click();
	}
	
	public void clickUploadCsvFileFromDropdown() {
		uploadCsvFileOption.click();
	}
	
	public void uploadWorkingCsvFile() {
		uploadCsvFile.sendKeys(System.getProperty("user.dir") + "//src//test//resources//dataProviders//wc_hero_data.csv");;
	}
	
	public void uploadErroneousCsvFile() {
		uploadCsvFile.sendKeys(System.getProperty("user.dir") + "//src//test//resources//dataProviders//bad_wc_hero_data.csv");;
	}
	
	public void uploadEmptyCsvFile() {
		uploadCsvFile.sendKeys(System.getProperty("user.dir") + "//src//test//resources//dataProviders//empty_hero_data.csv");;
	}
	
	public void clickCreateButton() {
		createButton.click();
	}
	
	public String getSuccessNotificationMsg() {
		return successMessage.getText();
	}
	

}
