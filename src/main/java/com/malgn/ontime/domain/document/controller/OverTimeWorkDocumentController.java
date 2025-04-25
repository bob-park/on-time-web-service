package com.malgn.ontime.domain.document.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.document.model.CreateOvertimeDocumentRequest;
import com.malgn.ontime.domain.document.model.OverTimeWorkDocumentResponse;
import com.malgn.ontime.domain.document.service.OverTimeWorkDocumentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("documents/overtimes")
public class OverTimeWorkDocumentController {

    private final OverTimeWorkDocumentService documentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public OverTimeWorkDocumentResponse createDocument(@AuthenticationPrincipal OidcUser user,
        @RequestBody CreateOvertimeDocumentRequest createRequest) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return documentService.createDocument(
            createRequest.toBuilder()
                .userUniqueId(uniqueId)
                .build());
    }

}
