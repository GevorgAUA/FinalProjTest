package Pages;

import Utilities.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage{

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(){
        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.ADD_TO_CART_FROM_PAGE));
        cartBtn.click();
    }

    public void addToCompare(){
        WebElement cmprBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.ADD_TO_COMPARE));
        cmprBtn.click();
    }

}
