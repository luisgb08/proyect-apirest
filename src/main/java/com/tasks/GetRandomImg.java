package com.tasks;

import io.restassured.common.mapper.TypeRef;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

import java.util.List;
import java.util.Map;

import static com.utils.TransferResponseListS1.assignIdImgList;
import static com.utils.WritePropertiesRandomIdImg.writeRandomIdInFile;
import static com.utils.SelectRandomIdImg.randomIdImgMethod;
import static net.serenitybdd.rest.SerenityRest.given;


public class GetRandomImg implements Task {

    private final String resourceApi;

    public GetRandomImg(String resourceApi) {
        this.resourceApi = resourceApi;
    }



    @Step("{0} consume get method")
    @Override
    public <T extends Actor> void performAs(T actor) {

        List<Map<String, Object>> listResponseBodyRImg =  null;

        listResponseBodyRImg = given().
                and().when().get(resourceApi).
                then().extract().body().as(new TypeRef<List<Map<String, Object>>>() {
                });

        //System.out.println("Total images : "+ listResponseBodyRImg.size());

        System.out.println("All images ids are: ");


        for(Map<String,Object> images : listResponseBodyRImg)
        {
            System.out.println(images.get("id"));
        }



        //System.out.println(listResponseBodyRImg.get(2).get("id"));

        //randomIdImage = listResponseBodyRImg.get(0).get("id").toString();

        //Se escribe archivo con id recibidos del response del escenario 1
        try {
            writeRandomIdInFile(listResponseBodyRImg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Se llama a un método randomIdImgMethod de la clase SelectRandomIdImg para enviarle la lista con el response del primer escenario
        //y seleccionar un id de imagen aleatorio
        randomIdImgMethod(listResponseBodyRImg, 1);


        //Se envía la lista a otra clase para tenerla disponible para su consulta para el escenario 3
        assignIdImgList(listResponseBodyRImg);


    }

    public static GetRandomImg executeGetMethodWithThen(String resourceApi) {
        return Tasks.instrumented(GetRandomImg.class, resourceApi);
    }


}
