package org.medsko.feast.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class BookSearchCriterion {

    // TODO: while this way of storing the codes for a book provides an easy way of assembling the 'bibkeys' search
    //      parameter, this format makes it somewhat more challenging to validate the values for different formats
    //      (e.g. ISBN can be 10 or 13 *digits*, while Open Library gives 'OL123M' as a valid example of an OLID.)
    // TODO: houd er wel rekening mee dat bv twee isbn codes mogelijk zijn per boek, als bijvoorbeeld beide formaten
    //      ISBN code (10 cijfers, of 13) gegeven zijn voor een bepaald boek. Dussss misschien abstracte super, met
    //      alleen de validatie formaat-specifiek?

    public enum Format {
        ISBN, LCCN, OCLC, OLID
    }

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    @Enumerated
    private Format format;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Book book;

}
