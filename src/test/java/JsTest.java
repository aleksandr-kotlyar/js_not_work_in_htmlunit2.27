import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class JsTest extends AbstractWebTest {

    /**
     * to make test pass comment switchOnJavaScript
     * to make test fail uncomment switchOnJavaScript
     * default for htmlUnit: javaScript is off
     */
    @Test
    public void testHtmlUnitWithJS() throws Exception {
        //switchOnJavaScript();
        open("http://www.pearl.de/authorization/login.do");
        System.out.println("url opened: " + url());
        System.out.println("testHtmlUnitWithJS: passed");
    }
}