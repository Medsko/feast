package org.medsko.feast.resolver;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.medsko.feast.domain.BookSearchCriterion;

public class BookSearchCriterionResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == BookSearchCriterion.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.findAnnotation(MockBookSearchCriterion.class)
                .map(this::createBookSearchCriterion)
                .orElseThrow(() -> new RuntimeException("This is probably why you should return null here instead"));
    }

    private BookSearchCriterion createBookSearchCriterion(MockBookSearchCriterion annotation) {
        BookSearchCriterion bean = new BookSearchCriterion();
        if (annotation.id() >= 0) {
            bean.setId(annotation.id());
        }
        bean.setValue(annotation.value());
        bean.setFormat(annotation.format());
        return bean;
    }
}
