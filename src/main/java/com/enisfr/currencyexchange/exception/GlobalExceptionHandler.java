package com.enisfr.currencyexchange.exception;

import static com.enisfr.currencyexchange.constant.GlobalConstants.ErrorMessages.DATE_FORMAT_ERROR_MSG;
import static com.enisfr.currencyexchange.constant.GlobalConstants.ErrorMessages.DEFAULT_ERROR_MSG;

import com.enisfr.currencyexchange.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  @ResponseBody
  public ResponseEntity<Object> handleDateFormatException(RuntimeException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    if (((MethodArgumentTypeMismatchException) ex).getName().equals("date")) {
      errorResponse.setMessage(DATE_FORMAT_ERROR_MSG);
      return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    errorResponse.setMessage(DEFAULT_ERROR_MSG);
    return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({GlobalException.class})
  @ResponseBody
  public ResponseEntity<Object> handleAllExceptions(RuntimeException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(DEFAULT_ERROR_MSG);
    return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

}
