package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Eden on 8/6/2016.
 */
public class SpotSaver {
    private ChromeDriver _webDriver;
    private JavascriptExecutor _js;

    public SpotSaver() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
    }

    public void Execute() throws InterruptedException {
        _webDriver = new ChromeDriver();
        _js = (JavascriptExecutor) _webDriver;
        _webDriver.manage().window().maximize();

        while (true) {
            boolean needRefresh = true;
            _webDriver.get("https://tickets.yesplanet.co.il/ypa?key=1025&ec=10256080616-299888");
            Thread.sleep(2000);

            while (needRefresh) {
                if (_webDriver.findElements(By.id("snatchbg")).size() != 0)
                    _js.executeScript("document.getElementById('snatchbg').remove()");
                if (_webDriver.findElements(By.id("snatch")).size() != 0)
                    _js.executeScript("document.getElementById('snatch').remove()");
                _js.executeScript("document.getElementsByClassName('ddlTicketQuantity')[0].value = '3'");
                _webDriver.findElement(By.id("ctl00_CPH1_lbNext1")).click();
                Thread.sleep(2000);
                if (!_webDriver.findElement(By.id("seatsCounterDisplay")).getText().equals("3")) {
                    _webDriver.findElement(By.id("_Seat_8_6")).click();
                    _webDriver.findElement(By.id("_Seat_8_5")).click();
                    _webDriver.findElement(By.id("_Seat_8_4")).click();
                }
                if (!_webDriver.findElement(By.id("seatsCounterDisplay")).getText().equals("0"))
                    needRefresh = false;
                else
                    _webDriver.navigate().refresh();
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, 15);
            String newTime = dateFormat.format(cal.getTime());
            System.out.println("Taken till - " + newTime);

            _webDriver.findElement(By.id("ctl00_CPH1_SPC_imgSubmit2")).click();

            Thread.sleep(15 * 60000);
        }
    }
}