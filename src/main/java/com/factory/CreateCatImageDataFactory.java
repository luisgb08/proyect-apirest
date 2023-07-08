package com.factory;

import com.github.javafaker.Faker;
import com.models.UploadCatRequest;

import static com.utils.ReadParamProperties.findParam;

import static com.models.CreateCatImgRequestBuilder.aCatImg;

public class CreateCatImageDataFactory {

    private static final Faker FAKER = new Faker();
    public static final String IDRPOSITION = String.valueOf(FAKER.random().nextInt(0, Integer.valueOf(findParam("LIMIT"))));
    public static final String IDFILEPOSITION = String.valueOf(FAKER.random().nextInt(0, (Integer.valueOf(findParam("CANTIDFILE")))-1));

}
