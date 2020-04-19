package org.medsko.feast.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexPlaygroundTest {

    @Test
    void testRange() {
        Pattern range = Pattern.compile("[a-zA-Z0-9]{2,4}");
        Pattern options = Pattern.compile("[a-zA-Z0-9]{2}|[a-zA-Z0-9]{4}");
        String tooShortTooLong = "d1A";
        assertTrue(range.matcher(tooShortTooLong).matches());
        assertFalse(options.matcher(tooShortTooLong).matches());
    }

}
