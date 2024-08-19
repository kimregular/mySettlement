package com.mysettlement.login;

import com.mysettlement.jwt.JwtUtil;
import com.mysettlement.member.entity.MemberRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String username = customOAuth2User.getName();

        MemberRole memberRole = null;
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String roleName = authority.getAuthority();
            try {
                memberRole = MemberRole.valueOf(roleName);
            } catch (IllegalArgumentException illegalArgumentException) {
                log.error("login role match error : {}", illegalArgumentException);
            }
        }

        String token = jwtUtil.createJwt(username, memberRole);

        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("http://localhost:8080");
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
