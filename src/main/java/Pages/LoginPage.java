package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractComponent {
WebDriver driver;
public  LoginPage(WebDriver driver)
{
    super(driver);
    this.driver=driver;
}
    private By usernameField=By.cssSelector("#user-name");
    private By PasswordField=By.name("password");
    private By LoginButton=By.cssSelector(".submit-button");




    public inventoryPage fillLoginData(String WriteUserName , String writePassword  )
    {
        driver.findElement(usernameField).sendKeys(WriteUserName);
        driver.findElement(PasswordField).sendKeys(writePassword);
        driver.findElement(LoginButton).click();
        return new  inventoryPage(driver);

    }















}
