package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static Utilities.AssertionMessages.WRONG_COUNTER_MESSAGE;
import static Utilities.AssertionMessages.WRONG_PRICE_MESSAGE;
import static Utilities.Parameters.headphoneSearch;
import static Utilities.Parameters.otherHeadphoneSearch;


public class AddAndRemoveCartTest extends TestBase {


    @Test
    public void addingSingleProduct() throws InterruptedException {
        driver.get("https://www.zigzag.am/");

        //starting in home page, getting and checking that cart counter is 0
        HomePage homePage = new HomePage(driver);
        String cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "", WRONG_COUNTER_MESSAGE);

        //searching from homepage and getting to results page
        homePage.search(headphoneSearch);
        ResultsPage resultsPage = new ResultsPage(driver);

        //Hovering over the first items price, adding it to the cart.
        resultsPage.hoverOverPrice();
        resultsPage.addToCartFromProductInfo();

        //waiting for the cartcount to update, getting it and checking that it has updated to 1
        Thread.sleep(3000);
        cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "1", WRONG_COUNTER_MESSAGE);

        //getting and checking that the price total in the header is updated correspondingly
        String headertotal = resultsPage.getTotalFromHeader();
        Assert.assertEquals(headertotal, "9,500 ֏", WRONG_PRICE_MESSAGE);

        //opening the cart bar, getting and checking that the total price there is also updated correctly
        resultsPage.openCart();
        String totalCartBar = resultsPage.getTotalFromCartBar();
        Assert.assertEquals(totalCartBar, "9,500 ֏", WRONG_PRICE_MESSAGE);

        //going to the cart page from results page's cart bar and counting the number of items shown in the cart list.
        //checking that the number of items is 1
        resultsPage.goToCartPagefromCartBar();
        CartPage cartPage = new CartPage(driver);
        int itemsCount = cartPage.numberOfProducts();
        Assert.assertEquals(itemsCount, 1, WRONG_COUNTER_MESSAGE);

        //getting and checking the total from cart is correct.
        String totalCart = cartPage.getTotalFromCart();
        Assert.assertEquals(totalCart, "9,500 ֏", WRONG_PRICE_MESSAGE);

        //open cart and remove the item, on the following confirmation window, decline it.
        cartPage.openCart();
        cartPage.removeFromCartBar();
        cartPage.declineRemoval();

        //wait and assert that after declining the removal counter and price total did not change
        Thread.sleep(3000);
        cartcount = homePage.getCartCount();
        totalCartBar = resultsPage.getTotalFromCartBar();
        Assert.assertEquals(cartcount, "1", WRONG_COUNTER_MESSAGE);
        Assert.assertEquals(totalCart, "9,500 ֏", WRONG_PRICE_MESSAGE);


        //removing from bar but accepting the removal, checking that the cart was emptied.
        cartPage.removeFromCartBar();
        cartPage.acceptRemoval();
        Thread.sleep(3000);
        cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "", WRONG_COUNTER_MESSAGE);
    }

    @Test
    public void addingMultipleProducts() throws InterruptedException {
        driver.get("https://www.zigzag.am/");

        //starting in home page, getting and checking that cart counter is 0
        HomePage homePage = new HomePage(driver);
        String cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "", WRONG_COUNTER_MESSAGE);

        //searching from homepage and getting to results page
        homePage.search(headphoneSearch);
        ResultsPage resultsPage = new ResultsPage(driver);

        //Hovering over the first item's price, adding it to the cart.
        resultsPage.hoverOverPrice();
        resultsPage.addToCartFromProductInfo();

        //waiting for the cart count to update, getting it and checking that it has updated to 1
        Thread.sleep(3000);
        cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "1", WRONG_COUNTER_MESSAGE);

        //getting and checking that the price total in the header is updated correspondingly
        String headertotal = resultsPage.getTotalFromHeader();
        Assert.assertEquals(headertotal, "9,500 ֏", WRONG_PRICE_MESSAGE);

        //from results page searching for another specific headphone
        // clicking on the first item and going to product page and adding it to the cart
        resultsPage.search(otherHeadphoneSearch);
        resultsPage.clickOnFirstProduct();
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();

        //waiting for counter to update, getting and checking that it gets updated to 2
        Thread.sleep(3000);
        cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "2", WRONG_COUNTER_MESSAGE);

        //getting and checking the total in header to be equal to the sum of items' prices
        headertotal = resultsPage.getTotalFromHeader();
        Assert.assertEquals(headertotal, "11,500 ֏", WRONG_PRICE_MESSAGE);

        //cart bar opens automatically, getting and checking its total to be correct.
        String totalCartBar = resultsPage.getTotalFromCartBar();
        Assert.assertEquals(totalCartBar, "11,500 ֏", WRONG_PRICE_MESSAGE);

        //going from results page to cart page, getting number of items and checking if its equal to 2.
        resultsPage.goToCartPagefromCartBar();
        CartPage cartPage = new CartPage(driver);
        int itemsCount = cartPage.numberOfProducts();
        Assert.assertEquals(itemsCount, 2, WRONG_COUNTER_MESSAGE);

        //getting and checking that the total sum of prices is correct in the cart page.
        String totalCart = cartPage.getTotalFromCart();
        Assert.assertEquals(totalCart, "11,500 ֏", WRONG_PRICE_MESSAGE);

        //removing the first item using the "x" icon in cart page list
        // assuring that counter in header for items is 1, in list is also 1 and that the price is correctly updated
        cartPage.removeFromCartPage();
        itemsCount = cartPage.numberOfProducts();
        Assert.assertEquals(itemsCount, 1, WRONG_COUNTER_MESSAGE);
        totalCart = cartPage.getTotalFromCart();
        Assert.assertEquals(totalCart, "2,000 ֏", WRONG_PRICE_MESSAGE);
        cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "1", WRONG_COUNTER_MESSAGE);

        //removing the last item, ensuring that the header counter is empty and there are no items.
        cartPage.removeFromCartPage();
        cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "", WRONG_COUNTER_MESSAGE);
    }

    @Test
    public void addingFromComparePage() throws InterruptedException{
        driver.get("https://www.zigzag.am/");
        //starting in home page, getting and checking that cart counter is 0
        HomePage homePage = new HomePage(driver);
        String cartcount = homePage.getCartCount();
        Assert.assertEquals(cartcount, "", WRONG_COUNTER_MESSAGE);

        //searching for specific item
        homePage.search(headphoneSearch);
        ResultsPage resultsPage = new ResultsPage(driver);

        //we open the first product and add it to compare
        resultsPage.clickOnFirstProduct();
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCompare();
        Thread.sleep(3000);

        //going to compare page and adding the item to cart from there
        homePage.goToComparePage();
        ComparePage comparePage = new ComparePage(driver);
        comparePage.addToCartFromComparePage();

        //waiting for counter to update and checking it to be equal to 1
        Thread.sleep(3000);
        cartcount = comparePage.getCartCount();
        Assert.assertEquals(cartcount, "1", WRONG_COUNTER_MESSAGE);

        //getting and checking that the price total in the header is updated correspondingly
        String headertotal = resultsPage.getTotalFromHeader();
        Assert.assertEquals(headertotal, "9,500 ֏", WRONG_PRICE_MESSAGE);

        //opening the cart bar, getting and checking that the total price there is also updated correctly
        resultsPage.openCart();
        String totalCartBar = resultsPage.getTotalFromCartBar();
        Assert.assertEquals(totalCartBar, "9,500 ֏", WRONG_PRICE_MESSAGE);

        //going to the cart page from results page's cart bar and counting the number of items shown in the cart list.
        //checking that the number of items is 1
        resultsPage.goToCartPagefromCartBar();
        CartPage cartPage = new CartPage(driver);
        int itemsCount = cartPage.numberOfProducts();
        Assert.assertEquals(itemsCount, 1, WRONG_COUNTER_MESSAGE);

        //getting and checking the total from cart is correct.
        String totalCart = cartPage.getTotalFromCart();
        Assert.assertEquals(totalCart, "9,500 ֏", WRONG_PRICE_MESSAGE);
    }

}
