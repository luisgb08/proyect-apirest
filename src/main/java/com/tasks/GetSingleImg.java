package com.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;


public class GetSingleImg implements Task {

    private final String resourceApi;

    public GetSingleImg(String resourceApi) {
        this.resourceApi = resourceApi;
    }


    @Step("{0} consume get method")
    @Override
    public <T extends Actor> void performAs(T actor) {
            given().
                    and().when().get(resourceApi).
                    then().extract().response();

    }

    public static GetSingleImg executeGetMethodWithThenSingleImg(String resourceApi) {
        return Tasks.instrumented(GetSingleImg.class, resourceApi);
    }
}
