package eden.eliel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Eden on 8/6/2016.
 */
public class SpotSaver {
    private final int SEAT_DURATION = 15;
    private final String CHROME_DRIVE_PACKAGE="webdriver.chrome.driver";

    private ChromeDriver _webDriver;
    private JavascriptExecutor _js;

    public SpotSaver() {
        System.setProperty(CHROME_DRIVE_PACKAGE, "C://chromedriver.exe");
    }

    public void Execute(String url, String [] seats) throws InterruptedException {
        String ticketUrl;

        _webDriver = new ChromeDriver();
        _js = (JavascriptExecutor) _webDriver;
        _webDriver.manage().window().maximize();

        _webDriver.get(url);
        System.out.println(_js.executeScript("return document.readyState"));
        Thread.sleep(2000);
        removeSnatch();
        ticketUrl = _webDriver.findElement(By.id("tix")).getAttribute("src");

        while (true) {
            boolean needRefresh = true;
            _webDriver.get(ticketUrl);
            Thread.sleep(2000);

            _js.executeScript("document.getElementsByClassName('ddlTicketQuantity')[0].value = '" + seats.length + "'");
            _webDriver.findElement(By.id("ctl00_CPH1_lbNext1")).click();

            while (needRefresh) {
                Thread.sleep(2000);
                if (!_webDriver.findElement(By.id("seatsCounterDisplay")).getText().equals("" + seats.length))
                    selectSeats(seats);
                if (!_webDriver.findElement(By.id("seatsCounterDisplay")).getText().equals("0"))
                    needRefresh = false;
                else
                    _webDriver.navigate().refresh();
            }
            printTakenTime();

            _webDriver.findElement(By.id("ctl00_CPH1_SPC_imgSubmit2")).click();

            Thread.sleep(SEAT_DURATION * 60000);
        }
    }

    private void selectSeats(String [] seats){
        for (String seat : seats){
            _webDriver.findElement(By.id(seat)).click();
        }
    }
    private void printTakenTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, SEAT_DURATION);
        String newTime = dateFormat.format(cal.getTime());
        System.out.println("Taken till - " + newTime);
    }
    private void removeSnatch(){
        if (_webDriver.findElements(By.id("snatchbg")).size() != 0)
            _js.executeScript("document.getElementById('snatchbg').remove()");
        if (_webDriver.findElements(By.id("snatch")).size() != 0)
            _js.executeScript("document.getElementById('snatch').remove()");
    }
}