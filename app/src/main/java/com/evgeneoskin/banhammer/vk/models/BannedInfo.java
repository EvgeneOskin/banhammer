package com.evgeneoskin.banhammer.vk.models;

import org.parceler.Parcel;

@Parcel
public class BannedInfo extends BanInfo {
    public long date;
    public long admin_id;
}
