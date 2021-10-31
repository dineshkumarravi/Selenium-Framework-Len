package com.qa.tests;

import java.util.Hashtable;
import java.util.List;

import com.qa.pages.CompressorsPage;
import com.qa.pages.ProductsPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.pages.HomePage;

public class ValidateCompressorDetailsTest extends TestBase {

    HomePage homePage;
    ProductsPage productsPage;
    CompressorsPage compressorsPage;

    public ValidateCompressorDetailsTest() {
        super();
    }

    // test cases should be separated -- independent with each other
    // before each test case -- launch the browser and login
    // @test -- execute test case
    // after each test case -- close the browser

    @Test(dataProvider = "getData")
    public void validateCompressorDetails(Hashtable<String, String> data) throws InterruptedException {
        homePage = new HomePage();
        extentTest = extent.startTest("ValidateCompressorDetailsTest");
        productsPage= homePage.clickSignIn().signIn(data.get("EMAILID"), data.get("Password"));
        compressorsPage = productsPage.navigateToMenu(data.get("LinkName"))
				                      .navigateToPage(data.get("PageNavigation"))
                                      .navigateToSubPage(data.get("SubPageNavigation"));
        extentTest.log(LogStatus.INFO, "Logged In to app and Navigate to Compressor page");
        Assert.assertEquals(compressorsPage.verifyPageTitle(), (data.get("PageTitle")));
        extentTest.log(LogStatus.PASS, "Verified Compressor Page Title");
        Assert.assertEquals(compressorsPage.verifyCompressorsMsgDesc(), "Replace your compressor at LennoxPros.com.");
        extentTest.log(LogStatus.PASS, "Verified Compressor Page Description");
        List<String> productDetailsList = compressorsPage.searchCompressorProducts(data.get("Catalog"))
                .getProductDetails(data.get("Catalog"),data.get("Model"));
        Assert.assertEquals(compressorsPage.getProductPrice(),data.get("Price"));
        extentTest.log(LogStatus.INFO, "Verified Price Details");
        Assert.assertTrue(compressorsPage.isAddToCartEnabled());
        extentTest.log(LogStatus.PASS,data.get("Model")+ " Product Details are"+ productDetailsList.toString());

    }
}
