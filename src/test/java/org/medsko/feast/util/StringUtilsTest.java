package org.medsko.feast.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medsko.feast.domain.Book;
import org.medsko.feast.domain.BookSearchCriterion;
import org.medsko.feast.resolver.BookSearchCriterionResolver;
import org.medsko.feast.resolver.MockBookSearchCriterion;

import static org.junit.jupiter.api.Assertions.*;
import static org.medsko.feast.domain.BookSearchCriterion.Format.LCCN;
import static org.medsko.feast.domain.BookSearchCriterion.Format.OCLC;

@ExtendWith(BookSearchCriterionResolver.class)
class StringUtilsTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    void noCriteria_emptyStringIsReturned() {

        String bibkeys = StringUtils.prepareBibKeys(book);

        assertAll(
                () -> assertNotNull(bibkeys, "NonNull contract implies empty string should be returned instead of null!"),
                () -> assertEquals("", bibkeys)
        );
    }

    @Test
    void onlyIsbn(@MockBookSearchCriterion(value = "1234512345") BookSearchCriterion criterion) {
        book.addSearchCriterion(criterion);
        String bibkeys = StringUtils.prepareBibKeys(book);

        assertAll(
                () -> assertNotNull(bibkeys),
                () -> assertEquals("ISBN:1234512345", bibkeys)
        );
    }

    @Test
    void twoAmericanFormats(@MockBookSearchCriterion(value = "2008054742", format = LCCN) BookSearchCriterion lccnCriterion,
                            @MockBookSearchCriterion(value = "297222669", format = OCLC) BookSearchCriterion oclcCriterion) {
        book.addSearchCriterion(lccnCriterion);
        book.addSearchCriterion(oclcCriterion);

        String bibkeys = StringUtils.prepareBibKeys(book);

        assertAll(
                () -> assertNotNull(bibkeys),
                () -> assertEquals("LCCN:2008054742,OCLC:297222669", bibkeys)
        );
    }
}