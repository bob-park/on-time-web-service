package com.malgn.ontime.configure.feign;

import static com.google.common.net.HttpHeaders.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import com.google.common.collect.Maps;

import feign.RequestInterceptor;

@RequiredArgsConstructor
@Configuration
public class FeignConfiguration {

    private static final String AUTHORIZED_CLIENT_NAME = "keyflow-auth";

    private final OAuth2AuthorizedClientService authorizedClientService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.headers(getRequestHeaders());
    }

    private Map<String, Collection<String>> getRequestHeaders() {
        Map<String, Collection<String>> headers = Maps.newHashMap();

        OAuth2AuthenticationToken authentication =
            (OAuth2AuthenticationToken)SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getClass().isAssignableFrom(AnonymousAuthenticationToken.class)) {
            throw new OAuth2AuthenticationException("unauthorized");
        }

        OAuth2AuthorizedClient client =
            authorizedClientService.loadAuthorizedClient(AUTHORIZED_CLIENT_NAME, authentication.getName());

        if (client == null) {
            throw new OAuth2AuthenticationException("unauthorized");
        }

        String authorizationHeader = "Bearer " + client.getAccessToken().getTokenValue();

        headers.put(AUTHORIZATION, Collections.singletonList(authorizationHeader));

        return headers;
    }

}
