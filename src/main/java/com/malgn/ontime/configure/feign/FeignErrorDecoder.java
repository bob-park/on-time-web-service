package com.malgn.ontime.configure.feign;

import static org.apache.commons.lang3.ObjectUtils.*;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.Response.Body;
import feign.codec.ErrorDecoder;

import com.malgn.common.exception.NotFoundException;
import com.malgn.common.exception.ServiceUnavailableException;
import com.malgn.common.model.ApiResult;

@Slf4j
@RequiredArgsConstructor
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        String errorMsg = null;

        Body body = response.body();
        ApiResult<?> apiResult = null;

        if (body != null) {
            try {
                apiResult = objectMapper.readValue(body.asInputStream(), ApiResult.class);
            } catch (IOException e) {
                log.warn("Response parse error - {}", e.getMessage());
            }
        }

        if (apiResult != null && isNotEmpty(apiResult.getError())) {
            errorMsg = apiResult.getError().getMessage();
        }

        switch (response.status()) {
            case 400 -> {
                return new IllegalArgumentException(errorMsg);
            }

            case 401 -> {
                return new AuthenticationServiceException(errorMsg);
            }

            case 403 -> {
                return new AccessDeniedException(errorMsg);
            }

            case 404 -> {
                return new NotFoundException();
            }

            case 503 -> {
                return new ServiceUnavailableException();
            }

            default -> {
                return new Exception(errorMsg);
            }
        }

    }
}
