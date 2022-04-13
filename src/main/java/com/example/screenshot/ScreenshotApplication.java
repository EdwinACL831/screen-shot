package com.example.screenshot;

import com.example.screenshot.controller.ScreenShotController;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ScreenshotApplication {

    private static String betPlayUrl = "https://gsm-adverts-uat.betstream.betgenius.com/singleadvertall/?c=MGM&cm=NBAH2H&scene=1";
    private static String googleUrl = "https://www.google.com";

    public static void main(String[] args) {
        SpringApplication.run(ScreenshotApplication.class, args);

        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver(new ChromeOptions().setHeadless(true));
        webDriver.manage().window().setSize(new Dimension(1920, 1080));

        ScreenShotController controller = new ScreenShotController(webDriver, betPlayUrl);

        try {
            controller.takeScreenShot();
        } catch (IOException e) {
            webDriver.quit();
            System.out.println(e.getMessage());
        }
    }

}
