package AdTask.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    /**
     * @param driver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
