package com.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

import static com.utils.ReadParamProperties.findParam;
import static net.serenitybdd.rest.SerenityRest.given;

public class GetSingleCatItem implements Task {

    private final String resourceApi;

    public GetSingleCatItem(String resourceApi) {
        this.resourceApi = resourceApi;
    }

    @Step("{0} consume get method")
    @Override
    public <T extends Actor> void performAs(T actor) {
            given()
                    .header("x-api-key",findParam("APIKEY"))
                    .and().when().get(resourceApi)
                    .then().extract().response();
    }

    public static GetSingleCatItem executeGetMethodForSingleCatItemWithThe(String resourceApi) {
        return Tasks.instrumented(GetSingleCatItem.class, resourceApi);
    }

}
