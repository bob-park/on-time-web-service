package com.malgn.ontime.domain.user.service;

import java.time.LocalDate;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import com.malgn.common.model.SimplePageImpl;
import com.malgn.ontime.domain.document.fegin.DocumentFeignClient;
import com.malgn.ontime.domain.document.model.SearchVacationDocumentRequest;
import com.malgn.ontime.domain.document.model.VacationDocumentResponse;
import com.malgn.ontime.domain.user.feign.UserEmploymentFeignClient;
import com.malgn.ontime.domain.user.model.SearchUserEmploymentRequest;
import com.malgn.ontime.domain.user.model.SearchUserLeaveEntryRequest;
import com.malgn.ontime.domain.user.model.UsedVacationResponse;
import com.malgn.ontime.domain.user.model.UserEmploymentResponse;
import com.malgn.ontime.domain.user.model.UserUsedVacationResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserUsedVacationService {

    private final DocumentFeignClient documentClient;
    private final UserEmploymentFeignClient userEmploymentClient;

    public List<UserUsedVacationResponse> getAll(SearchUserLeaveEntryRequest searchRequest) {

        List<UserUsedVacationResponse> result = Lists.newArrayList();

        SimplePageImpl<UserEmploymentResponse> userEmployments =
            userEmploymentClient.search(
                SearchUserEmploymentRequest.builder()
                    .build(),
                PageRequest.of(0, 100));

        for (UserEmploymentResponse userEmployment : userEmployments.content()) {

            List<VacationDocumentResponse> usedVacations =
                documentClient.searchVacation(
                    SearchVacationDocumentRequest.builder()
                        .userUniqueId(userEmployment.userUniqueId())
                        .startDateFrom(LocalDate.of(searchRequest.year(), 1, 1))
                        .endDateTo(LocalDate.of(searchRequest.year(), 12, 31))
                        .status("APPROVED")
                        .build(),
                    PageRequest.of(0, 100)).content();

            List<UsedVacationResponse> used = parseUsedVacations(usedVacations);

            result.add(
                UserUsedVacationResponse.builder()
                    .userUniqueId(userEmployment.userUniqueId())
                    .year(searchRequest.year())
                    .usedVacations(used)
                    .build());

        }

        return result;
    }

    private List<UsedVacationResponse> parseUsedVacations(List<VacationDocumentResponse> usedVacations) {

        List<UsedVacationResponse> result = Lists.newArrayList();

        for (VacationDocumentResponse usedVacation : usedVacations) {

            int month = usedVacation.getStartDate().getMonth().getValue();

            UsedVacationResponse findItem =
                result.stream()
                    .filter(item -> item.getMonth() == month)
                    .findAny()
                    .orElseGet(() -> {
                        UsedVacationResponse item =
                            UsedVacationResponse.builder()
                                .month(month)
                                .build();

                        result.add(item);

                        return item;
                    });

            findItem.add(usedVacation.getUsedDays());

        }

        return result;

    }

}
