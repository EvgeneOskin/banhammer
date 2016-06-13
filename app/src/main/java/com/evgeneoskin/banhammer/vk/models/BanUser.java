package com.evgeneoskin.banhammer.vk.models;

public class BanUser extends AbsBanUser {
    public int group_id;
    public int user_id;

    public static class ByUsername  extends AbsBanUser {
        public int username;
    }
}

class AbsBanUser {
    public int end_date;
    public int reason;
    public String comment;
    public int comment_visible;
}