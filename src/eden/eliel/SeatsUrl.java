package eden.eliel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eden on 8/8/2016.
 */
public class SeatsUrl {
    private final String SEAT_PATTERN = "_Seat_(\\d+)_(\\d+)";

    private WebDriver _webDriver;
    private JavascriptExecutor _js;
    private String _currentUrl;
    private ArrayList<Seat> _seatsArray;

    public SeatsUrl() throws InterruptedException {
        System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C://phantomjs.exe");
    }

    public ArrayList<Seat> getSeatsArray(String url){
        if (url.equals(_currentUrl))
            return _seatsArray;
        _currentUrl = url;
        _seatsArray = getSeatsFromUrl(_currentUrl);
        return _seatsArray;
    }

    private ArrayList<Seat> getSeatsFromUrl(String url){
        ArrayList<Seat> result = new ArrayList<>();

        try {
            _webDriver = new PhantomJSDriver();
            _js = (JavascriptExecutor) _webDriver;
            _webDriver.manage().window().maximize();

            String ticketUrl = getTicketsWeb(url);

            _webDriver.get(ticketUrl);
            Thread.sleep(2000);

            _js.executeScript("document.getElementsByClassName('ddlTicketQuantity')[0].value = '1'");
            _webDriver.findElement(By.id("ctl00_CPH1_lbNext1")).click();
            Thread.sleep(2000);

            _webDriver.findElement(By.id("ctl00_CPH1_SPC_imgSubmit2")).click();
            result = getSeatsFromSeatsElements(_webDriver.findElements(By.xpath("//*[@class='seat']")));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        _webDriver.close();

        return result;
    }
    private ArrayList<Seat> getSeatsFromSeatsElements(List<WebElement> seatsElements){
        ArrayList<Seat> result = new ArrayList<>();

        Pattern p = Pattern.compile(SEAT_PATTERN);
        Matcher matcher;

        for (WebElement seatElemnt : seatsElements){
            boolean status = isTaken(seatElemnt);
            matcher = p.matcher(seatElemnt.getAttribute("id"));
            matcher.find();
            result.add(new Seat(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)),status));
        }

        return result;
    }

    private boolean isTaken(WebElement seatElemnt) {
        String onclickString = seatElemnt.getAttribute("onclick");
        if (onclickString != null && onclickString.contains("Seat_OnClick"))
            return true;
        return false;
    }

    private String getTicketsWeb(String url) throws InterruptedException {
        _webDriver.get(url);
        Thread.sleep(2000);
        removeSnatch();
        return _webDriver.findElement(By.id("tix")).getAttribute("src");
    }
    private void removeSnatch(){
        if (_webDriver.findElements(By.id("snatchbg")).size() != 0)
            _js.executeScript("document.getElementById('snatchbg').remove()");
        if (_webDriver.findElements(By.id("snatch")).size() != 0)
            _js.executeScript("document.getElementById('snatch').remove()");
    }
}
