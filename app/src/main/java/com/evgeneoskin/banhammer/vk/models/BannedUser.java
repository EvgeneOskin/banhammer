package com.evgeneoskin.banhammer.vk.models;

public class BannedUser extends User {
    public BanInfo ban_info;

    public String getAdminFullName() {
        return String.valueOf(ban_info.admin_id);
    }
}
