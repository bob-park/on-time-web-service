package com.malgn.ontime.domain.document.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.document.model.DocumentResponse;
import com.malgn.ontime.domain.document.model.SearchDocumentRequest;
import com.malgn.ontime.domain.document.service.DocumentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("documents")
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping(path = "")
    public Page<DocumentResponse> search(@AuthenticationPrincipal OidcUser user, SearchDocumentRequest searchRequest,
        Pageable pageable) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return documentService.search(
                searchRequest.toBuilder()
                    .userUniqueId(uniqueId)
                    .build(),
                pageable)
            .toPage();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id:\\d+}/cancel")
    public DocumentResponse cancel(@PathVariable Long id) {
        return documentService.cancel(id);
    }
}
