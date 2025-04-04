package com.malgn.ontime.domain.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.malgn.ontime.domain.document.fegin.DocumentFeignClient;
import com.malgn.ontime.domain.document.model.DocumentApprovalHistoryResponse;
import com.malgn.ontime.domain.document.model.DocumentResponse;
import com.malgn.ontime.domain.document.model.SearchDocumentApprovalHistoryRequest;
import com.malgn.ontime.domain.user.feign.UserFeignClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentApprovalService {

    private final DocumentFeignClient documentClient;
    private final UserFeignClient userClient;

    public Page<DocumentApprovalHistoryResponse> search(SearchDocumentApprovalHistoryRequest searchRequest,
        Pageable pageable) {

        Page<DocumentApprovalHistoryResponse> result = documentClient.searchApproval(searchRequest, pageable).toPage();

        return result.map(
            item -> {

                DocumentResponse document = item.document();

                return item.toBuilder()
                    .document(
                        document.toBuilder()
                            .user(userClient.getById(document.getUserUniqueId()))
                            .build())
                    .build();
            });

    }

}
