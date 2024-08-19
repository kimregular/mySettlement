package com.mysettlement.login;

import com.mysettlement.member.entity.Member;
import com.mysettlement.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2User = {}", oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2ResponseDto oAuth2Response = getResponse(registrationId, oAuth2User);

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        Member foundMember = memberRepository.findByName(username).orElseGet(
                () -> memberRepository.save(Member.of(oAuth2Response))
        );

        return new CustomOAuth2User(OAuth2UserDto.of(foundMember));
    }

    private OAuth2ResponseDto getResponse(String id, OAuth2User user) {
        if (id.equals("naver")) return new NaverResponse(user.getAttributes());
        return new GoogleResponse(user.getAttributes());
    }
}
