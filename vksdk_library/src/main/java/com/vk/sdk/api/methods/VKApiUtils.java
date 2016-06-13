package com.vk.sdk.api.methods;

import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;

public class VKApiUtils extends VKApiBase {

    public VKRequest resolveScreenName(VKParameters methodParameters) {
        return prepareRequest("resolveScreenName", methodParameters);
    }

    @Override
    protected String getMethodsGroup() {
        return "utils";
    }
}
