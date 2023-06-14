package com.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.factory.CreateEmployeeDataFactory.ID_EMPLOYEE;
import static net.serenitybdd.rest.SerenityRest.given;

public class Delete implements Task {

    private final String resourceApi;

    public Delete(String resorceApi) {

        this.resourceApi = resorceApi;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        given()
                .header("x-api-key","live_6DcftSIDs0osSIuayASrljuhCkwa8T2GxK7dofwo4yYjmrmw0DYZ5XZ4LIdi87NK")
                .and().when().delete(resourceApi)
                .then().extract().response();
    }

    public static Delete deleteImage(String resourseApi) {

        return Tasks.instrumented(Delete.class, resourseApi);
    }
}
