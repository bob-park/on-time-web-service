package com.malgn.ontime.domain.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.document.fegin.DocumentFeignClient;
import com.malgn.ontime.domain.document.model.DocumentResponse;
import com.malgn.ontime.domain.document.model.SearchDocumentRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentService {

    private final DocumentFeignClient documentClient;

    public SimplePageImpl<DocumentResponse> search(SearchDocumentRequest searchRequest, Pageable pageable) {
        return documentClient.getDocuments(searchRequest, pageable);
    }

    public DocumentResponse cancel(long id) {
        return documentClient.cancelDocument(id);
    }

}
