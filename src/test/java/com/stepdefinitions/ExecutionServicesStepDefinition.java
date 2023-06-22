package com.stepdefinitions;

import com.utils.SelectRandomIdImg;
import com.utils.TransferResponseListS1;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;


import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static com.exceptions.ErrorAssertion.THE_CODES_DO_NOT_MATCH;
import static com.factory.CreateCatImageDataFactory.IDFILEPOSITION;
import static com.factory.CreateCatImageDataFactory.IDRPOSITION;
import static com.questions.Response.*;
import static com.tasks.DeleteCatImage.deleteImage;
import static com.tasks.GetRandomImgs.executeGetMethodWithThen;
import static com.tasks.GetSingleImg.executeGetMethodWithThenSingleImg;
import static com.tasks.Post.uploadCatWithThe;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.utils.ReadParamProperties.findParam;
import static com.tasks.Post2.initTest;
import static com.tasks.Post2.interactions;

import static org.hamcrest.CoreMatchers.equalTo;

public class ExecutionServicesStepDefinition {

    public String randomImageId;

    public String example;

    public String getVariable() {
        return Serenity.sessionVariableCalled("example");
    }

    public void saveVariable(String rs) {
        Serenity.setSessionVariable("example").to(rs);
    }



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
        RestAssured.baseURI = findParam("BASE_URI");
        RestAssured.basePath = findParam("BASE_PATH");


        //brinda la info de todos los Rquests y todos los Response
       RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
       RestAssured.requestSpecification = new RequestSpecBuilder()
                //Ejecuta todos los servicios con Aplication Json
               .setContentType(ContentType.MULTIPART)

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

        //Serenity.setSessionVariable("example").to(resourceApi);

        //saveVariable(resourceApi);
        //System.out.println("xx: " + randomImageId);
    }

    @Then("See that the is returned {int}")
    public void seeThatTheIsReturned(int statusCode) {
        assertThat(THE_CODES_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getStatusCode()), equalTo(statusCode));

        //then(theActorInTheSpotlight()).should(ResponseConsequence
        //        .seeThatResponse(THE_CODES_DO_NOT_MATCH, response -> response(statusCode))
        //));


    }


    @When("Execute the method GET with the random Id from Scenario 1")
    public void executeTheMethodGet2WithTheResourceApi() {
        //ResourceBundle rb = ResourceBundle.getBundle("dataRandomImg", Locale.getDefault());
        //when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(rb.getString("id"+ID_EMPLOYEE)));
        SelectRandomIdImg objRandomIdImg = new SelectRandomIdImg();

        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(objRandomIdImg.getRandomIdImg()));
        //example = Serenity.sessionVariableCalled("example");
        //System.out.println("Paso var: "+ example);
    }

    //Para consultar todas las images por Id del escenario 1. Una por una, se repite la ejecución del escenario
    @When("Execute the method GET with the Id {int} from scenario 1")
    public void executeTheMethodGETWithTheId(Integer numId) {
        TransferResponseListS1 objSelectIdImg = new TransferResponseListS1();

        List<Map<String, Object>> idList = objSelectIdImg.getResponseList();
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(idList.get(numId).get("id").toString()));
    }


    @When("Execute the method GET with the random Id from file")
    public void executeTheMethodGet3WithTheResourceApi() {
        ResourceBundle rb = ResourceBundle.getBundle("dataRandomImg1", Locale.getDefault());
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(rb.getString("id"+ IDFILEPOSITION)));
    }


    @When("Execute the method GET with the Id {int} from file")
    public void executeTheMethodGET4WithTheId(Integer numId) {
        ResourceBundle rb = ResourceBundle.getBundle("dataRandomImg1", Locale.getDefault());
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(rb.getString("id"+numId)));
    }

    @When("Execute the method POST with the resource api {string}")
    public void executeTheMethodPOSTWithTheResourceApi(String resourceApi) {
        //when(theActorInTheSpotlight()).wasAbleTo(uploadCatWithThe(resourceApi));

        initTest();
        interactions("/Users/lfigueras/Documents/proyect-apirest/src/test/resources/AngryCat.png","gabino22");

        //uploadCatWithThe(resourceApi);

    }


    @When("Execute the method GET with upload Id")
    public void executeTheMethodGet5WithTheResourceApi() {
        SelectRandomIdImg objRandomIdImg = new SelectRandomIdImg();
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(objRandomIdImg.getRandomIdImg()));

    }


    @When("Execute the method GET with with the resource api {string}")
    public void executeTheMethodGet6WithTheResourceApi(String resourceApi) {
        SelectRandomIdImg objRandomIdImg = new SelectRandomIdImg();
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(objRandomIdImg.getRandomIdImg()+"/"+resourceApi));

    }


    @When("Execute the method DELETE with the random Id")
    public void executeTheMethodDeleteWithTheResourceApi() {
        SelectRandomIdImg objRandomIdImg = new SelectRandomIdImg();
        when(theActorInTheSpotlight()).wasAbleTo(deleteImage(objRandomIdImg.getRandomIdImg()));
    }
}


