package AdTask.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdPage extends BasePage {
    // Selectors
    public static final String MEMO_LINK_COUNTER_SELECTOR = "span.page_header_menu span span#mnu_fav_id";
    public static final String MEMO_LINK_SELECTOR = "span.page_header_menu span a.a_menu";
    public static final String FIRST_SUBCATEGORY_LINK_SELECTOR = "h4.category:nth-of-type(1) a";
    public static final String AD_ENTRY_TITLE_SELECTOR = ".msg2";
    public static final String AD_ENTRY_SELECTOR = "table#page_main tbody tr td table:nth-of-type(2) tr";
    public static final String AD_ENTRY_CHECKBOX_SHORT_SELECTOR = "td.msga2.pp0 input";
    public static final String AD_ENTRY_LINK_SHORT_SELECTOR = "td.msga2 a";
    public static final String FIRST_AD_ENTRY_LINK_SELECTOR = "form#filter_frm table:nth-of-type(2) tr:nth-of-type(2) a.am";
    public static final String AD_ENTRY_CHECKBOX_SELECTOR = "table#page_main tbody tr td table:nth-of-type(2) " +
            "tr:nth-of-type(%d) td.msga2.pp0 input";
    public static final String ADD_TO_MEMO_AD_SECTION_LINK_SELECTOR = "table#page_main tbody tr:nth-of-type(2) table " +
            "tbody tr:nth-of-type(2) td.msg_footer a";
    public static final String ADD_TO_MEMO_SEARCH_SECTION_LINK_SELECTOR = "table#page_main tr td table:nth-of-type(4) " +
            "tr:nth-of-type(2) td a";
    public static final String ADDING_SUCCESS_ALERT_TITLE_SELECTOR = "body div.alert_dv div.alert_head_left";
    public static final String ADDING_SUCCESS_ALERT_MSG_SELECTOR = "body div.alert_dv div.alert_body div#alert_msg";
    public static final String ADDING_SUCCESS_ALERT_CLOSE_BUTTON_SELECTOR = "body div.alert_dv div.alert_body a#alert_ok";
    public static final String SEARCH_SECTION_MAIN_TEXT_FIELD_SELECTOR = "form table tbody tr:nth-of-type(2) input";
    public static final String REMOVE_ADS_FROM_MEMO_LINK_SELECTOR = "div.d12:nth-of-type(4) a";

    // Texts
    public static final String WEB_PAGE_IS_NOT_OPENED_TEXT = "Web page is not opened!";
    public static final String PAGE_IS_OPENED = "%s is opened!";
    public static final String MAIN_SECTION_TITLE = "a[title=\"%s, Announcements\"]";
    public static final String MEMO_COUNTER_IS_CORRECT_MESSAGE = "Memo counter is correct! Value: \"%s\"";
    public static final String MEMO_COUNTER_IS_NOT_CORRECT_MESSAGE = "Memo counter is not correct! Value: \"%s\" " +
            "Must be: \"%s\"";
    public static final String ADD_TO_MEMO_AD_SECTION_LINK_TEXT = "Add to favorites";
    public static final String ADD_TO_MEMO_SEARCH_SECTION_LINK_TEXT = "Pievienot izvēlētos Memo";
    public static final String ADDING_SUCCESS_ALERT_TITLE_TEXT = "Attention";
    public static final String ADDING_SUCCESS_ALERT_MSG_AD_SECTION_TEXT = "Advertisement added to favorites.";
    public static final String ADDING_SUCCESS_ALERT_MSG_SEARCH_SECTION_TEXT = "Sludinājumi ir pievienoti Memo.";
    public static final String AD_FOUND_MESSAGE = "Ad is found: %s";
    public static final String AD_NOT_FOUND_MESSAGE = "Ad is NOT found: %s";
    public static final String OPEN_AD_MODE = "msg";
    public static final String OPEN_SEARCH_MODE = "search-result";
    public static final String OPEN_MEMO_SECTION = "favorites";

    /**
     * @param driver
     */
    public AdPage(WebDriver driver) {
        super(driver);
    }

    /**
     * @param targetUrl
     */
    public void openWebPage(String targetUrl) {
        driver.get(targetUrl);

        try {
            assertEquals(targetUrl, driver.getCurrentUrl());
            System.out.println(getPageIsOpenedMessage(targetUrl));
        } catch (AssertionError e) {
            System.out.println(WEB_PAGE_IS_NOT_OPENED_TEXT);
            throw e;
        }
    }

    /**
     * @param memoAdCount
     */
    public void seeMemoCounter(int memoAdCount) {
        String counter;

        String memoCounterValue = driver.findElement(By.cssSelector(
                MEMO_LINK_COUNTER_SELECTOR)).getText().replaceAll("\\D+", "");

        if (memoAdCount == 0) {
            counter = "";
        } else {
            counter = Integer.toString(memoAdCount);
        }

        try {
            assertEquals(counter, memoCounterValue);
            System.out.println(getCorrectCounterAssertMessage(counter));
        } catch (AssertionError ae) {
            System.out.println(getIncorrectCounterAssertMessage(memoCounterValue, counter));
        }
    }

    /**
     * @param memoAdCount
     */
    public void seeMemoSection(int memoAdCount) {
        if (!isMemoSectionOpened(driver.getCurrentUrl())) {

            driver.findElement(By.cssSelector(MEMO_LINK_SELECTOR)).click();
        }

        if (memoAdCount == 0) {
            assertTrue(driver.findElements(By.cssSelector(AD_ENTRY_TITLE_SELECTOR)).isEmpty());
        }
        else {
            assertTrue(driver.findElements(By.cssSelector(AD_ENTRY_TITLE_SELECTOR)).size() > 0);
        }
    }

    /**
     * @param sectionName
     */
    public void openSection(String sectionName) {
        driver.findElement(By.cssSelector(getMainSectionTitle(sectionName))).click();
        driver.findElement(By.cssSelector(FIRST_SUBCATEGORY_LINK_SELECTOR)).click();
    }

    public void openFirstAdEntry() {
        driver.findElement(By.cssSelector(FIRST_AD_ENTRY_LINK_SELECTOR)).click();
    }

    /**
     * @param url
     * @return
     */
    private boolean isAdOpened(String url) {
        if (url.contains(OPEN_AD_MODE)) {
            return true;
        }
        return false;
    }

    /**
     * @param url
     * @return
     */
    private boolean isSearchSectionOpened(String url) {
        if (url.contains(OPEN_SEARCH_MODE)) {
            return true;
        }
        return false;
    }

    /**
     * @param url
     * @return
     */
    private boolean isMemoSectionOpened(String url) {
        if (url.contains(OPEN_MEMO_SECTION)) {
            return true;
        }
        return false;
    }

    public void seeAddToFavoritesLink() {
        String currentUrl = driver.getCurrentUrl();
        String linkText;

        if (isAdOpened(currentUrl)) {
            linkText = driver.findElement(By.cssSelector(ADD_TO_MEMO_AD_SECTION_LINK_SELECTOR)).getText();
            assertEquals(ADD_TO_MEMO_AD_SECTION_LINK_TEXT, linkText);
        }
        else if (isSearchSectionOpened(currentUrl)) {
            linkText = driver.findElement(By.cssSelector(ADD_TO_MEMO_SEARCH_SECTION_LINK_SELECTOR)).getText();
            assertEquals(ADD_TO_MEMO_SEARCH_SECTION_LINK_TEXT, linkText);
        }
    }

    public void addAdToMemoFromOpenEntry() {
        driver.findElement(By.cssSelector(ADD_TO_MEMO_AD_SECTION_LINK_SELECTOR)).click();
    }

    /**
     * @param count
     */
    public void addAdsToMemoFromSearch(int count) {
        for (int i = 2; i < count + 2; i++) {
            driver.findElement(By.cssSelector(getAdCheckboxSelector(i))).click();
        }
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(ADD_TO_MEMO_SEARCH_SECTION_LINK_SELECTOR)));
        seeAddToFavoritesLink();
        driver.findElement(By.cssSelector(ADD_TO_MEMO_SEARCH_SECTION_LINK_SELECTOR)).click();
    }

    /**
     * @return
     */
    public ArrayList<String> getAddedAds() {
        ArrayList<String> adUrls = new ArrayList<>();
        String currentUrl = driver.getCurrentUrl();

        if (isAdOpened(currentUrl)) {
            adUrls.add(currentUrl);
        }
        else if (isSearchSectionOpened(currentUrl) && driver.findElements(By.cssSelector(AD_ENTRY_TITLE_SELECTOR)).size() > 0 ) {
            boolean firstRow = true;

            for (WebElement elem : driver.findElements(By.cssSelector(AD_ENTRY_SELECTOR))) {
                try {
                    if (!firstRow && elem.findElement(By.cssSelector(AD_ENTRY_CHECKBOX_SHORT_SELECTOR)).isSelected()) {
                        adUrls.add(elem.findElement(By.cssSelector(AD_ENTRY_LINK_SHORT_SELECTOR)).getAttribute("href"));
                    }
                } catch (NoSuchElementException ex) {
                    continue;
                }
                firstRow = false;
            }
        }

        return adUrls;
    }

    public void seeSuccessMessage() {
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ADDING_SUCCESS_ALERT_MSG_SELECTOR)));

        if (isAdOpened(currentUrl)) {
            assertEquals(ADDING_SUCCESS_ALERT_MSG_AD_SECTION_TEXT, driver.findElement(By.cssSelector(ADDING_SUCCESS_ALERT_MSG_SELECTOR)).getText());
        }
        else if (isSearchSectionOpened(currentUrl)) {
            assertEquals(ADDING_SUCCESS_ALERT_MSG_SEARCH_SECTION_TEXT, driver.findElement(By.cssSelector(ADDING_SUCCESS_ALERT_MSG_SELECTOR)).getText());
        }

        assertEquals(ADDING_SUCCESS_ALERT_TITLE_TEXT, driver.findElement(By.cssSelector(ADDING_SUCCESS_ALERT_TITLE_SELECTOR)).getText());
    }

    public void closeSuccessMessage() {
        driver.findElement(By.cssSelector(ADDING_SUCCESS_ALERT_CLOSE_BUTTON_SELECTOR)).click();
    }

    /**
     * @param adUrls
     */
    public void seeMemoContainAd(Set<String> adUrls) {
        ArrayList<WebElement> adSearchElements = new ArrayList<>();
        adSearchElements.addAll(driver.findElements(By.cssSelector(AD_ENTRY_TITLE_SELECTOR)));

        for (String url : adUrls) {
            boolean adFound = false;

            for (WebElement element : adSearchElements) {
                if (element.findElement(By.cssSelector("a")).getAttribute("href").equals(url)) {
                    adFound = true;
                    break;
                }
            }

            try {
                assertTrue(adFound);
            } catch (AssertionError e) {
                System.out.println(getAdIsNotFoundMessage(url));
                throw e;
            }
        }
    }

    /**
     * @param adUrl
     */
    public void addSameAdToMemoAgain(String adUrl) {
        driver.get(adUrl);
        seeAddToFavoritesLink();
        addAdToMemoFromOpenEntry();
    }

    /**
     * @param value
     */
    public void searchForValue(String value) {
        driver.findElement(By.cssSelector(SEARCH_SECTION_MAIN_TEXT_FIELD_SELECTOR)).sendKeys(value + Keys.ENTER);
    }

    public void deleteAllAds() {
        for (WebElement element : driver.findElements(By.cssSelector(AD_ENTRY_CHECKBOX_SHORT_SELECTOR))) {
            element.click();
        }

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(REMOVE_ADS_FROM_MEMO_LINK_SELECTOR)));
        driver.findElement(By.cssSelector(REMOVE_ADS_FROM_MEMO_LINK_SELECTOR)).click();
    }

    /**
     * @param s
     * @return
     */
    private String getPageIsOpenedMessage(String s) {
        return String.format(PAGE_IS_OPENED, s);
    }

    /**
     * @param s
     * @return
     */
    private String getMainSectionTitle(String s) {
        return String.format(MAIN_SECTION_TITLE, s);
    }

    /**
     * @param s
     * @return
     */
    private String getAdIsFoundMessage(String s) {
        return String.format(AD_FOUND_MESSAGE, s);
    }

    /**
     * @param s
     * @return
     */
    private String getAdIsNotFoundMessage(String s) {
        return String.format(AD_NOT_FOUND_MESSAGE, s);
    }

    /**
     * @param s
     * @return
     */
    private String getCorrectCounterAssertMessage(String s) {
        return String.format(MEMO_COUNTER_IS_CORRECT_MESSAGE, s);
    }

    /**
     * @param actual
     * @param expected
     * @return
     */
    private String getIncorrectCounterAssertMessage(String actual, String expected) {
        return String.format(MEMO_COUNTER_IS_NOT_CORRECT_MESSAGE, actual, expected);
    }

    /**
     * @param i
     * @return
     */
    private String getAdCheckboxSelector(int i) {
        return String.format(AD_ENTRY_CHECKBOX_SELECTOR, i);
    }
}
