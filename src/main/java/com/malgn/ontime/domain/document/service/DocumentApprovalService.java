package com.malgn.ontime.domain.document.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.malgn.ontime.domain.approval.feign.ApprovalLineFeignClient;
import com.malgn.ontime.domain.approval.model.ApprovalLineResponse;
import com.malgn.ontime.domain.approval.model.SearchApprovalLineRequest;
import com.malgn.ontime.domain.document.fegin.DocumentFeignClient;
import com.malgn.ontime.domain.document.model.DocumentApprovalHistoryResponse;
import com.malgn.ontime.domain.document.model.DocumentResponse;
import com.malgn.ontime.domain.document.model.RejectDocumentRequest;
import com.malgn.ontime.domain.document.model.SearchDocumentApprovalHistoryRequest;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentApprovalService {

    private final ApprovalLineFeignClient approvalLineClient;
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

    public DocumentApprovalHistoryResponse getById(long id) {

        DocumentApprovalHistoryResponse result = documentClient.getApproval(id);

        DocumentResponse document = getDocumentDetail(result.document().getType(), result.document().getId());
        UserResponse user = userClient.getById(document.getUserUniqueId());

        List<ApprovalLineResponse> approvalLine =
            approvalLineClient.getLines(
                SearchApprovalLineRequest.builder()
                    .teamId(user.team().id())
                    .documentType(document.getType())
                    .build());

        List<ApprovalLineResponse> lines =
            approvalLine.isEmpty() ? List.of() : parseApprovalLineList(approvalLine.get(0));

        List<DocumentApprovalHistoryResponse> newApprovalHistories = new ArrayList<>();

        for (ApprovalLineResponse line : lines) {

            DocumentApprovalHistoryResponse history =
                document.getApprovalHistories().stream()
                    .filter(item -> item.approvalLine().id().equals(line.id()))
                    .findAny()
                    .orElse(
                        DocumentApprovalHistoryResponse.builder()
                            .approvalLine(line)
                            .build());

            newApprovalHistories.add(history);
        }

        return result.toBuilder()
            .document(
                document.toBuilder()
                    .user(userClient.getById(document.getUserUniqueId()))
                    .approvalHistories(newApprovalHistories)
                    .build())
            .build();
    }

    public DocumentApprovalHistoryResponse approve(long approvalId) {
        return documentClient.approveDocument(approvalId);
    }

    public DocumentApprovalHistoryResponse reject(long approvalId, RejectDocumentRequest rejectRequest) {
        return documentClient.rejectDocument(approvalId, rejectRequest);
    }

    private List<ApprovalLineResponse> parseApprovalLineList(ApprovalLineResponse approvalLine) {

        List<ApprovalLineResponse> result = new ArrayList<>();

        result.add(approvalLine);

        for (ApprovalLineResponse child : approvalLine.children()) {

            List<ApprovalLineResponse> childrenApprovalList = parseApprovalLineList(child);

            result.addAll(childrenApprovalList);
        }

        return result;
    }

    private DocumentResponse getDocumentDetail(String type, long id) {

        switch (type) {
            case "VACATION" -> {
                return documentClient.getVacationById(id);
            }
            case "OVERTIME_REPORT" -> {
                return documentClient.getOverTimeWorkTime(id);
            }
            default -> {
                return null;
            }

        }

    }

}
