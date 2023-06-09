package com.tasks;

import com.google.gson.Gson;
import com.models.CreateFavCatRequest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.json.JSONObject;
import static com.models.CreateFavCatImgRequestBuilder.aFavCatImg;
import static com.utils.ReadParamProperties.findParam;
import static com.utils.SaveFavCatIdImg.*;
import static net.serenitybdd.rest.SerenityRest.given;

public class PostFavCatImg implements Task {

    private final String resourceApi;
    private final String idImage;
    private final String subId;

    public PostFavCatImg(String resourceApi, String idImage, String subId) {
        this.resourceApi = resourceApi;
        this.idImage = idImage;
        this.subId = subId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
         CreateFavCatRequest catfav = aFavCatImg()
                .withImage_id(idImage)
                .withSub_id(subId)
                .build();

        String catfavJson = new Gson().toJson(catfav);

        //Se guarda la respuesta obtenida de la creación del favorito en un String
        String responseFav =

        given()
                .header("x-api-key", findParam("APIKEY"))
                .body(catfavJson)
                .and().when().post(resourceApi)
                .then().extract().body().asString();

        //Se encapsula dicha respuesta en un objeto Json
        JSONObject jsonObject = new JSONObject(responseFav);
        //Se extrae el valor del atributo id del favorito que resulta ser un entero y se convierte a String
        String idFav = String.valueOf(jsonObject.getInt("id"));
        //Se envía y setea en otra clase para dejarlo disponible para otros escenarios a ejecutar
        setCatFavIdImg(idFav);
    }

    public static PostFavCatImg createFavCatWithTheElements(String resourceApi, String idImage, String subId) {
        return Tasks.instrumented(PostFavCatImg.class, resourceApi, idImage, subId);
    }

}
