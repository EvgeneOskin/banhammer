package com.evgeneoskin.banhammer.vk.models;

import java.util.List;

public class Group {
    long id;
    String name;
    String screen_name;
    int is_closed;
    int type;
    int is_admin;
    int is_member;
    String photo_50;
    String photo_100;
    String photo_200;

    public boolean isAdmin() {
        return is_admin == 1;
    }

    public String getName() {
        return name;
    }
}