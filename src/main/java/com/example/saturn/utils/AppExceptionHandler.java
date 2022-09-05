package com.example.saturn.utils;

import com.example.saturn.models.Account;
import com.example.saturn.models.requests.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler {
//    @ExceptionHandler(value={ApiRequestException.class})
//    public ApiResponse<?> globalExceptionHandle(ApiRequestException e) {
//
////        ApiException exception = new ApiException(
////                e.getMessage(),
////                e,
////                HttpStatus.BAD_REQUEST,
////                ZonedDateTime.now(ZoneId.of("Z")));
//        return new ApiResponse<>(e.getStatus().value(),e.getMessage(), List.of());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> customErrorHandle(MethodArgumentNotValidException exception) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST,
                exception.getBindingResult().getFieldError().getDefaultMessage(),
                List.of()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> nullErrorHandle(NullPointerException exception) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.NOT_FOUND,
                exception.getMessage(),
                List.of()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> IllegalArgumentHandle(IllegalArgumentException exception) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                List.of()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ApiResponse> otherErrorHandle(Exception e) {
        return new ResponseEntity(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                List.of()),HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
