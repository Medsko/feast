package org.medsko.taco.cloud.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class AbstractUrlBuilder<T extends AbstractUrlBuilder<?>> {

    private final static String BASE_URL = "https://openlibrary.org/";
    private Map<String, String> parameters;

    protected String url;
    protected int arguments;

    AbstractUrlBuilder(Map<String, String> parameters, String api) {
        this.parameters = parameters;
        url = BASE_URL + api;
        arguments = 0;
    }

    public String build() {
        log.info("Url constructed: " + url);
        return url;
    }

    @SuppressWarnings("unchecked")
    public T withLimit(int limit) {
        appendParameter("limit=" + limit);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T withPage(int page) {
        appendParameter("page=" +page);
        return (T) this;
    }

    protected void appendParameter(String completeParameter) {
        appendParamSeparator();
        url += completeParameter;
    }

    protected void appendParameter(String name, String value) {
        appendParamSeparator();
        url += createParameterPlaceholder(name);
        parameters.put(name, prepareParameterValue(value));
    }

    private String createParameterPlaceholder(String name) {
        return name + "={" + name + "}";
    }

    private String prepareParameterValue(String value) {
        return value.replaceAll("\\s", "+");
    }

    private void appendParamSeparator() {
        if (arguments++ == 0) url += "?";
        else url += "&";
    }

}
