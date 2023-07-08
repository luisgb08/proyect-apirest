package com.userinterfaces;

import com.drivers.DriverChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Cats {

    DriverChrome objDriverChrome = new DriverChrome();

    public WebElement URLPOSTREQ = objDriverChrome.getDriver().findElement(By.id("urlreq"));

    public WebElement APIKEY = objDriverChrome.getDriver().findElement(By.id("apikey"));

    public WebElement INPUT_FILE = objDriverChrome.getDriver().findElement(By.id("file"));

    public WebElement INPUT_SUBID = objDriverChrome.getDriver().findElement(By.id("sub_id"));

    public WebElement UPLOAD_BUTTON = objDriverChrome.getDriver().findElement(By.id("uploadBtn"));

    public WebElement RESPONSE = objDriverChrome.getDriver().findElement(By.id("response"));

    public WebElement STATUS_CODE = objDriverChrome.getDriver().findElement(By.id("statuscode"));

}
