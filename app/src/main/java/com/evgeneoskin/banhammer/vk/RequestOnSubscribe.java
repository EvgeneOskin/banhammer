package com.evgeneoskin.banhammer.vk;

import com.evgeneoskin.banhammer.vk.models.Group;
import com.evgeneoskin.banhammer.vk.models.Items;
import com.google.common.base.Throwables;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import rx.Observable;
import rx.Subscriber;

public class RequestOnSubscribe implements Observable.OnSubscribe<VKResponse> {

    private final VKRequest request;

    public RequestOnSubscribe(VKRequest request) {
        this.request = request;
    }

    @Override
    public void call(final Subscriber<? super VKResponse> observer) {
        if (!observer.isUnsubscribed()) {
            request.executeWithListener(new VKRequest.VKRequestListener() {
                @Override
                public void onComplete(final VKResponse response) {
                    observer.onNext(response);
                    observer.onCompleted();
                }

                @Override
                public void onError(VKError error) {
                    observer.onError(new Throwable(error.toString()));
                }

                @Override
                public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                    //I don't really believe in progress
                }
            });
        }
    }
}
