package org.medsko.taco.cloud.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponse {

    private Integer start;
    private Integer numFound;
    private List<SolrDocument> docs;
}
