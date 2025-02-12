package com.malgn.ontime.configure.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.malgn.ontime.configure.properties.AppProperties;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class OAuth2ClientConfiguration {

    private final AppProperties properties;

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientRepository authorizedClientRepository;

    @Bean
    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            requests ->
                requests.anyRequest().authenticated());

        http.oauth2Login(oauth2login ->
            oauth2login
                .failureHandler((request, response, exception) -> {
                    log.error("Authorized Exception - {}", exception.getMessage(), exception);
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
                })
                .successHandler(successHandler()));

        http.exceptionHandling(exception ->
            exception.authenticationEntryPoint((request, response, authException) ->
                response.sendError(HttpStatus.UNAUTHORIZED.value(), authException.getMessage())
            ));

        http.formLogin(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.oauth2Client(Customizer.withDefaults());

        http.logout(logout ->
            logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl(properties.logoutSuccessUrl())
                .invalidateHttpSession(true)
                .clearAuthentication(true));

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public DefaultOAuth2AuthorizedClientManager authorizedClientManager() {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
            OAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode()
                .clientCredentials()
                .refreshToken()
                .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
            new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> response.sendRedirect(properties.loginSuccessUrl());
    }

    @Bean
    public LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            authorizedClientRepository.removeAuthorizedClient("keyflow-auth", authentication, request, response);
        };
    }
}
