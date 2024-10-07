package com.mysettlement.global.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /*
        동작 순서는 위에서부터다 -> 서순을 주의할것
        requestMatchers("/path/**") : 해당하는 경로의 요청이 왔을경우
        anyRequest() : 그외 다른 모든 경로의 요청이 왔을 경우
         */

        /*
        permitAll() : 로그인을 하지 않아도 모든 사용자가 접근 가능
        authenticated() : 로그인을 완료한 사용자만 접근 가능
        hasRole() : 특정 role을 만족하는 사용자만 접근 가능
        denyAll() : 로그인을 완료 했더라도 모든 사용자가 접근 불가능
         */
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
        );

        return http.build();
    }
}
