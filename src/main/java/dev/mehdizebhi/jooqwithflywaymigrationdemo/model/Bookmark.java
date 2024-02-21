package dev.mehdizebhi.jooqwithflywaymigrationdemo.model;

import java.util.List;

public record Bookmark (
        Long id,
        String url,
        String title,
        User createdBy,
        List<Tag> tags
){
}