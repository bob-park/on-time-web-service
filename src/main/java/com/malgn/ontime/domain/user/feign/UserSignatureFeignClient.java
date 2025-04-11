package com.malgn.ontime.domain.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.malgn.ontime.domain.user.model.UserSignatureResponse;

@FeignClient(name = "on-time-e-work-api", contextId = "on-time-e-work-api-user-signature")
public interface UserSignatureFeignClient {

    @GetMapping(path = "api/v1/users/{uniqueId}/signature")
    ResponseEntity<Resource> getSignature(@PathVariable String uniqueId);

    @PostMapping(path = "api/v1/users/{uniqueId}/signature", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UserSignatureResponse updateSignature(@PathVariable String uniqueId, @RequestPart("signature") MultipartFile signature);

    @PostMapping(path = "api/v1/users/{uniqueId}/signature/reset")
    UserSignatureResponse resetSignature(@PathVariable String uniqueId);

}
