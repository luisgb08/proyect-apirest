package com.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverChrome {
    static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public static WebDriver navigateTo(String url) {

        ChromeOptions options = new ChromeOptions();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get(url);
        return driver;
    }

    public static void closeDriver() {
        driver.close();
    }

}
