package com.malgn.ontime.configure;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.malgn.ontime.configure.properties.AppProperties;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfiguration {

    private final AppProperties properties;
}
