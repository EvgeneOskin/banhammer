package com.evgeneoskin.banhammer.json;

public interface Serializer {

    <T> T serialize (String data, Class<T> classObject);
    <T> String deserialize (T instance);
}
