package com.malgn.ontime.common.auth;

import java.util.Map;

import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface AuthUtils {

    String PROFILE_UNIQUE_ID = "uniqueId";
    String PROFILE_USER_ID = "userId";
    String PROFILE_USERNAME = "username";
    String PROFILE_ROLE = "role";

    static String getUniqueId(OidcUser user) {

        Map<String, Object> profile = user.getUserInfo().getClaim(OidcScopes.PROFILE);

        return String.valueOf(profile.get(PROFILE_UNIQUE_ID));

    }
}
