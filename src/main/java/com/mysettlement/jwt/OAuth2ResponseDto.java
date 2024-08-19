package com.mysettlement.jwt;

public interface OAuth2ResponseDto {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();
}
