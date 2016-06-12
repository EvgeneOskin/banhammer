package com.evgeneoskin.banhammer.vk;

import android.app.Activity;
import android.content.Intent;

import com.evgeneoskin.banhammer.vk.models.BannedUser;
import com.evgeneoskin.banhammer.vk.models.Group;

import java.util.List;

import rx.Observable;

public interface VK {
    void login(Activity activity);
    Observable<List<Group>> listGroups();
    Observable<List<BannedUser>> listBannedUsers(Group group);
    boolean onActivityResult(int requestCode, int resultCode, Intent data);
}
