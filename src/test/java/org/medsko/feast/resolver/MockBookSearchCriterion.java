package org.medsko.feast.resolver;

import org.medsko.feast.domain.BookSearchCriterion.Format;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.medsko.feast.domain.BookSearchCriterion.Format.ISBN;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface MockBookSearchCriterion {

    long ID = -1;
    String VALUE = "";
    Format FORMAT = ISBN;

    long id() default ID;

    String value() default VALUE;

    Format format() default ISBN;

}
