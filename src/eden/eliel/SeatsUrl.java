package eden.eliel;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eden on 8/8/2016.
 */
public class SeatsUrl {
    private final String USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";

    WebClient webClient = new WebClient();

    public SeatsUrl(){
        String newUrl = null;
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        try {
            newUrl = webClient.getPage("https://tickets.yesplanet.co.il/ypa/?key=1025&ec=10258080916-300536").getUrl().toString();
            Page page = webClient.getPage(newUrl);
            WebResponse response = page.getWebResponse();
            String content = response.getContentAsString();
            System.out.println(content);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
