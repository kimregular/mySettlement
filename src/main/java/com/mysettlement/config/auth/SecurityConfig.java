package com.mysettlement.config.auth;

import com.mysettlement.member.entity.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(headerConfig -> headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest.requestMatchers("/member")
                        .hasRole(MemberRole.DEFAULT.name())
                        .requestMatchers("/")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .logout( // 로그아웃 성공 시 / 주소로 이동
                        logoutConfig -> logoutConfig.logoutSuccessUrl("/"))
                // OAuth2 로그인 기능에 대한 여러 설정
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
