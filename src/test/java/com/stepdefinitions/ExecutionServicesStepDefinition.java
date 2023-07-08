package com.stepdefinitions;

import static com.drivers.DriverChrome.navigateTo;
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
import net.serenitybdd.screenplay.abilities.BrowsingTheWeb;
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
import static com.questions.Response.*;
import static com.tasks.DeleteCatImage.deleteImage;
import static com.tasks.GetRandomImgs.executeGetMethodWithThen;
import static com.tasks.GetSingleImg.executeGetMethodWithThenSingleImg;
import static com.tasks.GetUploadImgs.executeGetMethodWithUploadImgs;
import static com.tasks.UploadCatImgDataForm.uploadCatImgWithThe;
import static com.utils.SelectRandomIdImg.getRandomIdImg;
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

    //Se consulta una imagen según un id aleatorio del escenario 1
    @When("Execute the method GET with the random Id from Scenario 1")
    public void executeTheMethodGet2WithTheResourceApi() {
        //ResourceBundle rb = ResourceBundle.getBundle("dataRandomImg", Locale.getDefault());
        //when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(rb.getString("id"+ID_EMPLOYEE)));
        //SelectRandomIdImg objRandomIdImg = new SelectRandomIdImg();

        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(getRandomIdImg()));
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

    //Ejecuta Get de consulta de una imagen  seleccionando un id random del archivo
    @When("Execute the method GET with the random Id from file")
    public void executeTheMethodGet3WithTheResourceApi() {
        ResourceBundle rb = ResourceBundle.getBundle(findParam("NAME_FID_IMG"), Locale.getDefault());
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(rb.getString("id"+ IDFILEPOSITION)));
    }

    //Ejecuta get para consulta de imagen de acuerdo a la posición a la posición de resgistro en el archivo
    //Se quieren consultar todas las imagenes dadas las posiciones indicadas en el feature
    @When("Execute the method GET with the Id {int} from file")
    public void executeTheMethodGET4WithTheId(Integer numId) {
        ResourceBundle rb = ResourceBundle.getBundle(findParam("NAME_FID_IMG"), Locale.getDefault());
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(rb.getString("id"+numId)));
    }


    //Dado que el actor se le da la habiliadad de interactuar con el navegador
    @Given("I open html form data passing a image")
    public void iOpenHtmlFormDataPassingAImage() {
        givenThat(theActorCalled("Luis")).whoCan(BrowsingTheWeb.with(navigateTo(findParam("PATH_HTML_POST_PAGE"))));
    }

    //Cargar imagen con método Post
    @When("Execute the method POST with the resource api {string}")
    public void executeTheMethodPOSTWithTheResourceApi(String resourceApi) {
        when(theActorInTheSpotlight()).wasAbleTo(uploadCatImgWithThe(findParam("PATH_IMG"),findParam("CAT_IMG_OK"),findParam("UPLOAD_SUB_ID"), resourceApi));
    }

    @When("Execute the method POST with the resource api {string} for no cat image")
    public void executeTheMethodPOSTWithTheResourceApiForNoCatImage(String resourceApi) {
        when(theActorInTheSpotlight()).attemptsTo(uploadCatImgWithThe(findParam("PATH_IMG"),findParam("NO_CAT_IMG"),findParam("UPLOAD_SUB_ID"), resourceApi));
    }

    //Se ejecuta consulta de la imagen cargada con el id obtenido en el escenario 6 del Post ejecutado
    @When("Execute the method GET with upload Id")
    public void executeTheMethodGet5WithTheResourceApi() {
        //SelectRandomIdImg objRandomIdImg = new SelectRandomIdImg();
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(getRandomIdImg()));

    }

    //Se obtiene análisis de la imágen cargada en el escenario 6
    @When("Execute the method GET with with the resource api {string}")
    public void executeTheMethodGet6WithTheResourceApi(String resourceApi) {
        //SelectRandomIdImg objRandomIdImg = new SelectRandomIdImg();
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithThenSingleImg(getRandomIdImg()+"/"+resourceApi));

    }


    //Se elimina la imagen cargada
    @When("Execute the method DELETE with the random Id")
    public void executeTheMethodDeleteWithTheResourceApi() {
        //SelectRandomIdImg objRandomIdImg = new SelectRandomIdImg();
        when(theActorInTheSpotlight()).wasAbleTo(deleteImage(getRandomIdImg()));
    }

    @When("Execute the method GET with params subid {string} for limit {int}")
    public void executeTheMethodGETWithParamsSubidLimitForResApi(String subid, Integer limit) {
        when(theActorInTheSpotlight()).wasAbleTo(executeGetMethodWithUploadImgs(subid, limit));

    }


    @Then("See that the status code is {int}")
    public void seeThatTheStatusCodeIs (int statusCode) {
        assertThat(THE_CODES_DO_NOT_MATCH,
                theActorInTheSpotlight().asksFor(getUploadCatImgStatusCode()), equalTo(statusCode));
    }

}


