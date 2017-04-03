package com.example.browseimages.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "totalHits",
        "hits",
        "total"
})
public class ImageListMainModel {

    @JsonProperty("totalHits")
    public Integer totalHits;
    @JsonProperty("hits")
    public List<Hit> hits = null;
    @JsonProperty("total")
    public Integer total;

}
