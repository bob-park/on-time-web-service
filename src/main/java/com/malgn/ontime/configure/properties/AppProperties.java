package com.malgn.ontime.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public record AppProperties(String loginSuccessUrl) {
}
