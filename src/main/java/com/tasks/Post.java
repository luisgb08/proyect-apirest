package com.tasks;

import com.models.UploadCatRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.models.CreateCatImgRequestBuilder.aCatImg;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static net.serenitybdd.rest.SerenityRest.given;
import com.google.gson.Gson;
import org.htmlunit.javascript.host.xml.FormData;

import java.io.File;

public class Post implements Task {

    private final String resourceApi;

    public Post(String resourceApi) {
        this.resourceApi = resourceApi;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        File imageFile =  new File("/Users/lfigueras/Documents/proyect-apirest/src/test/resources/14.jpg");

        String apiKey = "live_6DcftSIDs0osSIuayASrljuhCkwa8T2GxK7dofwo4yYjmrmw0DYZ5XZ4LIdi87NK";
        String imagePath = "/Users/lfigueras/Documents/proyect-apirest/src/test/resources/14.jpg";

        FormData prueba = new FormData();

        //UploadCatRequest catImg = aCatImg()
        //        .withFile("/Users/lfigueras/Documents/proyect-apirest/src/test/resources/14.jpg")
        //        .withSub_id("lf123456")
        //        .build();
//
        //String catImgJson = new  Gson().toJson(catImg);
//
//
        //given()
        //        .header("x-api-key", apiKey)
        //        .header("Content-Type","image/jpg")
        //        .body("{\"file\":\"imageFile\"}")
        //        .and().post(resourceApi)
        //        .then().extract().response();

               //listResponseBodyUpImg =
        given()
                //.header("Content-Type","image/jpg")


                //.request()
                //.header("x-api-key", apiKey)
                //.header("Content-Type","multipart/form-data")
                //.accept("*/*")
                //.contentType(ContentType.JSON)
                .multiPart("file", imageFile, "image/jpg")
                //.body(new File(imagePath))
                .when()
                //.header("Content-Type","multipart/form-data")
          //      .body(aCatImg())
                //.config(RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                //.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("image/jpg", ContentType.JSON)))
                .post(resourceApi)
                .then().extract().body();


        //String apiKey = "live_6DcftSIDs0osSIuayASrljuhCkwa8T2GxK7dofwo4yYjmrmw0DYZ5XZ4LIdi87NK";
        //String imagePath = "src/main/java/com/tasks/cat1.jpg";
        //File imageFile =  new File(imagePath);

        //UploadCatRequest catImg = aCatImg()
        //        .withFile(imageFile)
        //        .withSub_id("lf123456")
        //        .build();

        //given()
        //        .body(aCatImg())
        //        //.RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));.
        //        .config(RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
        //        .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("image/jpeg", ContentType.MULTIPART)))//.multiPart("file", new File("/Users/lfigueras/Documents/proyect-apirest/src/test/resources/cat1.jpg"))
        //        .when()
        //        .post(resourceApi);
        //        //.then().extract().body();


        //        .post(resourceApi)
        //        .then().extract().body();


        //Se llama a un método randomIdImgMethod de la clase SelectRandomIdImg para enviarle la lista con el único response de la imagen cargada
        //y seleccionar el id de la imagen cargada
        //randomIdImgMethod(listResponseBodyUpImg, 2);


        //given()
        //        .body("{\"name\":\"Luis\",\"salary\":\"123\",\"age\":\"38\"}")
        //        .and().post(resourceApi)
        //        .then().extract().response();
    }

    public static Post uploadCatWithThe(String resourceApi) {
        return Tasks.instrumented(Post.class, resourceApi);
    }

    //public static int uploadCatWithThe(String resourceApi) {
//
    //    List<Map<String, Object>> listResponseBodyUpImg = new ArrayList<>();
//
    //    Map<String, Object> mapaPrueba = new HashMap<String, Object>();
    //    mapaPrueba.put("id", "CQBJFsFKD");
//
//
    //    listResponseBodyUpImg.add(mapaPrueba);
    //    //Se llama a un método randomIdImgMethod de la clase SelectRandomIdImg para enviarle la lista con el único response de la imagen cargada
    //    //y seleccionar el id de la imagen cargada
    //    randomIdImgMethod(listResponseBodyUpImg, 2);
//
    //    for(Map<String,Object> images : listResponseBodyUpImg)
    //    {
    //        System.out.println(images.get("id"));
    //    }
//
    //    return 1;
    //}
}
