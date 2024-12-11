package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationCheckOutPage extends AbstractComponent {
    WebDriver driver;
    public  InformationCheckOutPage(WebDriver driver)
    {
        super(driver);

        this.driver=driver;

    }



    private By FirstNameField=By.id("first-name");

    private By LastNameField=By.id("last-name");

    private By PostalCodeField=By.id("postal-code");
    private By ContinueButton=By.name("continue");





public  CheckoutOverviewPage  FillInformationCheckoutData(String Firstname ,String LastName ,String postalcode)
{


    driver.findElement(FirstNameField).sendKeys(Firstname);

    driver.findElement(LastNameField).sendKeys(LastName);

    driver.findElement(PostalCodeField).sendKeys(postalcode);

    driver.findElement(ContinueButton).click();

    return new CheckoutOverviewPage(driver);

}







}
