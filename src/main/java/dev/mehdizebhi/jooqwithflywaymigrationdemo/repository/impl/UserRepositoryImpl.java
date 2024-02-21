package dev.mehdizebhi.jooqwithflywaymigrationdemo.repository.impl;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl {

    private final DSLContext dsl;

    public UserRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }
}
