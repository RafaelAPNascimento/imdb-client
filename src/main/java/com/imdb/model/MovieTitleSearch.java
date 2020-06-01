package com.imdb.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MovieTitleSearch {

    /**
     *
     * (Required)
     *
     */
    @SerializedName("searchType")
    @Expose
    private String searchType;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("expression")
    @Expose
    private String expression;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("results")
    @Expose
    private List<MovieInfo> movies = null;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

}