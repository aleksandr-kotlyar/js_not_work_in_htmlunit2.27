import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

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

    @Before
    public void setUp() {
        WebDriverRunner.setWebDriver(new ModifiedHtmlUnitDriver());
        System.out.println("-----------------USING HTMLUNITDRIVER DRIVER-----------------");
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
