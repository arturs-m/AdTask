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
    public AdPage ap;
    private Set<String> ads = new HashSet<String>();

    @Given("Web page \"([^\"]*)\"")
    public void openTargetUrl(String targetUrl) throws IOException {
        init();
        ap = new AdPage(driver);
        ap.openWebPage(targetUrl);
    }

    @And("I see that Memo section is empty")
    public void seeMemoSectionIsEmpty() {
        ap.seeMemoCounter(getAdCount());
        ap.seeMemoSection(getAdCount());
    }

    @Then("I open first ad in section \"([^\"]*)\"")
    public void openAd(String sectionName) {
        driver.navigate().back();
        ap.openSection(sectionName);
        ap.openFirstAdEntry();
    }

    @And("I see a link \"Add to favorites\"")
    public void seeFavoritesLink() {
        ap.seeAddToFavoritesLink();
    }

    @Then("I add an ad to favorites")
    public void addAnAdvertisementToFavorites() {
        ap.addAdToMemoFromOpenEntry();
        ads.addAll(ap.getAddedAds());
    }

    @Then("I see success alert")
    public void seeAlertMessage() {
        ap.seeSuccessMessage();
        ap.closeSuccessMessage();
    }

    @And("I see \"Memo\" section contain added ad URL")
    public void seeMemoSectionIsNotEmpty() {
        ap.seeMemoCounter(getAdCount());
        ap.seeMemoSection(getAdCount());
        ap.seeMemoContainAd(ads);
    }

    @When("I try to add the same ad")
    public void addTheSameAd() {
        for (String i : ads) {
            ap.addSameAd(i);
            break;
        }

        ap.seeSuccessMessage();
        ap.closeSuccessMessage();
    }

    @Then("I see no changes")
    public void seeNoChanges() {
        ap.seeMemoCounter(getAdCount());
        ap.seeMemoSection(getAdCount());
        ap.seeMemoContainAd(ads);
    }

    @Given("Search section \"([^\"]*)\"")
    public void openSearchSection(String url) {
        ap.openWebPage(url);
    }

    @When("I search for \"([^\"]*)\"")
    public void searchValue(String value) {
        ap.searchForValue(value);
    }

    @And("I add (\\d+) more ads")
    public void addAdsFromSearchSection(int count) {
        ap.addAdsToMemoFromSearch(count);
        ap.seeAddToFavoritesLink();
        ads.addAll(ap.getAddedAds());
        ap.seeSuccessMessage();
        ap.closeSuccessMessage();
    }

    @When("I delete all ads from Memo")
    public void removeAllAdsFromMemo() {
        ap.deleteAllAds();
        ads.clear();
    }

    private int getAdCount() {
        return ads.size();
    }
}
