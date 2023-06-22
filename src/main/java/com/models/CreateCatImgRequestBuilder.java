package com.models;

import java.io.File;

public class CreateCatImgRequestBuilder {

    private UploadCatRequest catImgRequest;

    private CreateCatImgRequestBuilder() {
        catImgRequest = new UploadCatRequest();
    }

    public static CreateCatImgRequestBuilder aCatImg() {
        return new CreateCatImgRequestBuilder();

    }

    public CreateCatImgRequestBuilder withFile(String file) {
        this.catImgRequest.setFile(file);
        return this;
    }

    public CreateCatImgRequestBuilder withSub_id(String sub_id) {
        this.catImgRequest.setSub_id(sub_id);
        return this;
    }

    public UploadCatRequest build() {
        return catImgRequest;
    }
}
