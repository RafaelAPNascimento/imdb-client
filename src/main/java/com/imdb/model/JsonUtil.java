package com.imdb.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public final class JsonUtil
{
    private static final Gson gson = new GsonBuilder().create();

    public static <T> T getPojo(String json, Class<T> type)
    {
        return gson.fromJson(json, Objects.requireNonNull(type));
    }
}
