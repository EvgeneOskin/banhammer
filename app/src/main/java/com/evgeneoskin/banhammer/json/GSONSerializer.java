package com.evgeneoskin.banhammer.json;

import com.google.gson.Gson;

public class GSONSerializer implements JSONSerializer {
    Gson gson;

    public GSONSerializer() {
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
