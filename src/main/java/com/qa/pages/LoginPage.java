package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase {

	// Page Factory -OR
	@FindBy(id = "signInName")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "continue")
	WebElement signInBtn;

	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public void enterUsername(String uname) {
		TestBase.waitUntilElementDisplayed(username);
		username.sendKeys(uname);

	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public ProductsPage signIn(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		signInBtn.click();
		return new ProductsPage();
	}

}
