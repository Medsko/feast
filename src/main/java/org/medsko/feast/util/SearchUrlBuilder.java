package org.medsko.feast.util;

import java.util.Map;

public class SearchUrlBuilder extends AbstractUrlBuilder<SearchUrlBuilder> {

    private final static String SEARCH_API = "search.json";

    public SearchUrlBuilder(Map<String, String> parameters) {
        super(parameters, SEARCH_API);
    }

    public SearchUrlBuilder withTitle(String title) {
        appendParameter("title", title);
        return this;
    }

    public SearchUrlBuilder withAuthor(String author) {
        appendParameter("author", author);
        return this;
    }

}
