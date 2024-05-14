package Pages;

import Utilities.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends BasePage {
    public ResultsPage(WebDriver driver) { super(driver); }

    public void hoverOverPrice(){
        WebElement price = wait.until(ExpectedConditions.elementToBeClickable(Locators.PRODUCT_PRICE));
        Actions action = new Actions(driver);
        action.moveToElement(price).perform();
    }

    public void addToCartFromProductInfo(){
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.ADD_TO_CART_FROM_PRODUCT));
        addButton.click();
    }
    public void addToCompareFromProductInfo(){
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.ADD_TO_COMPARE_FROM_PRODUCT_INFO));
        addButton.click();
    }


    public void clickOnFirstProduct(){
        WebElement itemName = wait.until(ExpectedConditions.elementToBeClickable(Locators.PRODUCT_NAME_IN_RESULTS));
        itemName.click();
    }

    public boolean hasAtLeastFiveAcerTitlesImproved() {
        // Retrieve all elements using the provided locator
        List<WebElement> elements = driver.findElements(Locators.TITLES);

        // Create a list to store the processed texts
        List<String> texts = new ArrayList<>();

        // Process each element's text
        for (WebElement element : elements) {
            // Trim and convert to lowercase each element's text then add it to the list
            texts.add(element.getText().trim().toLowerCase());
        }

        // Initialize a counter for texts containing "acer"
        int acerCount = 0;

        // Iterate over the processed texts and count how many contain "acer"
        for (String text : texts) {
            if (text.contains("acer")) {
                acerCount++;
            }
        }

        // Check if there are at least 5 texts with "acer"
        return acerCount <= 5;
    }



}
