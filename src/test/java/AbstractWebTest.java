import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.annotation.Annotation;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static org.junit.Assert.fail;

public class AbstractWebTest {

    /**
     * rule for closing webdriver on finished() after manual setup with selenium
     */
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void finished(Description description) {
            closeWebdriver();
        }
    };

    private void closeWebdriver() {
        if (hasWebDriverStarted())
            getWebDriver().quit();
    }

    /**
     * default driver: htmlUnitDriver
     */
    private static final String HTML_UNIT_DRIVER = "htmlUnitDriver";
    private static final String CHROME = "chrome";

    @Before
    public void setUp() throws Exception {
        String driverName = "";

        /**
         * if test is annotated by @NonDefaultDriver -- use driverName from NonDefaultDriver
         */
        if (this.getClass().isAnnotationPresent(NonDefaultDriver.class)) {
            Annotation annotation = this.getClass().getAnnotation(NonDefaultDriver.class);
            NonDefaultDriver nonDefaultDriver = (NonDefaultDriver) annotation;
            driverName = nonDefaultDriver.driverName();
        }

        String webDriver = driverName.isEmpty() ? HTML_UNIT_DRIVER : driverName;
        /**
         * webDriver setup
         */
        if (CHROME.equalsIgnoreCase(webDriver)) {
            WebDriverRunner.setWebDriver(new CustomChromeDriver().createDriver(new DesiredCapabilities()));
            System.out.println("-----------------USING CHROME DRIVER-----------------");
        } else if (HTML_UNIT_DRIVER.equalsIgnoreCase(webDriver)) {
            WebDriverRunner.setWebDriver(new ModifiedHtmlUnitDriver());
            System.out.println("-----------------USING HTMLUNITDRIVER DRIVER-----------------");
        } else {
            fail("Set property 'driverName' to 'chrome' or 'htmlUnitDriver'");
        }

        Configuration.baseUrl = "https://www.google.ru";
        Configuration.timeout = 4000;
        Configuration.screenshots = true;
    }


    public void switchOnJavaScript() {
        if (isHtmlUnit()) {
            ((HtmlUnitDriver) getWebDriver()).setJavascriptEnabled(true);
        }
    }

    public void switchOffJavaScript() {
        if (isHtmlUnit()) {
            ((HtmlUnitDriver) getWebDriver()).setJavascriptEnabled(false);
        }
    }

    private boolean isHtmlUnit() {
        return getWebDriver() instanceof HtmlUnitDriver;
    }
}