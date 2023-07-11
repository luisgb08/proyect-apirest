package com.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import static com.utils.ReadParamProperties.findParam;
import static net.serenitybdd.rest.SerenityRest.given;

public class DeleteCatItem implements Task {

    private final String resourceApi;

    public DeleteCatItem(String resourceApi) {
        this.resourceApi = resourceApi;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        given()
                .header("x-api-key",findParam("APIKEY"))
                .and().when().delete(resourceApi)
                .then().extract().response();
    }

    public static DeleteCatItem deleteCatItemWithThe(String resourceApi) {
        return Tasks.instrumented(DeleteCatItem.class, resourceApi);
    }

}
