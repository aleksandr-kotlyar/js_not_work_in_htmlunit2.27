import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ChromeBrowserTest {
    /**
     * Same test as JsOnHtmlUnitTest pass on Chrome
     */
    @Test
    public void testOnChrome() {
        open("http://www.pearl.de/authorization/login.do");
        System.out.println("url opened: " + url());
        System.out.println("testOnChrome: passed");
    }
}
