package org.medsko.feast.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SearchUrlBuilderTest {

    private SearchUrlBuilder builder;

    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        parameters = new HashMap<>();
        builder = new SearchUrlBuilder(parameters);
    }

    @Test
    void searchByTitle() {

        builder.withTitle("Clean code");

        assertAll(
                () -> assertEquals("https://openlibrary.org/search.json?title={title}", builder.build()),
                () -> assertEquals(1, parameters.size()),
                () -> assertNotNull(parameters.get("title")),
                () -> assertEquals("Clean+code", parameters.get("title"))
        );
    }

    @Test
    void searchByAuthor() {

        builder.withAuthor("Uncle bob");

        assertAll(
                () -> assertEquals("https://openlibrary.org/search.json?author={author}", builder.build()),
                () -> assertEquals(1, parameters.size()),
                () -> assertNotNull(parameters.get("author")),
                () -> assertEquals("Uncle+bob", parameters.get("author"))
        );
    }

    @Test
    void searchByTitleAndAuthor() {

        builder
                .withAuthor("Uncle bob")
                .withTitle("Clean code");

        assertAll(
                () -> assertEquals("https://openlibrary.org/search.json?author={author}&title={title}", builder.build()),
                () -> assertEquals(2, parameters.size()),
                () -> assertEquals("Uncle+bob", parameters.get("author")),
                () -> assertEquals("Clean+code", parameters.get("title"))
        );
    }
}