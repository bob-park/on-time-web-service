package com.malgn.ontime.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.user.feign.UserSignatureFeignClient;
import com.malgn.ontime.domain.user.model.UserSignatureResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSignatureService {

    private final UserSignatureFeignClient userSignatureClient;

    public ResponseEntity<Resource> getSignature(String userUniqueId) {
        return userSignatureClient.getSignature(userUniqueId);
    }

    public UserSignatureResponse updateSignature(OidcUser user, MultipartFile signature) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return userSignatureClient.updateSignature(uniqueId, signature);
    }

    public UserSignatureResponse resetSignature(OidcUser user) {
        String uniqueId = AuthUtils.getUniqueId(user);

        return userSignatureClient.resetSignature(uniqueId);
    }

}
