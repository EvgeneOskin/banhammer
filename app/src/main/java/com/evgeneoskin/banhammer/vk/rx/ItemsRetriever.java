package com.evgeneoskin.banhammer.vk.rx;

import com.evgeneoskin.banhammer.vk.models.Items;

import java.util.List;

import rx.functions.Func1;

public class ItemsRetriever<G> implements Func1<Items<G>, List<G>> {
    @Override
    public List<G> call(Items<G> groupItems) {
        return groupItems.getItems();
    }
}
