package Tests;

import Pages.ComparePage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utilities.AssertionMessages.*;
import static Utilities.Parameters.headphoneSKU;
import static Utilities.Parameters.headphoneSearch;

public class AddAndRemoveCompareTest extends TestBase{

    @Test
    public void cannotCompareSameItem(){
        driver.get("https://www.zigzag.am/");

        //starting in homepage, we assert that compare counter is zero
        HomePage homePage = new HomePage(driver);
        String compareCount = homePage.getCompareCount();
        Assert.assertEquals(compareCount, "");

        //we hover over the first item and add it to the compare list.
        homePage.hoverOverPrice();
        homePage.addToCompareFromProductInfo();

        //we repeat the same process for same item
        homePage.hoverOverPrice();
        homePage.addToCompareFromProductInfo();

        //checking that the counter is 1
        compareCount = homePage.getCompareCount();
        Assert.assertEquals(compareCount, "1");

        //going to compare page and checking if the list has only one item. i.e. it wasnt added twice.
        homePage.goToComparePage();
        ComparePage comparePage = new ComparePage(driver);

        Assert.assertEquals(comparePage.numberOfProducts(), 1, CANNOT_COMPARE_SAME_ITEM_MESSAGE);
    }

    @Test
    public void comparePagePreservesCorrectParameters() throws InterruptedException
    {
        driver.get("https://www.zigzag.am/");
        HomePage homePage = new HomePage(driver);

        homePage.search(headphoneSearch);
        ResultsPage resultsPage = new ResultsPage(driver);

        resultsPage.clickOnFirstProduct();
        ProductPage productPage = new ProductPage(driver);

        productPage.addToCompare();
        Thread.sleep(2000);
        productPage.goToComparePage();

        ComparePage comparePage = new ComparePage(driver);
        Assert.assertEquals(comparePage.getSKU(), headphoneSKU, WRONG_SKU_NUMBER);
    }

    @Test
    public void cannotOpenComparePageBlank(){
        driver.get("https://www.zigzag.am/");
        //starting in homepage, we assert that compare counter is zero
        HomePage homePage = new HomePage(driver);
        String compareCount = homePage.getCompareCount();
        Assert.assertEquals(compareCount, "", WRONG_COUNTER_MESSAGE);

        //we attempt to go to the compare page. (i couldn't test if it is clickable or not due to weird way it was developed)
        homePage.goToComparePage();

        //Alternative: Check if the URL contains the substring 'product_compare'
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("product_compare"), EMPTY_COMPARE_PAGE_MESSAGE);
    }

    @Test
    public void addingMultipleItemsToCompareAndRemoving() throws InterruptedException
    {
        {
            driver.get("https://www.zigzag.am/");
            //starting in homepage, we assert that compare counter is zero
            HomePage homePage = new HomePage(driver);
            String compareCount = homePage.getCompareCount();
            Assert.assertEquals(compareCount, "", WRONG_COUNTER_MESSAGE);

            //add first item to compare from hovering
            homePage.hoverOverPrice();
            homePage.addToCompareFromProductInfo();

            //add second item to compare from product page and go to compare page, check the counter to update correctly
            homePage.clickSecondProductName();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCompare();
            productPage.goToComparePage();
            ComparePage comparePage = new ComparePage(driver);
            Thread.sleep(4000);
            compareCount = homePage.getCompareCount();
            Assert.assertEquals(compareCount, "2", WRONG_COUNTER_MESSAGE);

            //ensure that number of items are correct
            Assert.assertEquals(comparePage.numberOfProducts(), 2, WRONG_COUNTER_MESSAGE);

            //attempting to remove then declining doesnt make updates to the counters
            comparePage.removeFirstItem();
            comparePage.declineRemoval();
            compareCount = comparePage.getCompareCount();
            Thread.sleep(3000);
            Assert.assertEquals(compareCount, "2", WRONG_COUNTER_MESSAGE);
            Assert.assertEquals(comparePage.numberOfProducts(), 2, WRONG_COUNTER_MESSAGE);

            //attempting to remove then accepting makes updates to the counters
            Thread.sleep(3000);
            comparePage.removeFirstItem();
            comparePage.acceptRemoval();
            compareCount = comparePage.getCompareCount();
            Thread.sleep(3000);
            Assert.assertEquals(compareCount, "1");
            Assert.assertEquals(comparePage.numberOfProducts(), 1, WRONG_COUNTER_MESSAGE);

            //again attempting to remove the final item then accepting makes updates to the counters
            comparePage.removeFirstItem();
            comparePage.acceptRemoval();
            compareCount = comparePage.getCompareCount();
            Thread.sleep(3000);
            Assert.assertEquals(compareCount, "", WRONG_COUNTER_MESSAGE);
            Assert.assertEquals(comparePage.numberOfProducts(), 0, WRONG_COUNTER_MESSAGE);
        }
    }
}
