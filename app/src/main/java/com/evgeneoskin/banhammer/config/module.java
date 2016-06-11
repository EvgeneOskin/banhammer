package com.evgeneoskin.banhammer.config;

import android.app.Activity;

import com.evgeneoskin.banhammer.json.GSONSerializer;
import com.evgeneoskin.banhammer.json.Serializer;
import com.evgeneoskin.banhammer.vk.VKWrapper;
import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    private final Activity activity;

    public Module(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected void configure() {
        bind(Serializer.class).to(GSONSerializer.class);
        bind(VKWrapper.class).to(VKWrapper.class);
        bind(Activity.class).toInstance(activity);
    }
}
