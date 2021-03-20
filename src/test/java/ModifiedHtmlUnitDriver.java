import com.gargoylesoftware.css.parser.CSSErrorHandler;
import com.gargoylesoftware.css.parser.CSSException;
import com.gargoylesoftware.css.parser.CSSParseException;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class ModifiedHtmlUnitDriver extends HtmlUnitDriver {

    public ModifiedHtmlUnitDriver() {
        super(BrowserVersion.CHROME);
    }

    protected WebClient modifyWebClient(WebClient client) {
        client.setCssErrorHandler(new CSSErrorHandler() {
            @Override
            public void warning(CSSParseException arg0) throws CSSException {
            }

            @Override
            public void fatalError(CSSParseException arg0) throws CSSException {
            }

            @Override
            public void error(CSSParseException arg0) throws CSSException {
            }
        });

        client.getOptions().setCssEnabled(false);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);

        client.getOptions().setUseInsecureSSL(true);

        return client;
    }
}
