package com.factory;

import com.github.javafaker.Faker;

public class CreateEmployeeDataFactory {

    private static final Faker FAKER = new Faker();

    public static final String ID_EMPLOYEE = String.valueOf(FAKER.random().nextInt(0,9));

    public static final String NAME_EMPLOYEE = String.valueOf(FAKER.name().fullName());
    public static final String SALARY_EMPLOYEE = String.valueOf(FAKER.random().nextInt(20000000));
    public static final String AGE_EMPLOYEE = String.valueOf(FAKER.random().nextInt(1,100));

}
