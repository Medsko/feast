package org.medsko.feast.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FeastPropertiesTest {

    @Autowired
    private FeastProperties properties;

    @Test
    void hyphenToCamelCase() {
        assertNotNull(properties.getBaseUrl());
        assertEquals("http://some-url.com/", properties.getBaseUrl());
    }
}