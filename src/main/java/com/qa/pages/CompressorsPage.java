package com.qa.pages;

import com.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompressorsPage extends TestBase {


    // Page Factory -OR
    @FindBy(id = "search")
    WebElement inputSearchText;

    @FindBy(xpath = "//div[@class='category-title-container']//p")
    WebElement msgDescCompressors;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSearch;

    @FindBy(xpath = "//div[contains(@class, 'product-content')]")
    WebElement productContent;

    @FindBy(xpath = "//div[contains(@class, 'product-content')]/h1")
    WebElement productContentDetails;

    @FindBy(xpath = "//p[contains(@class, 'product-pricing')]")
    WebElement productPrice;

    @FindBy(xpath = "//label[contains(text(),'Shipping')]")
    WebElement labelShipping;

    @FindBy(id = "dm-pickup")
    WebElement radioBtnPickUp;

    @FindBy(xpath = "//label[contains(text(),'Pickup in Store')]")
    WebElement labelPickUpStore;

    @FindBy(xpath = "//div[@id='myStore']/p[position()=2]")
    WebElement storeName;

    @FindBy(xpath = "//button[contains(@id,'product-button')]")
    WebElement btnAddToCart;


    // Initializing the Page Objects:
    public CompressorsPage() {
        PageFactory.initElements(driver, this);
    }

    // Actions:
    public String verifyPageTitle() {
        return driver.getTitle();
    }

    public String verifyCompressorsMsgDesc(){
        return msgDescCompressors.getText();
    }

    public CompressorsPage searchCompressorProducts(String model) {
        TestBase.waitUntilElementDisplayed(inputSearchText);
        inputSearchText.sendKeys(model);
        btnSearch.click();
        return this;
    }

    public List<String> getProductDetails(String catalog, String model) {

        TestBase.waitUntilElementDisplayed(productContent);
        String details = productContentDetails.getText();
        String[] productDetails= details.split(",");
        List<String> productDetailsList =  Arrays.asList(productDetails);

        List<String> updatableProductList = new ArrayList<String>();
        updatableProductList.addAll(productDetailsList);
        String actualCatalog = driver.findElement(By.xpath("//p[contains(text(),'"+catalog+"')]")).getText().split(":")[1];
        String actualModel = driver.findElement(By.xpath("//p[contains(text(),'"+model+"')]")).getText().split(":")[1];
        updatableProductList.add(actualCatalog);
        updatableProductList.add(actualModel);

        updatableProductList.add(productPrice.getText());
        updatableProductList.add(labelShipping.getText());
        selectPickUpInStore();
        updatableProductList.add(labelPickUpStore.getText());
        updatableProductList.add(storeName.getText());

        return updatableProductList;
    }

    public String getProductPrice(){
        return productPrice.getText();
    }

    public void selectPickUpInStore(){
        radioBtnPickUp.click();
    }

    public boolean isAddToCartEnabled(){
        return btnAddToCart.isEnabled();
    }
}
