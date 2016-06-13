package com.evgeneoskin.banhammer.vk;

import android.app.Activity;
import android.content.Intent;

import com.evgeneoskin.banhammer.vk.models.BanInfo;
import com.evgeneoskin.banhammer.vk.models.BanUserResult;
import com.evgeneoskin.banhammer.vk.models.BannedUser;
import com.evgeneoskin.banhammer.vk.models.Group;
import com.evgeneoskin.banhammer.vk.models.ResponseResolveScreenName;

import java.util.List;

import rx.Observable;

public interface VK {
    void login(Activity activity);
    Observable<List<Group>> listGroups();
    Observable<List<BannedUser>> listBannedUsers(Group group);
    Observable<ResponseResolveScreenName> findUser(String username);
    Observable<BanUserResult> banUser(Group group, long userId, BanInfo banInfo);
    boolean onActivityResult(int requestCode, int resultCode, Intent data);
}
