package com.evgeneoskin.banhammer.vk.models;

/**
 * Created by eoskin on 12.06.16.
 */
public class User {
    public long id;
    public String first_name;
    public String last_name;

    public String getFullName() {
        return String.format("%s %s", first_name, last_name);
    }
}
