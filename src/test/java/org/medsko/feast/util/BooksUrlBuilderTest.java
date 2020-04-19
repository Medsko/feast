package org.medsko.feast.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BooksUrlBuilderTest {

    private BooksUrlBuilder builder;

    private Map<String, String> parameters;

    @BeforeEach
    void setUp() {
        parameters = new HashMap<>();
        builder = new BooksUrlBuilder(parameters);
    }

    // TODO: write this test, and evaluate whether class under test is well-designed.

    @Test
    void withBibkeys() {

    }

    @Test
    void asData() {
    }

    @Test
    void asJson() {
    }
}