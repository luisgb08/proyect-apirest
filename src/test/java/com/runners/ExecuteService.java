package com.runners;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = { "pretty", "html: target/cucumber" },
        glue = "com.stepdefinitions",
        tags = "@services",
                //"@Get10Imagesx or @GetImageIdScenario1x or @GetAllImagesIdScenario1x or @GetRandomImageIdFilex or @GetAllImagesIdFilex",
               // "@UploadImage or @UploadNoCatImage",
        snippets = SnippetType.CAMELCASE,
        monochrome = true
)

public class ExecuteService {

}
