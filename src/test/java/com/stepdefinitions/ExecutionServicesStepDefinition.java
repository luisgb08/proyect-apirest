package com.stepdefinitions;

import static com.drivers.DriverChrome.navigateTo;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.abilities.BrowsingTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import java.io.IOException;
import static com.exceptions.ErrorAssertion.THE_CODES_DO_NOT_MATCH;
import static com.factory.CreateCatImageDataFactory.IDFILEPOSITION;
import static com.questions.Response.*;
import static com.tasks.DeleteCatItem.deleteCatItemWithThe;
import static com.tasks.GetCatImgList.executeGetMethodWithThe;
import static com.tasks.GetSingleCatItem.executeGetMethodForSingleCatItemWithThe;
import static com.tasks.GetUploadImgs.executeGetMethodForUploadImgWithTheElements;
import static com.tasks.PostFavCatImg.createFavCatWithThe;
import static com.tasks.UploadCatImgDataForm.uploadCatImgWithTheElements;
import static com.utils.Constants.SUCCESS_MSG;
import static com.utils.MakeParamsPathS1.paramPathS1;
import static com.utils.ReadParamProperties.findIdImgFile;
import static com.utils.SaveFavCatIdImg.getCatFavIdImg;
import static com.utils.SelectSaveRandomIdImg.getRandomIdImg;
import static com.utils.TransferResponseListS1.getResponseList;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.utils.ReadParamProperties.findParam;
import static org.hamcrest.CoreMatchers.equalTo;

public class ExecutionServicesStepDefinition {

    @Before
    public static void actor() throws IOException {
        OnStage.setTheStage(new Cast());
        theActorCalled("Luis");
    }

    @Before
    public static void setUpRest() {
        RestAssured.baseURI = findParam("BASE_URI");
        RestAssured.basePath = findParam("BASE_PATH");

       //brinda la info de todos los Rquests y todos los Response
       RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
       RestAssured.requestSpecification = new RequestSpecBuilder()
                //Ejecuta todos los servicios con Aplication Json
                .setContentType(ContentType.JSON)

                //omitiendo esta validación de HTTP segurop o HTTPS
                .setRelaxedHTTPSValidation()
                .build();
    }

    //Conexión a la Api
    @Given("I make the connection to the api")
    public void iMakeTheConnectionToTheApi() {
        givenThat(theActorCalled("Luis").whoCan(CallAnApi.at("/")));
    }

    //Ejecución de Get con consulta de varias imagenes según parámetros indicados
    @When("Execute the method GET with the resource api {string}")
    public void executeTheMethodGETWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThe(resourceApi+paramPathS1()));
    }

    @Then("See that the code returned is {int}")
    public void seeThatTheCodeReturnedIs(int statusCode) {
        assertThat(THE_CODES_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getStatusCode()), equalTo(statusCode));
    }

    //Se consulta una imagen según un id aleatorio del escenario 1
    @When("Execute the method GET with random Id from Scenario1 with the resource api {string}")
    public void executeTheMethodGetWithRandomIdFromScenario1WithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSingleCatItemWithThe(resourceApi+"/"+getRandomIdImg()));
    }

    //Para consultar todas las images por Id del escenario 1. Una por una, se repite la ejecución del escenario
    @When("Execute the method GET with the Id {int} from scenario1 with the resource api {string}")
    public void executeTheMethodGETWithTheIdFromScenario1WithTheResourceApi(Integer numId, String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSingleCatItemWithThe(resourceApi+"/"+getResponseList().get(numId).get("id").toString()));
    }

    //Ejecuta Get de consulta de una imagen  seleccionando un id random del archivo
    @When("Execute the method GET with the random Id from file with the resource api {string}")
    public void executeTheMethodGetWithTheRandomIdFromFileWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSingleCatItemWithThe(resourceApi+"/"+findIdImgFile("id"+ IDFILEPOSITION)));
    }

    //Ejecuta get para consulta de imagen de acuerdo a la posición a la posición de resgistro en el archivo
    //Se quiere consultar todas las imagenes dadas las posiciones indicadas en el feature
    @When("Execute the method GET with the Id {int} from file with the resource api {string}")
    public void executeTheMethodGETWithTheIdFromFileWithTheResourceApi(Integer numId, String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSingleCatItemWithThe(resourceApi+"/"+findIdImgFile("id"+numId)));
    }

    //Dado que el actor se le da la habiliadad de interactuar con el navegador
    @Given("I open html form data passing a image")
    public void iOpenHtmlFormDataPassingAImage() {
        givenThat(theActorCalled("Luis")).whoCan(BrowsingTheWeb.with(navigateTo(findParam("PATH_HTML_POST_PAGE"))));
    }

    //Cargar imagen con método Post
    @When("Execute the method POST with the resource api {string}")
    public void executeTheMethodPOSTWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(uploadCatImgWithTheElements(findParam("PATH_IMG"),findParam("CAT_IMG_OK"),findParam("UPLOAD_SUB_ID"), resourceApi));
    }

    @When("Execute the method POST with the resource api {string} for no cat image")
    public void executeTheMethodPOSTWithTheResourceApiForNoCatImage(String resourceApi) {
        when(theActorInTheSpotlight()).attemptsTo(uploadCatImgWithTheElements(findParam("PATH_IMG"),findParam("NO_CAT_IMG"),findParam("UPLOAD_SUB_ID"), resourceApi));
    }

    //Se ejecuta consulta de la imagen cargada con el id obtenido en el escenario 6 del Post ejecutado
    @When("Execute the method GET with upload Id with the resource api {string}")
    public void executeTheMethodGetWithTheUploadIdImage(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSingleCatItemWithThe(resourceApi+"/"+getRandomIdImg()));
    }

    //Se obtiene análisis de la imágen cargada en el escenario 6
    @When("Execute the method GET for analysys with the resource api {string}")
    public void executeTheMethodGet6WithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSingleCatItemWithThe(resourceApi+"/"+getRandomIdImg()+"/"+"analysis"));
    }

    //Se elimina la imagen cargada
    @When("Execute the method DELETE with the random Id with the resource api {string}")
    public void executeTheMethodDeleteWithTheRandomIdWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(deleteCatItemWithThe(resourceApi+"/"+getRandomIdImg()));
    }

    @When("Execute the method GET with params subid {string} for limit {int} with the resource api {string}")
    public void executeTheMethodGETWithParamsSubidLimitForResApi(String subid, Integer limit, String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForUploadImgWithTheElements(resourceApi, subid, limit));
    }

    @Then("See that the status code is {int}")
    public void seeThatTheStatusCodeIs (int statusCode) {
        assertThat(THE_CODES_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getUploadCatImgStatusCode()), equalTo(statusCode));
    }

    @When("Execute the method POST to create fav with the resource api {string}")
    public void executeTheMethodPOSTToCreateFavWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(createFavCatWithThe(resourceApi));
    }

    @Then("Check if the cat favourite was create successfuly")
    public void checkIfTheCatFavouriteWasCreateSuccessfuly() {
        assertThat("The message do not match",
                theActorInTheSpotlight().asksFor(getMessage()), equalTo(SUCCESS_MSG));
    }

    @When("Execute the method GET with fav Id with the resource api {string}")
    public void executeTheMethodGetWithTheFavIdWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSingleCatItemWithThe(resourceApi+"/"+getCatFavIdImg()));
    }

    //Se elimina el favorito de la imagen creado dado el Id del favorito
    @When("Execute the method DELETE with fav Id with the resource api {string}")
    public void executeTheMethodDeleteWithTheFavIdWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(deleteCatItemWithThe(resourceApi+"/"+getCatFavIdImg()));
    }

    @Then("Check if the cat favourite was deleted successfuly")
    public void checkIfTheCatFavouriteWasDeletedSuccessfuly() {
        assertThat("The meesage do not match",
                theActorInTheSpotlight().asksFor(getMessage()), equalTo(SUCCESS_MSG));
    }

}


