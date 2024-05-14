package Pages;

import Utilities.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static Utilities.Locators.COUNTERS;

public abstract class BasePage
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToHomePage()
    {
        WebElement homeButton = driver.findElement(Locators.HOME_BUTTON);
        homeButton.click();
    }

    public String getCartCount()
    {
        // Find all elements with the class name "items_count"
        List<WebElement> countElements = driver.findElements(COUNTERS);
        return countElements.get(1).getText();
    }

    public String getCompareCount()
    {
        // Find all elements with the class name "items_count"
        List<WebElement> countElements = driver.findElements(COUNTERS);
        return countElements.get(0).getText();
    }

    public String getTotalFromCartBar() throws InterruptedException
    {
        Thread.sleep(3000);
        WebElement amount = driver.findElement(Locators.TOTAL_CART_BAR);
        return amount.getText();
    }

    public String getTotalFromHeader() throws InterruptedException
    {
        Thread.sleep(3000);
        WebElement headerAmount = driver.findElement(Locators.TOTAL_HEADER);
        return headerAmount.getText();
    }

    public String getTotalFromCart() throws InterruptedException
    {
        Thread.sleep(4000);
        WebElement headerAmount = driver.findElement(Locators.TOTAL_CART);
        return headerAmount.getText();
    }

    public void openCart()
    {
        WebElement cartButton = driver.findElement(Locators.CART_BAR);
        cartButton.click();
    }

    public void removeFromCartBar()
    {
        WebElement remove_btn = wait.until(ExpectedConditions.elementToBeClickable(Locators.DELETE_BUTTON_FROM_CART_BAR_AND_COMPARE));
        remove_btn.click();
    }

    public void declineRemoval()
    {
        WebElement decline_btn = wait.until(ExpectedConditions.elementToBeClickable(Locators.DISMISS_BUTTON));
        decline_btn.click();
    }

    public void acceptRemoval()
    {
        WebElement accept_btn = wait.until(ExpectedConditions.elementToBeClickable(Locators.ACCEPT_BUTTON));
        accept_btn.click();
    }

    public void removeFromCartPage()
    {
        WebElement remove_btn = wait.until(ExpectedConditions.elementToBeClickable(Locators.DELETE_BUTTON_FROM_CART_PAGE));
        remove_btn.click();
    }


    public void goToCartPagefromCartBar()
    {
        WebElement cartbutton = wait.until(ExpectedConditions.elementToBeClickable(Locators.GO_TO_CART_BUTTON));
        cartbutton.click();
    }

    public void goToComparePage()
    {
        WebElement compareButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.COMPARE_PAGE));
        compareButton.click();
    }


    public void clickSecondProductName()
    {
        List<WebElement> products = driver.findElements(Locators.PRODUCT_NAME_IN_RESULTS);
        products.get(1).click();
    }

    public void search(String query)
    {
        WebElement searchBox = driver.findElement(Locators.SEARCH_BOX);
        searchBox.clear();
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    public void fillSearchWith(String query)
    {
        WebElement searchBox = driver.findElement(Locators.SEARCH_BOX);
        searchBox.clear();
        searchBox.sendKeys(query);
    }

    public boolean isSubmitButtonClickable()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try
        {
            // Use WebDriverWait to wait until the element is clickable
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_SUBMIT));
            submitButton.click();
            // If the element is found and clickable, return true
            return true;
        } catch (Exception e)
        {
            // If the element is not clickable within the time frame, return false
            return false;
        }
    }

    public boolean isInResultPage()
    {
        // Retrieve the current URL from the driver
        String currentUrl = driver.getCurrentUrl();

        // Check if the URL contains the substring 'result'
        return currentUrl.contains("result");
    }
}