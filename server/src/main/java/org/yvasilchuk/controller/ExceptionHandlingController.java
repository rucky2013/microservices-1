package org.yvasilchuk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.exceptions.FatException;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(FatException.class)
    @ResponseBody
    public ResponseEntity<BaseResponse> handleApplicationException(FatException exception) {
        BaseResponse response = new BaseResponse();
        response.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
