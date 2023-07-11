package com.tasks;

import io.restassured.common.mapper.TypeRef;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;
import java.util.List;
import java.util.Map;
import static com.utils.ReadParamProperties.findParam;
import static com.utils.TransferResponseListS1.assignIdImgList;
import static com.utils.WritePropertiesRandomIdImg.writeRandomIdInFile;
import static com.utils.SelectSaveIdImg.assignRandomUpIdImg;
import static net.serenitybdd.rest.SerenityRest.given;

public class GetCatImgList implements Task {

    private final String resourceApi;

    public GetCatImgList(String resourceApi) {
        this.resourceApi = resourceApi;
    }

    @Step("{0} consume get method")
    @Override
    public <T extends Actor> void performAs(T actor) {

        List<Map<String, Object>> listResponseBodyRImg = null;

        listResponseBodyRImg =
        given()
                .header("x-api-key",findParam("APIKEY"))
                .and().when().get(resourceApi)
                .then().extract().body().as(new TypeRef<List<Map<String, Object>>>() {
                });


        System.out.println("All images ids are: ");
        for(Map<String,Object> images : listResponseBodyRImg)
        {
            System.out.println(images.get("id"));
        }


        //Se escribe archivo con ids recibidos del response del escenario 1
        try {
            writeRandomIdInFile(listResponseBodyRImg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Se llama al método assignRandomUpIdImg de la clase SelectSaveIdImg para enviarle la lista con el response del primer escenario
        //y seleccionar un id de imagen aleatorio
        assignRandomUpIdImg(listResponseBodyRImg, 1);

        //Se envía la lista a otra clase para tenerla disponible para su consulta para el escenario 3
        assignIdImgList(listResponseBodyRImg);

    }

    public static GetCatImgList executeGetMethodWithThe(String resourceApi) {
        return Tasks.instrumented(GetCatImgList.class, resourceApi);
    }

}
