package org.medsko.feast.util;

import org.medsko.feast.domain.Book;
import org.medsko.feast.domain.BookSearchCriterion;
import org.springframework.lang.NonNull;

import java.util.StringJoiner;

public final class StringUtils {

    private StringUtils() {
        // Cuz...you know.
    }

    @NonNull
    public static String prepareBibKeys(@NonNull Book book) {
        StringJoiner joiner = new StringJoiner(",");
        book.getSearchCriteria()
                .forEach(criterion -> joiner.add(prepareSearchCriterion(criterion)));
        return joiner.toString();
    }

    @NonNull
    private static String prepareSearchCriterion(BookSearchCriterion criterion) {
        if (org.apache.commons.lang3.StringUtils.isBlank(criterion.getValue()))
            return "";
        return criterion.getFormat().name() + ":" + criterion.getValue();
    }

}
