package TestCases;

import Base.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CompleteAddToCartAndCheckoutTest extends TestBase {

//    String firstname="ahmed";
//    String lastname="fathi";
//    String postalcode="12345";


    @Test(dataProvider = "retrieveData")
    public  void TC_CompletAddToCartAndCheckOut(HashMap<String,String> input ) throws InterruptedException {
        LoginPageObject.fillLoginData(input.get("username"),input.get("password"));
        inventoryPageObject.productWordAssertAfterReachedInventoryPage();
        inventoryPageObject.selectThreeItems();
        inventoryPageObject.ClickAddToCartLinkTextBtn();

        CartPageObject.AssertionForFirstItems();
        CartPageObject.AssertionForSecondItems();
        CartPageObject.ClickCheckOutBtn();
        InformationCheckOutPageObject.FillInformationCheckoutData(input.get("firstname"),input.get("lastname"),input.get("postalcode"));
        CheckoutOverviewPageObject.ClickOnFinshBtn();
        Assert.assertTrue(CheckoutOverviewPageObject.TextIsDisplayed());

        Thread.sleep(3000);

    }



    @DataProvider
    public Object[][] retrieveData() throws IOException {
        // Get the user directory dynamically
        String userDir = System.getProperty("user.dir");

        // Construct the path to the JSON file relative to the user directory
        String filePath = userDir + "\\src\\test\\java\\Data\\DifferentLoginUser.json";

        // Read JSON data and convert it to a list of HashMaps
        List<HashMap<String, String>> data = GetJasonDataToMap(filePath);

        return new Object[][] {
                { data.get(5) },
        };





}}
