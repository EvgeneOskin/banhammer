package com.evgeneoskin.banhammer;

import android.graphics.drawable.Drawable;

import com.vk.sdk.VKSdk;

public class Application extends android.app.Application {

    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this.getApplicationContext());
    }
}
