package com.example.browseimages.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "previewHeight",
        "likes",
        "favorites",
        "tags",
        "webformatHeight",
        "views",
        "webformatWidth",
        "previewWidth",
        "comments",
        "downloads",
        "pageURL",
        "previewURL",
        "webformatURL",
        "imageWidth",
        "user_id",
        "user",
        "type",
        "id",
        "userImageURL",
        "imageHeight"
})
public class Hit {

    @JsonProperty("previewHeight")
    public Integer previewHeight;
    @JsonProperty("likes")
    public Integer likes;
    @JsonProperty("favorites")
    public Integer favorites;
    @JsonProperty("tags")
    public String tags;
    @JsonProperty("webformatHeight")
    public Integer webformatHeight;
    @JsonProperty("views")
    public Integer views;
    @JsonProperty("webformatWidth")
    public Integer webformatWidth;
    @JsonProperty("previewWidth")
    public Integer previewWidth;
    @JsonProperty("comments")
    public Integer comments;
    @JsonProperty("downloads")
    public Integer downloads;
    @JsonProperty("pageURL")
    public String pageURL;
    @JsonProperty("previewURL")
    public String previewURL;
    @JsonProperty("webformatURL")
    public String webformatURL;
    @JsonProperty("imageWidth")
    public Integer imageWidth;
    @JsonProperty("user_id")
    public Integer userId;
    @JsonProperty("user")
    public String user;
    @JsonProperty("type")
    public String type;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("userImageURL")
    public String userImageURL;
    @JsonProperty("imageHeight")
    public Integer imageHeight;

}
