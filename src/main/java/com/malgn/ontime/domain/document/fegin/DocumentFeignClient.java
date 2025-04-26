package com.malgn.ontime.domain.document.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.document.model.CreateOvertimeDocumentRequest;
import com.malgn.ontime.domain.document.model.CreateVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.DocumentApprovalHistoryResponse;
import com.malgn.ontime.domain.document.model.DocumentResponse;
import com.malgn.ontime.domain.document.model.OverTimeWorkDocumentResponse;
import com.malgn.ontime.domain.document.model.OverTimeWorkTimeResponse;
import com.malgn.ontime.domain.document.model.RejectDocumentRequest;
import com.malgn.ontime.domain.document.model.SearchDocumentApprovalHistoryRequest;
import com.malgn.ontime.domain.document.model.SearchDocumentRequest;
import com.malgn.ontime.domain.document.model.SearchVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.VacationDocumentResponse;

@FeignClient(name = "on-time-e-work-api", contextId = "on-time-e-work-api-document")
public interface DocumentFeignClient {

    /*
     * documents
     */
    @GetMapping(path = "api/v1/documents")
    SimplePageImpl<DocumentResponse> getDocuments(@SpringQueryMap SearchDocumentRequest searchRequest,
        Pageable pageable);

    @PostMapping(path = "api/v1/documents/{id}/request")
    DocumentResponse requestDocument(@PathVariable("id") Long id);

    @DeleteMapping(path = "api/v1/documents/{id}/cancel")
    DocumentResponse cancelDocument(@PathVariable("id") Long id);

    /*
     * vacation
     */
    @PostMapping(path = "api/v1/documents/vacations")
    VacationDocumentResponse createVacationDocumentResponse(@RequestBody CreateVacationDocumentRequest createRequest);

    @GetMapping(path = "api/v1/documents/vacations")
    SimplePageImpl<VacationDocumentResponse> searchVacation(@SpringQueryMap SearchVacationDocumentRequest searchRequest,
        Pageable pageable);

    @GetMapping(path = "api/v1/documents/vacations/{id}")
    VacationDocumentResponse getVacationById(@PathVariable("id") Long id);

    /*
     * overtime work document
     */
    @PostMapping(path = "api/v1/documents/overtimes")
    OverTimeWorkDocumentResponse createOverTimeWorkDocument(
        @RequestBody CreateOvertimeDocumentRequest createRequest);

    @GetMapping(path = "api/v1/documents/overtimes/{id:\\d+}")
    OverTimeWorkDocumentResponse getOverTimeWorkTime(@PathVariable long id);

    /*
     * approval
     */
    @GetMapping(path = "api/v1/documents/approval")
    SimplePageImpl<DocumentApprovalHistoryResponse> searchApproval(
        @SpringQueryMap SearchDocumentApprovalHistoryRequest searchRequest, Pageable pageable);

    @GetMapping(path = "api/v1/documents/approval/{approvalId}")
    DocumentApprovalHistoryResponse getApproval(@PathVariable Long approvalId);

    @PostMapping(path = "api/v1/documents/approval/{approvalId}")
    DocumentApprovalHistoryResponse approveDocument(@PathVariable Long approvalId);

    @PostMapping(path = "api/v1/documents/approval/{approvalId}/reject")
    DocumentApprovalHistoryResponse rejectDocument(@PathVariable Long approvalId,
        @RequestBody RejectDocumentRequest rejectRequest);
}
