package com.malgn.ontime.domain.document.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.document.model.CreateVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.DocumentApprovalHistoryResponse;
import com.malgn.ontime.domain.document.model.RejectDocumentRequest;
import com.malgn.ontime.domain.document.model.SearchDocumentApprovalHistoryRequest;
import com.malgn.ontime.domain.document.model.SearchVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.VacationDocumentResponse;

@FeignClient(name = "on-time-e-work-api", contextId = "on-time-e-work-api-document")
public interface DocumentFeignClient {

    /*
     * vacation
     */
    @PostMapping(path = "api/v1/documents/vacations")
    VacationDocumentResponse createVacationDocumentResponse(@RequestBody CreateVacationDocumentRequest createRequest);

    @GetMapping(path = "api/v1/documents/vacations")
    SimplePageImpl<VacationDocumentResponse> search(@SpringQueryMap SearchVacationDocumentRequest searchRequest);

    @GetMapping(path = "api/v1/documents/vacations/{id}")
    VacationDocumentResponse getById(@PathVariable("id") Long id);

    /*
     * approval
     */
    @GetMapping(path = "api/v1/documents/approval")
    SimplePageImpl<DocumentApprovalHistoryResponse> searchApproval(
        @SpringQueryMap SearchDocumentApprovalHistoryRequest searchRequest);

    @PostMapping(path = "api/v1/documents/approval/{approvalId}")
    DocumentApprovalHistoryResponse approveDocument(@PathVariable Long approvalId);

    @PostMapping(path = "api/v1/documents/approval/{approvalId}/reject")
    DocumentApprovalHistoryResponse rejectDocument(@PathVariable Long approvalId, @RequestBody RejectDocumentRequest rejectRequest);
}
