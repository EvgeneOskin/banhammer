package com.evgeneoskin.banhammer.vk;

import android.app.Activity;
import android.content.Intent;

import com.evgeneoskin.banhammer.json.GSONSerializer;
import com.evgeneoskin.banhammer.json.JSONSerializer;
import com.evgeneoskin.banhammer.vk.models.Group;
import com.evgeneoskin.banhammer.vk.models.ResponseGroupItems;
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

public class VKWrapper implements VK {

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
        final VKRequest request = VKApi.groups().get(parameters);
        return Observable.create(new RequestOnSubscribe(request))
                .map(new FuncResponseDeserialize(serializer, ResponseGroupItems.class))
                .map(new ItemsRetriever<Group>());
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean handled = VKSdk.onActivityResult(
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
        return handled;
    }
}
