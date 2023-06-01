package com.tasks;

import com.models.CreateEmployeeRequest;
import com.models.CreateEmployeeRequestBuilder;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.factory.CreateEmployeeDataFactory.*;
import static com.models.CreateEmployeeRequestBuilder.aEmployee;
import static net.serenitybdd.rest.SerenityRest.given;
import com.google.gson.Gson;

public class Post implements Task {

    private final String resourceApi;

    public Post(String resourceApi) {
        this.resourceApi = resourceApi;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        CreateEmployeeRequest employee = aEmployee()
                .withName(NAME_EMPLOYEE)
                .withSalary(SALARY_EMPLOYEE)
                .withAge(AGE_EMPLOYEE)
                .build();

        String employeeJson = new Gson().toJson(employee);
        given()
                .body(employeeJson)
                .and().post(resourceApi)
                .then().extract().response();

        //given()
        //        .body("{\"name\":\"Luis\",\"salary\":\"123\",\"age\":\"38\"}")
        //        .and().post(resourceApi)
        //        .then().extract().response();
    }

    public static Post createEmployeeWithThe(String resourceApi) {
        return Tasks.instrumented(Post.class, resourceApi);
    }
}
