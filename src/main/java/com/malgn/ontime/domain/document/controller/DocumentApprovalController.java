package com.malgn.ontime.domain.document.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.document.model.DocumentApprovalHistoryResponse;
import com.malgn.ontime.domain.document.model.SearchDocumentApprovalHistoryRequest;
import com.malgn.ontime.domain.document.service.DocumentApprovalService;

@RequiredArgsConstructor
@RestController
@RequestMapping("documents/approval")
public class DocumentApprovalController {

    private final DocumentApprovalService documentApprovalService;

    @GetMapping(path = "")
    public Page<DocumentApprovalHistoryResponse> search(@AuthenticationPrincipal OidcUser user,
        SearchDocumentApprovalHistoryRequest searchRequest, Pageable pageable) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return documentApprovalService.search(
            searchRequest.toBuilder()
                .userUniqueId(uniqueId)
                .build(),
            pageable);
    }

    @GetMapping(path = "{approvalId}")
    public DocumentApprovalHistoryResponse getApproval(@PathVariable Long approvalId) {
        return documentApprovalService.getById(approvalId);
    }
}
