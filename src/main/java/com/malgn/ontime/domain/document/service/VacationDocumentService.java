package com.malgn.ontime.domain.document.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.approval.feign.ApprovalLineFeignClient;
import com.malgn.ontime.domain.approval.feign.SearchApprovalLineRequest;
import com.malgn.ontime.domain.approval.model.ApprovalLineResponse;
import com.malgn.ontime.domain.document.fegin.DocumentFeignClient;
import com.malgn.ontime.domain.document.model.CreateVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.DocumentApprovalHistoryResponse;
import com.malgn.ontime.domain.document.model.SearchVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.VacationDocumentResponse;
import com.malgn.ontime.domain.team.model.TeamResponse;
import com.malgn.ontime.domain.user.feign.UserFeignClient;
import com.malgn.ontime.domain.user.feign.UserTeamFeignClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class VacationDocumentService {

    private final ApprovalLineFeignClient approvalLineClient;
    private final DocumentFeignClient documentClient;
    private final UserFeignClient userClient;
    private final UserTeamFeignClient userTeamClient;

    public VacationDocumentResponse createVacation(CreateVacationDocumentRequest createRequest) {
        return documentClient.createVacationDocumentResponse(createRequest);
    }

    public SimplePageImpl<VacationDocumentResponse> search(SearchVacationDocumentRequest searchRequest) {
        return documentClient.search(searchRequest);
    }

    public VacationDocumentResponse getDetail(Long id, String userUniqueId) {

        VacationDocumentResponse document = documentClient.getById(id);
        TeamResponse team = userTeamClient.getTeam(userUniqueId);

        List<ApprovalLineResponse> approvalLine =
            approvalLineClient.getLines(
                SearchApprovalLineRequest.builder()
                    .teamId(team.id())
                    .documentType("VACATION")
                    .build());

        List<ApprovalLineResponse> lines =
            approvalLine.isEmpty() ? List.of() : parseApprovalLineList(approvalLine.get(0));

        List<DocumentApprovalHistoryResponse> newApprovalHistories = new ArrayList<>();

        for (ApprovalLineResponse line : lines) {

            DocumentApprovalHistoryResponse history =
                document.getApprovalHistories().stream()
                    .filter(item -> item.line().id().equals(line.id()))
                    .findAny()
                    .orElse(
                        DocumentApprovalHistoryResponse.builder()
                            .line(line)
                            .build());

            newApprovalHistories.add(history);
        }

        return document.toBuilder()
            .approvalHistories(newApprovalHistories)
            .build();
    }

    private List<ApprovalLineResponse> parseApprovalLineList(ApprovalLineResponse approvalLine) {

        List<ApprovalLineResponse> result = new ArrayList<>();

        for (ApprovalLineResponse child : approvalLine.children()) {

            List<ApprovalLineResponse> childrenApprovalList = parseApprovalLineList(child);

            result.addAll(childrenApprovalList);
        }

        return result;
    }
}
