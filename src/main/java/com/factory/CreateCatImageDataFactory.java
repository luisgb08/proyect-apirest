package com.factory;

import com.github.javafaker.Faker;
import com.models.UploadCatRequest;

import static com.utils.ReadParamProperties.findParam;

import static com.models.CreateCatImgRequestBuilder.aCatImg;

public class CreateCatImageDataFactory {

    private static final Faker FAKER = new Faker();

    public static final String IDRPOSITION = String.valueOf(FAKER.random().nextInt(0, Integer.valueOf(findParam("limit"))));
    public static final String IDFILEPOSITION = String.valueOf(FAKER.random().nextInt(0, (Integer.valueOf(findParam("cantidfile")))-1));
    public static final String NAME_EMPLOYEE = String.valueOf(FAKER.name().fullName());
    public static final String SALARY_EMPLOYEE = String.valueOf(FAKER.random().nextInt(20000000));
    public static final String AGE_EMPLOYEE = String.valueOf(FAKER.random().nextInt(1,100));


}
