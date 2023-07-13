package com.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import static com.tasks.PostUploadCatImg.getResponseUploadImg;
import static com.tasks.PostUploadCatImg.getUploadCatImgStatusCod;

public class Response {

    public static Question<Integer> getStatusCode() {
        return Question.about("Status code").answeredBy(actor -> SerenityRest.lastResponse().statusCode());
    }

    public static Question<Integer> getUploadCatImgStatusCode() {
        return Question.about("Status code").answeredBy(actor -> Integer.valueOf(getUploadCatImgStatusCod()));
    }

    public static Question<String> getMessage() {
        return Question.about("Get message").answeredBy(actor -> JsonPath.from(SerenityRest.lastResponse().body().asString()).get("message").toString());
    }

    public static Question<String> getMessageUnhappyPath() {
        return Question.about("Get message of Unhappypath Scenario").answeredBy(actor -> (SerenityRest.lastResponse().getBody().asString()));
    }

    public static Question<String> getResponseUploadImage() {
        return Question.about("Status code").answeredBy(actor -> getResponseUploadImg());
    }

}
