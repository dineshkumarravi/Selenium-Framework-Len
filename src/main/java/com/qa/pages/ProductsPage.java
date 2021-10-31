package com.qa.pages;

import com.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends TestBase {

    @FindBy(xpath = "//ul[@class='menu filters']")
    WebElement menuFilters;

    // Initializing the Page Objects:
    public ProductsPage()  {
        PageFactory.initElements(driver, this);
    }

    // Actions:

    public ProductsPage navigateToMenu(String menuNavigation){
        TestBase.waitUntilElementPresent(By.linkText(menuNavigation));
        driver.findElement(By.linkText(menuNavigation)).click();
        return this;
    }

    public ProductsPage navigateToPage(String pageNavigation){
        TestBase.waitUntilElementPresent(By.linkText(pageNavigation));
        driver.findElement(By.linkText(pageNavigation)).click();
        return this;
    }

    public CompressorsPage navigateToSubPage(String subPageNavigation) throws InterruptedException {
        TestBase.waitUntilElementPresent(By.linkText(subPageNavigation));
        driver.findElement(By.linkText(subPageNavigation)).click();
        TestBase.waitUntilElementDisplayed(menuFilters);
        driver.findElement(By.linkText(subPageNavigation)).click();
        return new CompressorsPage();
    }
}

