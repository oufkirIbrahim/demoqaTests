package com.demoqa.helper;

import com.demoqa.model.User;
import com.github.javafaker.Faker;

public class UserTestHelper {

    private static final Faker faker = new Faker();

    public static User initUser() {
        return new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                String.valueOf(faker.number().numberBetween(18, 60)),
                String.valueOf(faker.number().numberBetween(30000, 120000)),
                faker.company().industry() // or use faker.job().field()
        );
    }
}
