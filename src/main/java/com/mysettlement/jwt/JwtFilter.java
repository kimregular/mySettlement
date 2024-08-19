package com.mysettlement.jwt;

import com.mysettlement.login.CustomOAuth2User;
import com.mysettlement.login.OAuth2UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
        }

        if (authorization == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization;

        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtil.getUsername(token);
        String memberRole = jwtUtil.getMemberRole(token);
        OAuth2UserDto oAuth2UserDto = new OAuth2UserDto(username, memberRole);
        CustomOAuth2User oAuth2User = new CustomOAuth2User(oAuth2UserDto);

        Authentication authToken = new UsernamePasswordAuthenticationToken(oAuth2User, null, oAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
