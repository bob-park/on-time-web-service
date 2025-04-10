package com.malgn.ontime.domain.document.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.document.model.CreateVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.SearchVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.VacationDocumentResponse;
import com.malgn.ontime.domain.document.service.VacationDocumentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("documents/vacations")
public class VacationDocumentController {

    private final VacationDocumentService documentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "")
    public VacationDocumentResponse createDocument(@AuthenticationPrincipal OidcUser user,
        @RequestBody CreateVacationDocumentRequest createRequest) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return documentService.createVacation(
            createRequest.toBuilder()
                .userUniqueId(uniqueId)
                .build());
    }

    @GetMapping(path = "")
    public Page<VacationDocumentResponse> search(@AuthenticationPrincipal OidcUser user,
        SearchVacationDocumentRequest searchRequest, @PageableDefault Pageable pageable) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return documentService.search(
            searchRequest.toBuilder()
                .userUniqueId(uniqueId)
                .build(),
            pageable);
    }

    @GetMapping(path = "{id:\\d+}")
    public VacationDocumentResponse getDocument(@AuthenticationPrincipal OidcUser user, @PathVariable Long id) {
        String uniqueId = AuthUtils.getUniqueId(user);

        return documentService.getDetail(id, uniqueId);
    }
}
