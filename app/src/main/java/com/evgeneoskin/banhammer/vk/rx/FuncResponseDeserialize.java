package com.evgeneoskin.banhammer.vk.rx;

import com.evgeneoskin.banhammer.json.JSONSerializer;
import com.vk.sdk.api.VKResponse;

import rx.functions.Func1;

public class FuncResponseDeserialize<T> implements Func1<VKResponse, T> {

    private final Class<T> classInstance;
    private final JSONSerializer serializer;

    public FuncResponseDeserialize(JSONSerializer serializer, Class<T> classInstance) {
        this.classInstance = classInstance;
        this.serializer = serializer;
    }

    @Override
    public T call (VKResponse response){
        return serializer.serialize(response.responseString, classInstance);
    }
}
