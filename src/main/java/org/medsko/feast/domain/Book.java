package org.medsko.feast.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String rawResponse; // Holds the raw JSON of the Open Library response.

    @OneToMany
    private List<BookSearchCriterion> searchCriteria = new ArrayList<>();

    private String title;
    private String subTitle;
    private String author;

    /**
     * Tests whether this Book object has enough data to look up the book. This is the case if at least one of the four
     * identifying fields is filled.
     *
     * @return {@code true} if sufficient data is available to retrieve the book.
     */
    public boolean isSearchable() {
        return searchCriteria.stream()
                .anyMatch(criterion -> StringUtils.isNotBlank(criterion.getValue()));
    }
}
