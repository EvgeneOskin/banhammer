package com.evgeneoskin.banhammer.config;

import com.evgeneoskin.banhammer.json.GSONSerializer;
import com.evgeneoskin.banhammer.json.JSONSerializer;
import com.evgeneoskin.banhammer.vk.VK;
import com.evgeneoskin.banhammer.vk.VKWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(JSONSerializer.class).to(GSONSerializer.class);
        bind(VK.class).to(VKWrapper.class);
        bind(String.class).annotatedWith(Names.named("vk scope")).toInstance("groups");
    }
}
