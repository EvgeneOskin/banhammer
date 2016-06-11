package com.evgeneoskin.banhammer.config;

import android.app.Activity;

import com.evgeneoskin.banhammer.json.GSONSerializer;
import com.evgeneoskin.banhammer.json.Serializer;
import com.evgeneoskin.banhammer.vk.VK;
import com.evgeneoskin.banhammer.vk.VKWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class AppModule extends AbstractModule {

    private final Activity activity;

    public AppModule(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void configure() {
        bind(Serializer.class).to(GSONSerializer.class);
        bind(Activity.class).toInstance(activity);
        bind(VK.class).to(VKWrapper.class);
        bind(String.class).annotatedWith(Names.named("vk scope")).toInstance("groups");
    }
}
