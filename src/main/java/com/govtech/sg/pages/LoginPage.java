package com.govtech.sg.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	@FindBy(id="username-in")
	WebElement username;
	
	@FindBy(id="password-in")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submitButton;
	
	@FindBy(xpath="//h1[text()='Working Class Hero System']")
	WebElement headerMessage;
	
	@FindBy(xpath="//div[text()='Unable to log in successfully!']")
	WebElement errorMessage;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getLoginPageHeader() {
		return headerMessage.getText();
	}
	
	public void getUserName(String uname) {
		username.sendKeys(uname);
	}
	
	public void getPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickOnSubmit() {
		submitButton.click();
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	
	public ClerkDashboardPage doClerkLogin(String uname, String pwd) {
		System.out.println("Login with: " + uname + " and " + pwd );
		username.sendKeys(uname);
		password.sendKeys(pwd);
		submitButton.click();
		return new ClerkDashboardPage(driver);
	}
	
	public BookKeeperDashboardPage doBookkeeperLogin(String uname1, String pwd1) {
		System.out.println("Login with: " + uname1 + " and " + pwd1 );
		username.sendKeys(uname1);
		password.sendKeys(pwd1);
		submitButton.click();
		return new BookKeeperDashboardPage(driver);
	}

}
