package Pages;

import org.openqa.selenium.WebDriver;
import Utilities.Locators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class ComparePage extends BasePage
{
    public ComparePage(WebDriver driver) {
        super(driver);
    }

    public int numberOfProducts(){
        List<WebElement> prices = driver.findElements(Locators.PRODUCT_PRICE);
        return prices.size();
}

    public void addToCartFromComparePage(){
        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.ADD_TO_CART_FROM_COMPARE));
        cartBtn.click();
    }

    public void removeFirstItem(){
        WebElement dltBtn = wait.until(ExpectedConditions.elementToBeClickable(Locators.DELETE_BUTTON_FROM_CART_BAR_AND_COMPARE));
        dltBtn.click();
    }

    public String getSKU(){
        WebElement sku = driver.findElement(Locators.SKU);
        return sku.getText();
    }
}
