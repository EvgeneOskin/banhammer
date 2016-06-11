package com.evgeneoskin.banhammer;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.List;

public class VKWrapper {

    private final Activity activity;

    VKWrapper(@NonNull Activity activity) {
        this.activity = activity;
    }

    void login() {
        if (!VKSdk.isLoggedIn()) {
            VKSdk.login(activity, "groups");
        }
    }

    void listGroups() {
        VKParameters parameters = new VKParameters();
        VKRequest request = VKApi.groups().get(parameters);
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                //Do complete stuff
            }
            @Override
            public void onError(VKError error) {
                //Do error stuff
            }
            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                //I don't really believe in progress
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
