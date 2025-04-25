package com.malgn.ontime.domain.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.malgn.ontime.domain.document.fegin.DocumentFeignClient;
import com.malgn.ontime.domain.document.model.CreateOvertimeDocumentRequest;
import com.malgn.ontime.domain.document.model.OverTimeWorkDocumentResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class OverTimeWorkDocumentService {

    private final DocumentFeignClient documentClient;

    public OverTimeWorkDocumentResponse createDocument(CreateOvertimeDocumentRequest createRequest) {
        return documentClient.createOverTimeWorkDocument(createRequest);
    }

}
