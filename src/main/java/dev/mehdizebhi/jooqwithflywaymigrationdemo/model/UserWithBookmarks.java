package dev.mehdizebhi.jooqwithflywaymigrationdemo.model;

import java.util.List;

public record UserWithBookmarks(Long id, String name, String email, List<BookmarkInfo> bookmarks) {
    public record BookmarkInfo (Long id, String title, String url){}
}
