package com.evgeneoskin.banhammer.vk;

import android.app.Activity;
import android.content.Intent;

import com.evgeneoskin.banhammer.json.GSONSerializer;
import com.evgeneoskin.banhammer.json.JSONSerializer;
import com.evgeneoskin.banhammer.vk.models.BanInfo;
import com.evgeneoskin.banhammer.vk.models.BanUser;
import com.evgeneoskin.banhammer.vk.models.BanUserResult;
import com.evgeneoskin.banhammer.vk.models.BannedUser;
import com.evgeneoskin.banhammer.vk.models.Group;
import com.evgeneoskin.banhammer.vk.models.ResponseBannedItems;
import com.evgeneoskin.banhammer.vk.models.ResponseGroupItems;
import com.evgeneoskin.banhammer.vk.models.ResponseUserItems;
import com.evgeneoskin.banhammer.vk.models.User;
import com.evgeneoskin.banhammer.vk.rx.FuncResponseDeserialize;
import com.evgeneoskin.banhammer.vk.rx.ItemsRetriever;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;

import java.util.List;

import rx.Observable;

public class VKImpl implements VK {

    JSONSerializer serializer = new GSONSerializer();
    String scope = "groups";

    public void login(Activity activity) {
        if (!VKSdk.isLoggedIn()) {
            VKSdk.login(activity, this.scope);
        }
    }

    public Observable<List<Group>> listGroups() {
        final VKParameters parameters = new VKParameters();
        parameters.put("extended", 1);
        parameters.put("filter", "moder");
        final VKRequest request = VKApi.groups().get(parameters);
        return Observable.create(new RequestOnSubscribe(request))
                .map(new FuncResponseDeserialize(serializer, ResponseGroupItems.class))
                .map(new ItemsRetriever<Group>());
    }

    public Observable<List<BannedUser>> listBannedUsers(Group group) {
        final VKParameters parameters = new VKParameters();
        parameters.put("group_id", group.id);
        final VKRequest request = VKApi.groups().getBanned(parameters);
        return Observable.create(new RequestOnSubscribe(request))
                .map(new FuncResponseDeserialize(serializer, ResponseBannedItems.class))
                .map(new ItemsRetriever<BannedUser>());
    }

    @Override
    public Observable<List<User>> findUser(String screenName) {
        final VKParameters parameters = new VKParameters();
        parameters.put("screen_name", screenName);
        final VKRequest request = VKApi.utils().resolveSrcreenName(parameters);
        return Observable.create(new RequestOnSubscribe(request))
                .map(new FuncResponseDeserialize(serializer, ResponseUserItems.class))
                .map(new ItemsRetriever<User>());
    }

    @Override
    public Observable<BanUserResult> banUser(Group group, int userId, BanInfo banInfo) {
        final VKParameters parameters = new VKParameters();
        parameters.put("group_id", group.id);
        parameters.put("user_id", userId);
        parameters.put("end_date", banInfo.end_date);
        parameters.put("reason", banInfo.reason);
        parameters.put("comment", banInfo.comment);

        final VKRequest request = VKApi.groups().banUser(parameters);
        return Observable.create(new RequestOnSubscribe(request))
                .map(new FuncResponseDeserialize(serializer, BanUserResult.class))
                .map(new ItemsRetriever<BannedUser>());

    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return VKSdk.onActivityResult(
                requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
                    @Override
                    public void onResult(VKAccessToken res) {
                        // Пользователь успешно авторизовался
                    }
                    @Override
                    public void onError(VKError error) {
                        // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                    }
                }
        );
    }
}
