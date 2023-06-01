package com.stepdefinitions;

import com.models.CreateEmployeeRequest;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.exceptions.ErrorAssertion.THE_CODES_DO_NOT_MATCH;
import static com.factory.CreateEmployeeDataFactory.NAME_EMPLOYEE;
import static com.questions.Response.*;
import static com.tasks.Delete.deleteEmployee;
import static com.tasks.Get.executeGetMethodWithThen;
import static com.tasks.GetSingleImg.executeGetMethodWithThenSingleImg;
import static com.tasks.Post.createEmployeeWithThe;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.equalTo;

public class ExecutionServicesStepDefinition {

    public String randomImageId;

    @Before
    public static void actor() throws IOException {
        OnStage.setTheStage(new Cast());
        theActorCalled("Luis");

        //ResourceBundle rb = ResourceBundle.getBundle("config", Locale.getDefault());

        //System.out.println("Elemento 1: " + rb.getString("hello"));
        //System.out.println("Elemento 2: " + rb.getString("world"));

    }


    @Before
    public static void setUpRest() {
        RestAssured.baseURI = "https://api.thecatapi.com";
        RestAssured.basePath = "/v1/images";

        //brinda la info de todos los Rquests y todos los Response
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                //Ejecuta todos los servicios con Aplication Json
                .setContentType(ContentType.JSON)

                //omitiendo esta validación de HTTP segurop o HTTPS
                .setRelaxedHTTPSValidation()
                .build();
    }


    @Given("I make the connection to the api")
    public void iMakeTheConnectionToTheApi() {
        givenThat(theActorCalled("Luis").whoCan(CallAnApi.at("/")));
    }

    @When("Execute the method GET with the resource api {string}")
    public void executeTheMethodGETWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThen(resourceApi));

    }

    @Then("See that the is returned {int}")
    public void seeThatTheIsReturned(int statusCode) {
        assertThat(THE_CODES_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getStatusCode()), equalTo(statusCode));

        //then(theActorInTheSpotlight()).should(ResponseConsequence
        //        .seeThatResponse(THE_CODES_DO_NOT_MATCH, response -> response(statusCode))
        //));


    }

    @When("Execute the method GET with the random Id")
    public void executeTheMethodGet2WithTheResourceApi() {
        ResourceBundle rb = ResourceBundle.getBundle("dataRandomImg", Locale.getDefault());
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(rb.getString("id")));
    }

}