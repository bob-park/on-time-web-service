package com.malgn.ontime.domain.user.service;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.document.fegin.DocumentFeignClient;
import com.malgn.ontime.domain.document.model.DocumentApprovalHistoryResponse;
import com.malgn.ontime.domain.document.model.SearchDocumentApprovalHistoryRequest;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.feign.UserLeaveEntryFeignClient;
import com.malgn.ontime.domain.user.model.SearchUserRequest;
import com.malgn.ontime.domain.user.model.UpdateUserPasswordRequest;
import com.malgn.ontime.domain.user.model.UserLeaveEntryResponse;
import com.malgn.ontime.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserFeignClient userClient;
    private final UserLeaveEntryFeignClient userLeaveEntryClient;
    private final DocumentFeignClient documentClient;

    public UserResponse me(OidcUser user) {

        String uniqueId = AuthUtils.getUniqueId(user);

        UserResponse result = userClient.getById(uniqueId);
        UserLeaveEntryResponse leaveEntry = userLeaveEntryClient.getLeaveEntry(uniqueId, LocalDate.now().getYear());

        SimplePageImpl<DocumentApprovalHistoryResponse> documents =
            documentClient.searchApproval(
                SearchDocumentApprovalHistoryRequest.builder()
                    .userUniqueId(uniqueId)
                    .status("WAITING")
                    .build(),
                PageRequest.of(0, 100));

        return result.toBuilder()
            .leaveEntry(leaveEntry)
            .proceedingDocumentsCount(documents.total())
            .build();
    }

    public Page<UserResponse> getUsers(SearchUserRequest searchRequest, Pageable pageable) {
        SimplePageImpl<UserResponse> users = userClient.getUsers(searchRequest, pageable);

        Page<UserResponse> page = users.toPage();

        return page.map(item -> userClient.getById(item.uniqueId()));
    }

    public UserResponse getUser(String uniqueId) {
        return userClient.getById(uniqueId);
    }

    public ResponseEntity<Resource> getUserAvatar(String uniqueId) {
        return userClient.getUserAvatar(uniqueId);
    }

    public UserResponse updateUserAvatar(OidcUser user, MultipartFile avatar) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return userClient.updateUserAvatar(uniqueId, avatar);
    }

    public UserResponse resetUserAvatar(OidcUser user) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return userClient.resetUserAvatar(uniqueId);
    }

    public UserResponse updatePassword(OidcUser user, UpdateUserPasswordRequest updateRequest) {

        String uniqueId = AuthUtils.getUniqueId(user);

        return userClient.updatePassword(uniqueId, updateRequest);
    }

}
