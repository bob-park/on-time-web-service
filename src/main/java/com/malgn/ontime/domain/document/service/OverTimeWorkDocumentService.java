package com.malgn.ontime.domain.document.service;

import static org.apache.commons.lang3.math.NumberUtils.*;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.malgn.ontime.common.auth.AuthUtils;
import com.malgn.ontime.domain.approval.feign.ApprovalLineFeignClient;
import com.malgn.ontime.domain.approval.model.ApprovalLineResponse;
import com.malgn.ontime.domain.approval.model.SearchApprovalLineRequest;
import com.malgn.ontime.domain.document.fegin.DocumentFeignClient;
import com.malgn.ontime.domain.document.model.CreateOvertimeDocumentRequest;
import com.malgn.ontime.domain.document.model.DocumentApprovalHistoryResponse;
import com.malgn.ontime.domain.document.model.OverTimeWorkDocumentResponse;
import com.malgn.ontime.domain.team.model.TeamResponse;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.model.UserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class OverTimeWorkDocumentService {

    private final ApprovalLineFeignClient approvalLineClient;
    private final DocumentFeignClient documentClient;
    private final UserFeignClient userClient;

    public OverTimeWorkDocumentResponse createDocument(CreateOvertimeDocumentRequest createRequest) {
        return documentClient.createOverTimeWorkDocument(createRequest);
    }

    public OverTimeWorkDocumentResponse getDocument(long id) {

        OidcUser principal = (OidcUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uniqueId = AuthUtils.getUniqueId(principal);

        OverTimeWorkDocumentResponse document = documentClient.getOverTimeWorkTime(id);
        UserResponse user = userClient.getById(uniqueId);
        TeamResponse team = user.group();

        List<ApprovalLineResponse> approvalLine =
            approvalLineClient.getLines(
                SearchApprovalLineRequest.builder()
                    .teamId(toLong(team.id()))
                    .documentType("OVERTIME_WORK")
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

        return document.toBuilder()
            .user(user)
            .approvalHistories(newApprovalHistories)
            .build();
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
}
