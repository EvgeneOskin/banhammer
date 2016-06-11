package com.evgeneoskin.banhammer.json;

import com.google.gson.Gson;
import com.google.inject.Inject;

public class GSONSerializer implements Serializer {
    Gson gson;

    @Inject
    public GSONSerializer () {
        gson = new Gson();
    }

    @Override
    public <T> T serialize(String json, Class<T> classObject) {
        return gson.fromJson(json, classObject);
    }

    @Override
    public <T> String deserialize(T instance) {
        return gson.toJson(instance);
    }
}
