package Utilities;

import org.openqa.selenium.By;

public class Locators
{
        public static final By HOME_BUTTON = By.className("main_logo");
        public static final By CATALOG = By.className("catalog_btn");
        public static final By SEARCH_BOX = By.id("search");
        public static final By CART_BAR = By.className("basket_block");
        public static final By COMPARE_PAGE = By.className("icon_compare");
        public static final By PRODUCT_PRICE = By.className("current_price");
        public static final By ADD_TO_CART_FROM_PRODUCT = By.className("add_to_cart");
        public static final By GO_TO_CART_BUTTON = By.xpath("//*[@class='action primary viewcart']");
        public static final By CART_LIST = By.id("shopping-cart-table");
        public static final By ADD_TO_COMPARE = By.className("tocompare");
        public static final By ADD_TO_COMPARE_FROM_PRODUCT_INFO = By.xpath("//button[text()='Համեմատել']");
        public static final By ADD_TO_CART_FROM_PAGE = By.xpath("/html/body/div[2]/main/div/div/div[4]/div/div[2]/div[2]/ol/li[1]/div/div/div[5]/a[2]");
        public static final By COUNTERS = By.className("items_count");
        public static final By TOTAL_CART_BAR = By.xpath("//*[@data-th='Ընդհանուր']");
        public static final By TOTAL_CART = By.xpath("//*[@data-th='ԸՆԴՀԱՆՈՒՐ ԳՈՒՄԱՐԸ՝']");
        public static final By TOTAL_HEADER = By.className("basket_total");
        public static final By TITLES = By.className("item_name");
        public static final By DELETE_BUTTON_FROM_CART_BAR_AND_COMPARE = By.className("delete");
        public static final By DELETE_BUTTON_FROM_CART_PAGE = By.className("item_remove");
        public static final By DISMISS_BUTTON = By.className("action-dismiss");
        public static final By ACCEPT_BUTTON = By.className("action-accept");
        public static final By PRODUCT_NAME_IN_RESULTS = By.className("product_name");
        public static final By ADD_TO_CART_FROM_COMPARE = By.className("tocart");
        public static final By SEARCH_SUBMIT = By.cssSelector("input[aria-label='Search']");
        public static final By SKU = By.cssSelector(".cell.product.attribute");
}
