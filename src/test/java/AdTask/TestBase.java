package AdTask;

import junit.textui.TestRunner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBase {
    protected WebDriver driver;
    protected TestRunner target;
    protected Properties properties = new Properties();
    protected String OS = (System.getProperty("os.name")).toUpperCase();

    private void getProperties() throws IOException {
        String propertiesFileName = "config.properties";

        InputStream propInput = new FileInputStream(System.getProperty("user.dir") + "/"
                + propertiesFileName);
        properties.load(propInput);
    }

    private void setChromeDriverLocationProperty() {
        if (OS.contains("WIN")) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriver"));
        }
    }

    private void launchChrome(boolean options) {
        if (options) {
            driver = new ChromeDriver();
        } else {
            driver = new ChromeDriver();
        }
    }

    protected void init() throws IOException {
        getProperties();
        setChromeDriverLocationProperty();
        target = new TestRunner();
        boolean options = false;

        launchChrome(options);
        Dimension d = new Dimension(1600, 900);
        driver.manage().window().setSize(d);
    }

    /**
     * Close browser
     */
    protected void tearDown()
    {
        driver.quit();
    }
}
