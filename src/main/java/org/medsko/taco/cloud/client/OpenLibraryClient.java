package org.medsko.taco.cloud.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.medsko.taco.cloud.domain.Book;
import org.medsko.taco.cloud.domain.BookSearchCriterion;
import org.medsko.taco.cloud.dto.SearchResponse;
import org.medsko.taco.cloud.util.SearchUrlBuilder;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Component
public class OpenLibraryClient {

    private final static String BASE_URL = "https://openlibrary.org/api";

    private final static String PARAMETER_JSON_FORMAT = "&format=json";

    private final static String PARAMETER_DETAILS = "jscmd=details";

    private RestTemplate rest;

    private Traverson traverson;

    public OpenLibraryClient() {
        rest = new RestTemplate();
        traverson = new Traverson(URI.create(BASE_URL), MediaTypes.HAL_JSON);
    }

    public Book getBook(Book book) {
        if (!book.isSearchable()) {
            return book;
        }
        Map<String, String> parameters = prepareBookSearchParameters(book);
        String bookUrl = BASE_URL + "/books?bibkeys={bibkeys}" + PARAMETER_JSON_FORMAT;

        String rawResult = rest.getForObject(bookUrl, String.class, parameters);
        log.info("Raw result: " + rawResult);
        ResponseEntity<Book[]> response = rest.getForEntity(bookUrl, Book[].class, parameters);

        response.getHeaders()
                .forEach((headerKey, _value) -> log.info(headerKey));
        Book[] result = response.getBody();
        assert result != null;
        Stream.of(result).forEach(bookResponse -> log.info(bookResponse.toString()));

        return book;
    }

    public void searchForCleanCode() {

        Map<String, String> parameters = new HashMap<>();
        String url = new SearchUrlBuilder(parameters)
                .withTitle("clean code")
                .build();

        log.info("Raw response: " + rest.getForObject(url, String.class, parameters));

        SearchResponse response = rest.getForObject(url, SearchResponse.class, parameters);
        log.info(String.valueOf(response));
    }

    private Map<String, String> prepareBookSearchParameters(Book book) {
        String preparedBibKeys = prepareBibKeys(book);
        return Collections.singletonMap("bibkeys", preparedBibKeys);
    }

    private String prepareBibKeys(Book book) {
        StringBuilder builder = new StringBuilder();
        book.getSearchCriteria()
                .forEach(criterion -> builder.append(prepareSearchCriterion(criterion)));
        return builder.toString();
    }


    private String prepareSearchCriterion(BookSearchCriterion criterion) {
        if (StringUtils.isBlank(criterion.getValue()))
            return "";
        return criterion.getFormat().name() + ":" + criterion.getValue();
    }

    public void setRest(RestTemplate rest) {
        this.rest = rest;
    }
}
