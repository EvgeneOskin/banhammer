package com.evgeneoskin.banhammer.json;

public interface JSONSerializer {

    <T> T serialize (String data, Class<T> classObject);
    <T> String deserialize (T instance);
}
