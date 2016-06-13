package com.evgeneoskin.banhammer.vk.models;

import java.util.List;

public class ResponseUserItems extends Items<User> {
    Items<User> response;

    @Override
    public List<User> getItems() {
        return response.getItems();
    }
}
