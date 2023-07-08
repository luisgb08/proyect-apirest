package com.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;
import static com.utils.ReadParamProperties.findParam;
import static net.serenitybdd.rest.SerenityRest.given;


public class GetUploadImgs implements Task {
    private final String subId;
    private final Integer limit;

    public GetUploadImgs(String subId, Integer limit) {
        this.subId = subId;
        this.limit = limit;
    }

    @Step("{0} consume get method")
    @Override
    public <T extends Actor> void performAs(T actor) {
                given()
                .header("x-api-key",findParam("APIKEY"))
                .queryParam("limit", limit).queryParam("sub_id", subId)
                .and().when().get()
                .then().extract().body();
    }

    public static GetUploadImgs executeGetMethodWithUploadImgs(String subId, Integer limit) {
        return Tasks.instrumented(GetUploadImgs.class, subId, limit);
    }

}
