package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public  CartPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;


    }

private By FirstItemSauceLabsBackpack= By.xpath("//*[text()=\"Sauce Labs Backpack\"]");

private  By SecondItemSauceLabsBikeLight=By.xpath("//*[text()=\"Sauce Labs Bike Light\"]");

private By CheckOutBtn=By.id("checkout");


public  boolean  AssertionForFirstItems()
{

    WaiteVisibilityOfElement(FirstItemSauceLabsBackpack);
  return   driver.findElement(FirstItemSauceLabsBackpack).isDisplayed();


}

public boolean AssertionForSecondItems()
{
    WaiteVisibilityOfElement(SecondItemSauceLabsBikeLight);

   return driver.findElement(SecondItemSauceLabsBikeLight).isDisplayed();
}

public  InformationCheckOutPage  ClickCheckOutBtn()
{


    driver.findElement(CheckOutBtn).click();

    return  new InformationCheckOutPage(driver);
}



}
