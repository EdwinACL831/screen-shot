package com.example.screenshot.controller;

import com.example.screenshot.ScreenShotMaker;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class ScreenShotController {
    private final String url;
    private final WebDriver webDriver;

    public ScreenShotController(WebDriver webDriver, String url) {
        this.webDriver = webDriver;
        this.url = url;
    }

    public String takeScreenShot() throws IOException {
        ScreenShotMaker screenShot = new ScreenShotMaker(webDriver);
        String screenShotPath = screenShot.takeScreenShot(url, 0, 0, 400, 400, "PNG");
        String fullScreenShot = screenShot.takeScreenShot(url, "PNG");
        String elementScreenShotPath = screenShot.takeScreenShot(url, "iframe[src*='size=300x600']", "PNG");
        String elementScreenShotPath2 = screenShot.takeScreenShot(url, "#verticalAds", "PNG");

        webDriver.quit();
        return elementScreenShotPath2;
    }
}
