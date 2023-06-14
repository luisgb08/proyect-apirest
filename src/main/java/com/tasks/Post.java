package com.tasks;

import com.models.UploadCatRequest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.factory.CreateEmployeeDataFactory.ID_EMPLOYEE;
import static com.models.CreateCatImgRequestBuilder.aCatImg;
import static com.utils.SelectRandomIdImg.randomIdImgMethod;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static net.serenitybdd.rest.SerenityRest.given;
import com.google.gson.Gson;
import org.htmlunit.javascript.host.xml.FormData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Post implements Task {

    private final String resourceApi;

    public Post(String resourceApi) {
        this.resourceApi = resourceApi;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        File imageFile =  new File("Users/lfigueras/Documents/Gato2.jpg");

        String apiKey = "live_6DcftSIDs0osSIuayASrljuhCkwa8T2GxK7dofwo4yYjmrmw0DYZ5XZ4LIdi87NK";
        String imagePath = "Usuarios/lfigueras/Documentos/Gato2.jpg";

        String requestBody = "{\n" +
                " \"sub_id\": " +
                "\"lf123456\" "+
                " \n" +
                "}";
        //String employeeJson = new  Gson().toJson(employee);

        //listResponseBodyUpImg = given()
                //.header("Content-Type","image/jpg")


                //.request()
        //        .header("x-api-key", apiKey)
                //.header("Content-Type","multipart/form-data")
                //.contentType(ContentType.MULTIPART)
        //        .multiPart("file", new File(imagePath), "multipart/form-data")
                //.body(new File(imagePath))
        //        .when()//.header("Content-Type","image/jpg")
                //.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("image/jpeg", ContentType.TEXT)))
        //        .body(requestBody)
               // .body(employeeJson)


                //.contentType("multipart/form-data")

        //        .post(resourceApi)
        //        .then().extract().body().as(new TypeRef<List<Map<String, Object>>>() {
        //        });


        //Se llama a un método randomIdImgMethod de la clase SelectRandomIdImg para enviarle la lista con el único response de la imagen cargada
        //y seleccionar el id de la imagen cargada
        //randomIdImgMethod(listResponseBodyUpImg, 2);


        //given()
        //        .body("{\"name\":\"Luis\",\"salary\":\"123\",\"age\":\"38\"}")
        //        .and().post(resourceApi)
        //        .then().extract().response();
    }

    //public static Post uploadCatWithThe(String resourceApi) {
    //    return Tasks.instrumented(Post.class, resourceApi);
    //}

    public static int uploadCatWithThe(String resourceApi) {

        List<Map<String, Object>> listResponseBodyUpImg = new ArrayList<>();

        Map<String, Object> mapaPrueba = new HashMap<String, Object>();
        mapaPrueba.put("id", "CQBJFsFKD");


        listResponseBodyUpImg.add(mapaPrueba);
        //Se llama a un método randomIdImgMethod de la clase SelectRandomIdImg para enviarle la lista con el único response de la imagen cargada
        //y seleccionar el id de la imagen cargada
        randomIdImgMethod(listResponseBodyUpImg, 2);

        for(Map<String,Object> images : listResponseBodyUpImg)
        {
            System.out.println(images.get("id"));
        }

        return 1;
    }
}
