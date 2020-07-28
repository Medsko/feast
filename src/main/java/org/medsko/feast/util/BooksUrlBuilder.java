package org.medsko.feast.util;

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

    /**
     * By default, Open Library returns JSON specifying a couple of URLs: a field info_url (to book page in Open Library)
     * and a preview_url. This might be handy if redirecting the user to Open Library is the goal - so far my intentions
     * are more scrapey than that.
     * <p>
     * Since the format of the response is tightly coupled to the presence of this parameter, perhaps some construction
     * that mirrors this dependency is in order.
     * @return {@code this}
     */
    public BooksUrlBuilder asData() {
        appendParameter(DATA_FORMAT_PARAMETER);
        return this;
    }

    /**
     * By default, Open Library will return javascript. You probably want to call this method.
     * @return {@code this}
     */
    public BooksUrlBuilder asJson() {
        appendParameter(JSON_FORMAT_PARAMETER);
        return this;
    }
}
