package AdTask;

import AdTask.pages.AdPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckAdvertisementWebSteps extends TestBase {
    public AdPage page;
    private Set<String> ads = new HashSet<String>();

    @Given("Web page \"([^\"]*)\"")
    public void openTargetUrl(String targetUrl) throws IOException {
        init();
        page = new AdPage(driver);
        page.openWebPage(targetUrl);
    }

    @And("I see that Memo section is empty")
    public void seeMemoSectionIsEmpty() {
        int adCount = getAdCount();
        page.seeMemoCounter(adCount);
        page.seeMemoSection(adCount);
    }

    @Then("I open first ad in section \"([^\"]*)\"")
    public void openAd(String sectionName) {
        driver.navigate().back();
        page.openSection(sectionName);
        page.openFirstAdEntry();
    }

    @And("I see a link \"Add to favorites\"")
    public void seeFavoritesLink() {
        page.seeAddToFavoritesLink();
    }

    @Then("I add an ad to favorites")
    public void addAnAdvertisementToFavorites() {
        page.addAdToMemoFromOpenEntry();
        ads.addAll(page.getAddedAds());
    }

    @Then("I see success alert")
    public void seeAlertMessage() {
        page.seeSuccessMessage();
        page.closeSuccessMessage();
    }

    @And("I see \"Memo\" section contain added ad URL")
    public void seeMemoSectionIsNotEmpty() {
        int adCount = getAdCount();
        page.seeMemoCounter(adCount);
        page.seeMemoSection(adCount);
        page.seeMemoContainAd(ads);
    }

    @When("I try to add the same ad")
    public void addTheSameAd() {
        for (String i : ads) {
            page.addSameAdToMemoAgain(i); // For testing that adding the same ad does not add a duplicate
            break;
        }

        page.seeSuccessMessage();
        page.closeSuccessMessage();
    }

    @Then("I see no changes")
    public void seeNoChanges() {
        int adCount = getAdCount();
        page.seeMemoCounter(adCount);
        page.seeMemoSection(adCount);
        page.seeMemoContainAd(ads);
    }

    @Given("Search section \"([^\"]*)\"")
    public void openSearchSection(String url) {
        page.openWebPage(url);
    }

    @When("I search for \"([^\"]*)\"")
    public void searchValue(String value) {
        page.searchForValue(value);
    }

    @And("I add (\\d+) more ads")
    public void addAdsFromSearchSection(int count) {
        page.addAdsToMemoFromSearch(count);
        page.seeAddToFavoritesLink();
        ads.addAll(page.getAddedAds());
        page.seeSuccessMessage();
        page.closeSuccessMessage();
    }

    @When("I delete all ads from Memo")
    public void removeAllAdsFromMemo() {
        page.deleteAllAds();
        ads.clear();
    }

    private int getAdCount() {
        return ads.size();
    }
}
