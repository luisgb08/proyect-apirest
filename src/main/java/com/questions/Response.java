package com.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import static com.tasks.PostUploadCatImg.getUploadCatImgStatusCod;

public class Response {

    public static Question<Integer> getStatusCode() {
        return Question.about("Status code").answeredBy(actor -> SerenityRest.lastResponse().statusCode());
    }

    public static Question<Integer> getUploadCatImgStatusCode() {
        return Question.about("Status code").answeredBy(actor -> Integer.valueOf(getUploadCatImgStatusCod()));
    }

    public static Question<String> getNameEmployee() {
        return Question.about("Get name employee").answeredBy(actor -> JsonPath.from(SerenityRest.lastResponse().body().asString()).get("data.employee_name").toString());
    }

    public static Question<String> getMessage() {
        return Question.about("Get message").answeredBy(actor -> JsonPath.from(SerenityRest.lastResponse().body().asString()).get("message").toString());
    }

    public static Question<String> getNameEmployeeCreate() {
        return Question.about("Get name employee").answeredBy(actor -> JsonPath.from(SerenityRest.lastResponse().body().asString()).get("data.name").toString());
    }

}
