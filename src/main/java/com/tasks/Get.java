package com.tasks;

import io.restassured.common.mapper.TypeRef;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

import java.util.List;
import java.util.Map;

import static com.utils.WritePropertiesRandomIdImg.writeRandomIdInFile;
import static net.serenitybdd.rest.SerenityRest.given;


public class Get implements Task {

    private final String resourceApi;

    private String randomIdImage;

    public Get(String resourceApi) {
        this.resourceApi = resourceApi;
    }

    public void setRandomIdImage(String randomIdImage) {
        this.randomIdImage = randomIdImage;
    }

    public String getRandomIdImage() {
        return randomIdImage;
    }

    @Step("{0} consume get method")
    @Override
    public <T extends Actor> void performAs(T actor) {

        List<Map<String, Object>> responseBody =  null;

        responseBody = given().
                and().when().get(resourceApi).
                then().extract().body().as(new TypeRef<List<Map<String, Object>>>() {
                });

        System.out.println("Total images : "+ responseBody.size());

        System.out.println("All images ids are: ");


        for(Map<String,Object> images : responseBody)
        {
            System.out.println(images.get("id"));
        }

        //System.out.println(responseBody.get(2).get("id"));

        randomIdImage = responseBody.get(0).get("id").toString();

        try {
            writeRandomIdInFile(randomIdImage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Código original
           // given().
           //         and().when().get(resourceApi).
           //         then().extract().response();

    }

    public static Get executeGetMethodWithThen(String resourceApi) {
        return Tasks.instrumented(Get.class, resourceApi);
    }
}
