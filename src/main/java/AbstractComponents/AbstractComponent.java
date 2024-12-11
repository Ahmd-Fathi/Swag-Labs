package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {


    WebDriver driver;
    WebDriverWait wait;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Initialize wait once
    }
    public void WaiteVisibilityOfElement(By findBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }



    public void WaitElementBeClickable(By findBy)
    {

        wait.until(ExpectedConditions.elementToBeClickable(findBy));



    }


    public void WaitElementBeSelected(By findBy)
    {

        wait.until(ExpectedConditions.elementToBeSelected(findBy));


    }














}
