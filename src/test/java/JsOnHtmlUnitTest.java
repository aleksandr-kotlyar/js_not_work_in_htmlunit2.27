import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * test fail on HtmlUnit with JS enabled
 * test pass on HtmlUnit with JS disabled
 * default for HtmlUnit: javaScript is off
 */
public class JsOnHtmlUnitTest extends AbstractWebTest {

    /**
     * Test fail on HtmlUnit with JS enabled
     */
    @Test
    public void testOnHtmlUnitWithJS() {
        switchOnJavaScript();
        open("http://www.pearl.de/authorization/login.do");
        System.out.println("url opened: " + url());
        System.out.println("testHtmlUnitWithJS: passed");
    }

    /**
     * Test pass on HtmlUnit with JS disabled
     */
    @Test
    public void testOnHtmlUnitWithoutJS() {
        switchOffJavaScript();
        open("http://www.pearl.de/authorization/login.do");
        System.out.println("url opened: " + url());
        System.out.println("testHtmlUnitWithoutJS: passed");
    }
}
