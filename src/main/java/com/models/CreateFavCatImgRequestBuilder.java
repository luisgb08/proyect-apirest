package com.models;

public class CreateFavCatImgRequestBuilder {

    private CreateFavCatRequest catFavImgRequest;

    private CreateFavCatImgRequestBuilder() {
        catFavImgRequest = new CreateFavCatRequest();
    }

    public static CreateFavCatImgRequestBuilder aFavCatImg() {
        return new CreateFavCatImgRequestBuilder();

    }

    public CreateFavCatImgRequestBuilder withImage_id(String image_id) {
        this.catFavImgRequest.setImage_id(image_id);
        return this;
    }

    public CreateFavCatImgRequestBuilder withSub_id(String sub_id) {
        this.catFavImgRequest.setSub_id(sub_id);
        return this;
    }

    public CreateFavCatRequest build() {
        return catFavImgRequest;
    }

}
