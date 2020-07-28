package org.medsko.feast.random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailDomainBlacklistComposerTest {

    private final static String INPUT_DIRECTORY = "C:\\Users\\melle\\Documents\\input";
    private final static String INPUT_FILE = "popularPrivateEmailDomains.txt";

    private EmailDomainBlacklistComposer composer;

    @BeforeEach
    void setUp() {
        composer = new EmailDomainBlacklistComposer();
    }

    @Test
    void testComposeBlacklist() throws IOException {
        String testInputFile = Paths.get(INPUT_DIRECTORY, "testDomains.txt").toString();
        List<String> blacklist = composer.composeBlacklist(testInputFile);
        assertAll(
                () -> assertEquals(4, blacklist.size()),
                () -> assertEquals(1, blacklist.stream().filter(domain -> domain.endsWith(".*")).count()),
                () -> assertEquals(3, blacklist.stream().filter(domain -> !domain.endsWith(".*")).count()),
                () -> assertEquals(1, blacklist.stream().filter(domain -> domain.matches("hotmail.*")).count())
        );
    }

    @Test
    void composeActualBlacklist() throws IOException {
        String inputFile = Paths.get(INPUT_DIRECTORY, INPUT_FILE).toString();
        composer.composeBlacklist(inputFile).forEach(System.out::println);
    }

}
