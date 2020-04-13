package org.medsko.taco.cloud.util;

import java.util.Map;

public class BooksUrlBuilder extends AbstractUrlBuilder<BooksUrlBuilder> {

    private final static String BOOKS_API = "api/books";

    private final static String DATA_FORMAT_PARAMETER = "jscmd=data";

    private final static String JSON_FORMAT_PARAMETER = "format=json";

    public BooksUrlBuilder(Map<String, String> parameters) {
        super(parameters, BOOKS_API);
    }

    public BooksUrlBuilder withBibkeys(String bibkeys) {
        appendParameter("bibkeys", bibkeys);
        return this;
    }

    public BooksUrlBuilder asData() {
        appendParameter(DATA_FORMAT_PARAMETER);
        return this;
    }

    public BooksUrlBuilder asJson() {
        appendParameter(JSON_FORMAT_PARAMETER);
        return this;
    }
}
