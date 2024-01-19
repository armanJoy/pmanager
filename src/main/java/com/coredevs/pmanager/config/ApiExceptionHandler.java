package com.coredevs.pmanager.config;

import com.coredevs.pmanager.model.view.ApiStatusVm;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@RestControllerAdvice
public class ApiExceptionHandler extends ExceptionHandlerExceptionResolver {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiStatusVm> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        String msg = "";
        if (ex.getAllErrors() != null && ex.getAllErrors().size() > 0) {
            for (ObjectError allError : ex.getAllErrors()) {
                msg = msg.concat(allError.getDefaultMessage());
            }
        }
        logger.error(msg, ex);
        return ResponseEntity
                .status(400)
                .body(new ApiStatusVm(msg, false, null));
    }

}
