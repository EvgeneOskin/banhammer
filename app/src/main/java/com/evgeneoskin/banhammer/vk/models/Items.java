package com.evgeneoskin.banhammer.vk.models;

import java.util.List;

public class Items<T> {
    int count;
    List<T> items;

    public List<T> getItems() {
        return items;
    }
}

