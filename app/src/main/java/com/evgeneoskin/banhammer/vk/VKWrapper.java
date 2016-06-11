package com.evgeneoskin.banhammer.vk;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.evgeneoskin.banhammer.json.Serializer;
import com.evgeneoskin.banhammer.vk.models.GroupItems;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import rx.Observable;
import rx.functions.Func1;

public class VKWrapper implements VK {

    private final Activity activity;
    private final Serializer serializer;
    private final String scope;

    @Inject
    VKWrapper(@NonNull Activity activity, @NonNull Serializer serializer,
              @Named("vk scope") String scope) {
        this.serializer = serializer;
        this.activity = activity;
        this.scope = scope;
    }

    public void login() {
        if (!VKSdk.isLoggedIn()) {
            VKSdk.login(activity, this.scope);
        }
    }

    public Observable<GroupItems> listGroups() {
        final VKParameters parameters = new VKParameters();
        final VKRequest request = VKApi.groups().get(parameters);
        return Observable.create(new RequestOnSubscribe(request)).map(new Func1<VKResponse, GroupItems>() {

            @Override
            public GroupItems call (VKResponse response){
                return serializer.serialize(
                        response.responseString, GroupItems.class
                );
            }
        });
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
