package com.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.io.File;

import static com.tasks.PostUploadCatImg.dataFormWebInteractions;
import static com.utils.ReadParamProperties.findParam;
import static net.serenitybdd.rest.SerenityRest.given;

public class UploadCatImgRestAssured implements Task {

    private final String pathImg;
    private final String catImgOk;
    private final String uploadSubId;
    private final String resourceApi;

    public UploadCatImgRestAssured(String pathImg, String catImgOk, String uploadSubId, String resourceApi) {
        this.pathImg = pathImg;
        this.catImgOk = catImgOk;
        this.uploadSubId = uploadSubId;
        this.resourceApi = resourceApi;
    }

    public static UploadCatImgRestAssured uploadCatImgWithTheElements2(String pathImg, String catImgOk, String uploadSubId, String resourceApi) {
        return Tasks.instrumented(UploadCatImgRestAssured.class, pathImg, catImgOk, uploadSubId, resourceApi);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        File file = new File("src/test/resources/images/cat_1.jpeg");

        actor.attemptsTo(Post.to("images/upload")
                        .with(requestSpecification -> requestSpecification
                        .header("x-api-key","live_6DcftSIDs0osSIuayASrljuhCkwa8T2GxK7dofwo4yYjmrmw0DYZ5XZ4LIdi87NK")
                        .contentType(ContentType.MULTIPART)
                        .multiPart("file", file, "image/jpeg").log().all()
                        ));

    }

}
