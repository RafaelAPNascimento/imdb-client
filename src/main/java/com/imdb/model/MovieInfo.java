package com.imdb.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MovieInfo
{
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("resultType")
    @Expose
    private String resultType;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;
}
