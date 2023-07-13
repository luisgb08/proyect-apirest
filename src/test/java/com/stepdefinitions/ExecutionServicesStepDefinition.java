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

import static com.exceptions.ErrorAssertion.*;
import static com.factory.CreateCatImageDataFactory.IDFILEPOSITION;
import static com.questions.Response.*;
import static com.tasks.DeleteCatItem.deleteCatItemWithThe;
import static com.tasks.GetCatImgList.executeGetMethodWithThe;
import static com.tasks.GetSimpleCatItem.executeGetMethodForSimpleCatItemWithThe;
import static com.tasks.GetUploadImgs.executeGetMethodForUploadImgWithTheElements;
import static com.tasks.PostFavCatImg.createFavCatWithTheElements;
import static com.tasks.UploadCatImgDataForm.uploadCatImgWithTheElements;
import static com.utils.Constants.SUCCESS_MSG;
import static com.utils.MakeParamsPathS1.paramPathS1;
import static com.utils.ReadParamProperties.findIdImgFile;
import static com.utils.SaveFavCatIdImg.getCatFavIdImg;
import static com.utils.SelectMsgUploadImgUP.MsgUploadImgUP;
import static com.utils.SelectSaveIdImg.getIdImg;
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

    //Valida el código de estado devuelto vs el código de estatus esperado que se pasa como parámetro desde el escenario en el feature
    @Then("See that the code returned is {int}")
    public void seeThatTheCodeReturnedIs(int statusCode) {
        assertThat(THE_CODES_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getStatusCode()), equalTo(statusCode));
    }

    //Se consulta una imagen según un id aleatorio del escenario 1
    @When("Execute the method GET with random Id from Scenario1 with the resource api {string}")
    public void executeTheMethodGetWithRandomIdFromScenario1WithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSimpleCatItemWithThe(resourceApi+"/"+ getIdImg()));
    }

    //Para consultar todas las images por Id del escenario 1. Una por una, se repite la ejecución del escenario
    @When("Execute the method GET with the Id {int} from scenario1 with the resource api {string}")
    public void executeTheMethodGETWithTheIdFromScenario1WithTheResourceApi(Integer numId, String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSimpleCatItemWithThe(resourceApi+"/"+getResponseList().get(numId).get("id").toString()));
    }

    //Ejecuta Get de consulta de una imagen  seleccionando un id random del archivo
    @When("Execute the method GET with the random Id from file with the resource api {string}")
    public void executeTheMethodGetWithTheRandomIdFromFileWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSimpleCatItemWithThe(resourceApi+"/"+findIdImgFile("id"+ IDFILEPOSITION)));
    }

    //Ejecuta get para consulta de imagen de acuerdo a la posición a la posición de resgistro en el archivo
    //Se quiere consultar todas las imagenes dadas las posiciones indicadas en el feature
    @When("Execute the method GET with the Id {int} from file with the resource api {string}")
    public void executeTheMethodGETWithTheIdFromFileWithTheResourceApi(Integer numId, String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSimpleCatItemWithThe(resourceApi+"/"+findIdImgFile("id"+numId)));
    }

    //Dado que el actor se le da la habiliadad de interactuar con el navegador
    @Given("I open html form data passing a image")
    public void iOpenHtmlFormDataPassingAImage() {
        givenThat(theActorCalled("Luis")).whoCan(BrowsingTheWeb.with(navigateTo(findParam("PATH_HTML_POST_PAGE"))));
    }

    //Cargar imagen de gato con método Post
    @When("Execute the method POST with the resource api {string}")
    public void executeTheMethodPOSTWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(uploadCatImgWithTheElements(findParam("PATH_IMG"),findParam("CAT_IMG_OK"),findParam("UPLOAD_SUB_ID"), resourceApi));
    }

    //Se intenta cargar imagen sin gato con método Post
    @When("Execute the method POST with the resource api {string} for no cat image")
    public void executeTheMethodPOSTWithTheResourceApiForNoCatImage(String resourceApi) {
        when(theActorInTheSpotlight()).attemptsTo(uploadCatImgWithTheElements(findParam("PATH_IMG"),findParam("NO_CAT_IMG"),findParam("UPLOAD_SUB_ID"), resourceApi));
    }

    //Se ejecuta consulta de la imagen cargada con el id obtenido en el escenario 6 del Post ejecutado
    @When("Execute the method GET with upload Id with the resource api {string}")
    public void executeTheMethodGetWithTheUploadIdImage(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSimpleCatItemWithThe(resourceApi+"/"+ getIdImg()));
    }

    //Se obtiene análisis de la imágen cargada en el escenario 6
    @When("Execute the method GET for analysys with the resource api {string}")
    public void executeTheMethodGet6WithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSimpleCatItemWithThe(resourceApi+"/"+ getIdImg()+"/"+"analysis"));
    }

    //Se elimina la imagen cargada
    @When("Execute the method DELETE with the random Id with the resource api {string}")
    public void executeTheMethodDeleteWithTheRandomIdWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(deleteCatItemWithThe(resourceApi+"/"+ getIdImg()));
    }

    //Se consultan todas las imagenes cargadas dado los parámetros de sub_id y limit
    @When("Execute the method GET with params subid {string} for limit {int} with the resource api {string}")
    public void executeTheMethodGETWithParamsSubidLimitForResApi(String subid, Integer limit, String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForUploadImgWithTheElements(resourceApi, subid, limit));
    }

    //Valida el código recibido en el formulario de carga de la imágen de gato vs el esperado indicado en el escenario del feature
    @Then("See that the status code is {int}")
    public void seeThatTheStatusCodeIs (int statusCode) {
        assertThat(THE_CODES_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getUploadCatImgStatusCode()), equalTo(statusCode));
    }

    //Se crea favorito para la imágen cargada
    @When("Execute the method POST to create fav with the resource api {string}")
    public void executeTheMethodPOSTToCreateFavWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(createFavCatWithTheElements(resourceApi, getIdImg(), findParam("UPLOAD_SUB_ID")));
    }

    //Valida el mensaje recibido después de creado el favorito
    @Then("Check if the cat favourite was create successfuly")
    public void checkIfTheCatFavouriteWasCreateSuccessfuly() {
        assertThat(THE_MESSAGE_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getMessage()), equalTo(SUCCESS_MSG));
    }

    //Se consulta el favorito creado
    @When("Execute the method GET with fav Id with the resource api {string}")
    public void executeTheMethodGetWithTheFavIdWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSimpleCatItemWithThe(resourceApi+"/"+getCatFavIdImg()));
    }

    //Se elimina el favorito de la imagen creado dado el Id del favorito
    @When("Execute the method DELETE with fav Id with the resource api {string}")
    public void executeTheMethodDeleteWithTheFavIdWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(deleteCatItemWithThe(resourceApi+"/"+getCatFavIdImg()));
    }

    //Se valida mensaje de eliminación de favorito
    @Then("Check if the cat favourite was deleted successfuly")
    public void checkIfTheCatFavouriteWasDeletedSuccessfuly() {
        assertThat(THE_MESSAGE_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getMessage()), equalTo(SUCCESS_MSG));
    }

    //Consulta favoritos dado un sub_id
    @When("Execute the method GET by Sub_id with the resource api {string}")
    public void executeTheMethodGetBySub_idWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodForSimpleCatItemWithThe(resourceApi));
    }

    //Valida el código recibido en el formulario de carga de la imágen sin gato vs el esperado indicado en el escenario del feature
    @Then("Check if the received message is correct")
    public void checkIfTheReceivedMessageIsCorrect() {
        assertThat(THE_MESSAGE_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getMessageUnhappyPath()), equalTo(INVALID_DATA));
    }

    //Validar si el mensaje cuando no se carga una imagen de gato es correcto
    //Se valida mensaje dependiendo de la resource api upload o upload2
    @Then("Check if the message in the body response is correct with the message {int}")
    public void checkIfTheMessageInTheBodyResponseIsCorrectWithTheMessage(int typeMsg) {
            assertThat(THE_MESSAGE_DO_NOT_MATCH,
                    theActorInTheSpotlight().asksFor(getResponseUploadImage()), equalTo(MsgUploadImgUP(typeMsg)));
    }

}


