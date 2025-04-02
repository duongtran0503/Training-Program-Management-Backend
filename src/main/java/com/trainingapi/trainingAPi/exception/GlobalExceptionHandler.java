package com.trainingapi.trainingAPi.exception;

import com.trainingapi.trainingAPi.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<?>> handlingRuntimeException(Exception exception) {
        log.error("Exception: ", exception);
        ApiResponse<?> apiResponse = new ApiResponse<>();

        apiResponse.setStatusCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getStatusCode().value());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        apiResponse.setSuccess(ErrorCode.UNCATEGORIZED_EXCEPTION.isSuccess());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handlingAppException(AppException e) {
         log.error("Exception:",e);

         ApiResponse<?> apiResponse = new ApiResponse<>();
         apiResponse.setSuccess(e.getErrorCode().isSuccess());
         apiResponse.setMessage(e.getErrorCode().getMessage());
         apiResponse.setStatusCode(e.getErrorCode().getStatusCode().value());

         return  ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }


}
