package com.example.saturn.utils;

import com.example.saturn.models.Account;
import com.example.saturn.models.requests.ApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value={BindException.class})
    public ResponseEntity<ApiResponse> otherErrorHandle(BindException e) {
        return new ResponseEntity(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getBindingResult().getFieldError().getDefaultMessage(),
                List.of()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
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
        System.out.println(exception);
        String message = exception.getBindingResult().getFieldError().getField()+" "+ exception.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST,
                message,
                List.of()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> nullErrorHandle(NullPointerException exception) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.NOT_FOUND,
                exception.getMessage(),
                List.of()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ApiResponse> invalidFormatHandle(InvalidFormatException exception) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                List.of()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> DateTimeParseHandle(DateTimeParseException exception) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                List.of()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> ResponseStatusHandle(ResponseStatusException exception){
        return new ResponseEntity<ApiResponse>(new ApiResponse(exception.getStatus(),
                exception.getMessage(),
                List.of()),exception.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse> IllegalArgumentHandle(IllegalArgumentException exception) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                List.of()),HttpStatus.BAD_REQUEST);
    }




}
