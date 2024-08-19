package com.mysettlement.login;

import java.util.Map;


public class NaverResponse implements OAuth2ResponseDto {

    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> userAttrubutes) {
        this.attribute = (Map<String, Object>) userAttrubutes.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
