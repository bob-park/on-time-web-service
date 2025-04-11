package com.malgn.ontime.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.malgn.ontime.domain.user.model.UserSignatureResponse;
import com.malgn.ontime.domain.user.service.UserSignatureService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserSignatureController {

    private final UserSignatureService userSignatureService;

    @GetMapping(path = "{uniqueId}/signature")
    public ResponseEntity<Resource> getSignature(@PathVariable String uniqueId) {
        return userSignatureService.getSignature(uniqueId);
    }

    @PostMapping(path = "signature")
    public UserSignatureResponse updateSignature(@AuthenticationPrincipal OidcUser user,
        @RequestPart("signature") MultipartFile signature) {
        return userSignatureService.updateSignature(user, signature);
    }

    @PostMapping(path = "signature/reset")
    public UserSignatureResponse resetSignature(@AuthenticationPrincipal OidcUser user) {
        return userSignatureService.resetSignature(user);
    }

}
