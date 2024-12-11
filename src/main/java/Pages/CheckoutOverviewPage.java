package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends AbstractComponent {
    WebDriver driver;
    public  CheckoutOverviewPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;

    }

private By FinshBtn=By.id("finish");

    private  By ConfirmationMessage=By.xpath("//*[text()=\"Thank you for your order!\"]");



    public  void ClickOnFinshBtn()
    {

        driver.findElement(FinshBtn).click();
    }

public  boolean TextIsDisplayed()
{

  return   driver.findElement(ConfirmationMessage).isDisplayed();

}



}
