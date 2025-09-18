package org.SRS.error;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiHandleException  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(APIBaseException.class)
    public ResponseEntity<ErrorDetails> handleApiException(APIBaseException ex, WebRequest request){
ErrorDetails details= new ErrorDetails(ex.getMessage(), request.getDescription(false),ex.getHttpStatus());

 return new ResponseEntity<>(details,ex.getHttpStatus());
    }

@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders header, HttpStatus status, WebRequest request){
ValidationError validationError =new ValidationError();
validationError.setPath(request.getDescription(false));
List<FieldError> fieldErrorList= ex.getBindingResult().getFieldErrors();

for(FieldError f:fieldErrorList){
validationError.addError(f.getDefaultMessage());
}

return new ResponseEntity<>(validationError,HttpStatus.BAD_REQUEST);




}

}
