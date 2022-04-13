package com.example.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenShotMaker {
    private static final String TEMP_SCREEN_SHOT_PATH = System.getProperty("user.dir") + File.separator + "temp-screen-shot" + File.separator;

    private final WebDriver webDriver;
    private File fullScreenShotFile;

    public ScreenShotMaker(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String takeScreenShot(String url, String format) throws IOException {
        return takeFullScreenShot(url, format,true);
    }

    public String takeScreenShot(String url, int startXPosition, int startYPosition, int width, int height, String format) throws IOException {
        takeFullScreenShot(url, format, false);

        BufferedImage buffSubImage = ImageIO.read(this.fullScreenShotFile).getSubimage(startXPosition, startYPosition, width, height);

        return takeElementScreenShot(format, buffSubImage);
    }

    public String takeScreenShot(String url, String elementCssSelector, String format) throws IOException {
        takeFullScreenShot(url, format, false);
        WebElement element = this.webDriver.findElement(By.cssSelector(elementCssSelector));

        return takeScreenShot(url, element.getLocation().getX(), element.getLocation().getY(), element.getSize().getWidth(), element.getSize().getHeight(), format);
    }

    private String takeFullScreenShot(String url, String format, boolean createFullScreenFile) throws IOException {
        this.webDriver.get(url);
        this.fullScreenShotFile = ((TakesScreenshot) this.webDriver).getScreenshotAs(OutputType.FILE);

        if (createFullScreenFile) {
            File destiny = new File(TEMP_SCREEN_SHOT_PATH + "original-"+  System.currentTimeMillis() +"." + format);
            FileUtils.copyFile(this.fullScreenShotFile, destiny);
            return destiny.getAbsolutePath();
        }

        return "";
    }

    private String takeElementScreenShot(String format, BufferedImage buffSubImage) throws IOException {
        // Generate the file of the subImage
        File subImage = new File(this.fullScreenShotFile.getParent() + File.separator + "subImage." + format);
        File destinyFile = new File(TEMP_SCREEN_SHOT_PATH + "subImage-"+ System.currentTimeMillis() + "." + format);

        ImageIO.write(buffSubImage, format, subImage);
        FileUtils.copyFile(subImage, destinyFile);

        // return the file's path
        return destinyFile.getAbsolutePath();
    }
}
