package org.medsko.taco.cloud.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class BookSearchCriterion {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    @Enumerated
    private Format format;

    public enum Format {
        ISBN, LCCN, OCLC, OLID
    }

}
