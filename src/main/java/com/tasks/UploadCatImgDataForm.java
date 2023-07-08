package com.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import static com.tasks.PostUploadCatImg.dataFormWebInteractions;
import static com.utils.ReadParamProperties.findParam;

public class UploadCatImgDataForm implements Task {

    private final String pathImg;
    private final String catImgOk;
    private final String uploadSubId;
    private final String resourceApi;


    public UploadCatImgDataForm(String pathImg, String catImgOk, String uploadSubId, String resourceApi) {
        this.pathImg = pathImg;
        this.catImgOk = catImgOk;
        this.uploadSubId = uploadSubId;
        this.resourceApi = resourceApi;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        dataFormWebInteractions(pathImg + catImgOk, uploadSubId, findParam("BASE_URI")+findParam("BASE_PATH")+"/"+resourceApi, findParam("APIKEY"));
    }

    public static UploadCatImgDataForm uploadCatImgWithThe(String pathImg, String catImgOk, String uploadSubId, String resourceApi) {
        return Tasks.instrumented(UploadCatImgDataForm.class, pathImg, catImgOk, uploadSubId, resourceApi);
    }

}
