package org.medsko.taco.cloud.dto;

import lombok.Data;

import java.util.List;

/**
 * A document describing a book, in
 * <a href="https://github.com/internetarchive/openlibrary/blob/master/conf/solr/conf/schema.xml">solr format</>.
 */
@Data
public class SolrDocument {

    private String key;
    private String title;
    private Integer firstPublishYear;

    // TODO: check whether the next section of fields should be lists as well
    private String type;
    private String redirects;
    private String titleSuggest;
    private Boolean hasFullText;    // TODO: test whether this can be deserialized (field name is has_fulltext)
    private String subtitle;
    private String alternativeTitle;

    private List<String> language;
    private List<String> lccn;
    private List<String> oclc;
    private List<String> isbn;
    private List<String> authorKey;
    private List<String> authorName;
}
