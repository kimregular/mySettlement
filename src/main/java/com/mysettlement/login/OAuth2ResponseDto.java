package com.mysettlement.login;

public interface OAuth2ResponseDto {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();
}
