package com.evgeneoskin.banhammer.vk.models;

import java.util.List;

public class ResponseBannedItems extends Items<BannedUser> {
    Items<BannedUser> response;

    @Override
    public List<BannedUser> getItems() {
        return response.getItems();
    }
}
