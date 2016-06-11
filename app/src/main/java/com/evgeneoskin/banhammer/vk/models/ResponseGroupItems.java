package com.evgeneoskin.banhammer.vk.models;

import java.util.List;

public class ResponseGroupItems extends Items<Group> {
    Items<Group> response;

    @Override
    public List<Group> getItems() {
        return response.getItems();
    }
}
