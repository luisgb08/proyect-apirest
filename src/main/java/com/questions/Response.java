package com.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class Response {

    public static Question<Integer> getStatusCode() {
        return Question.about("Status code").answeredBy(actor -> SerenityRest.lastResponse().statusCode());
    }

    public static Question<String> getNameEmployee() {
        return Question.about("Get name employee").answeredBy(actor -> JsonPath.from(SerenityRest.lastResponse().body().asString()).get("data.employee_name").toString());
    }

    public static Question<String> getMessage() {
        return Question.about("Get message").answeredBy(actor -> JsonPath.from(SerenityRest.lastResponse().body().asString()).get("status").toString());
    }

    public static Question<String> getNameEmployeeCreate() {
        return Question.about("Get name employee").answeredBy(actor -> JsonPath.from(SerenityRest.lastResponse().body().asString()).get("data.name").toString());
    }

}
