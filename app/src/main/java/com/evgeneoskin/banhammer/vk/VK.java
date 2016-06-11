package com.evgeneoskin.banhammer.vk;

import android.app.Activity;
import android.content.Intent;

import com.evgeneoskin.banhammer.vk.models.GroupItems;
import rx.Observable;

public interface VK {
    void login(Activity activity);
    Observable<GroupItems> listGroups();
    boolean onActivityResult(int requestCode, int resultCode, Intent data);
}
