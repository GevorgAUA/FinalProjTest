package Pages;

import Utilities.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int numberOfProducts(){
        List<WebElement> titles = driver.findElements(Locators.TITLES);
        return titles.size();
    }

    public void clickOnRemoveButton(){

    }
}
