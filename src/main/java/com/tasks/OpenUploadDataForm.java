package com.tasks;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tasks.Post2.openDataForm;
import static com.utils.SelectRandomIdImg.randomIdImgMethod;
import static com.utils.ReadParamProperties.findParam;

import java.time.Duration;

import static com.utils.ReadParamProperties.findParam;


public class PostUploadImg implements Task {

    private final String urlPage;

    public PostUploadImg(String urlPage) {
        this.urlPage = urlPage;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        openDataForm(urlPage);
    }


    public static PostUploadImg uploadImgCat(String urlPage) {
        return Tasks.instrumented(PostUploadImg.class, urlPage);
    }
}
