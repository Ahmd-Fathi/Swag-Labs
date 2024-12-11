package TestCases;

import Base.TestBase;

import Base.retray;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AddProductWithAllUserNamesTest extends TestBase {

    @Test(dataProvider = "retrieveData", groups = "error handling", retryAnalyzer = retray.class)
    public void TC_AllUserAddToCart(HashMap<String,String> input ) throws InterruptedException {
        // Login using provided credentials
        LoginPageObject.fillLoginData(input.get("username"), input.get("password"));

        // Assertions and actions after login
        inventoryPageObject.productWordAssertAfterReachedInventoryPage();
        inventoryPageObject.selectThreeItems();
        inventoryPageObject.ClickAddToCartLinkTextBtn();
        CartPageObject.AssertionForFirstItems();
        CartPageObject.AssertionForSecondItems();
    }


        @DataProvider
        public Object[][] retrieveData() throws IOException {
            // Get the user directory dynamically
            String userDir = System.getProperty("user.dir");

            // Construct the path to the JSON file relative to the user directory
            String filePath = userDir + "\\src\\test\\java\\Data\\DifferentLoginUser.json";

            // Read JSON data and convert it to a list of HashMaps
            List<HashMap<String, String>> data = GetJasonDataToMap(filePath);

            // Returning the data as Object[][] (two-dimensional array of objects)
            return new Object[][] {
                    { data.get(0) },  // First row of data
                    { data.get(1) },
                    {data.get(2)},
                    {data.get(3)},
                    {data.get(4)}// Second row of data
            };


    }
}
