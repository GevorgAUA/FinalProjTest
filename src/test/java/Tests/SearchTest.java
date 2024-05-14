package Tests;

import Pages.ResultsPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utilities.AssertionMessages.NO_MATCHES_MESSAGE;
import static Utilities.Parameters.*;

public class SearchTest extends TestBase{

//
//    //failing test, since behavioral bug was detected
//    @Test
//    public void searchOnlyAvailableForMinimumTwoCharacters()
//    {
//        driver.get("https://www.zigzag.am/");
//
//        //starting in home page, check if the search button is available for empty query.
//        HomePage homePage = new HomePage(driver);
//
//        homePage.search(emptyquery);
//        Assert.assertFalse(homePage.isInResultPage());
//
//        homePage.search(oneCharacterQuery);
//        Assert.assertFalse(homePage.isInResultPage());
//
//        homePage.search(twoCharacterQuery);
//        Assert.assertTrue(homePage.isInResultPage()); }


    @Test
    public void searchResultsContainKeyword() throws InterruptedException
    {
        driver.get("https://www.zigzag.am/");
        HomePage homePage = new HomePage(driver);

        //search for acer, see if there are at least 5 results that have it in  their titles.
        homePage.search(acerSearch);
        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.hasAtLeastFiveAcerTitlesImproved(), NO_MATCHES_MESSAGE);
    }

}
