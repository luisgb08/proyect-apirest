package com.factory;

import com.github.javafaker.Faker;
import static com.utils.ReadParamProperties.findParam;

public class CreateCatImageDataFactory {
    private static final Faker FAKER = new Faker();
    public static final String IDRPOSITION = String.valueOf(FAKER.random().nextInt(0, Integer.valueOf(findParam("LIMIT"))));
    public static final String IDFILEPOSITION = String.valueOf(FAKER.random().nextInt(0, (Integer.valueOf(findParam("CANTIDFILE")))-1));

}
