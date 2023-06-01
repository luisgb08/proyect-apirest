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
        given().
                and().when().delete(resourceApi+"/"+ID_EMPLOYEE)
                .then().extract().response();
    }

    public static Delete deleteEmployee(String resourseApi) {
        return Tasks.instrumented(Delete.class, resourseApi);
    }
}
