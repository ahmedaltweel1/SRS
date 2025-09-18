package org.SRS.error;

import org.springframework.http.HttpStatus;

public class UserOrEmailAlreadyExistsException extends APIBaseException{

    public UserOrEmailAlreadyExistsException(String message){
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus(){
        return HttpStatus.CONFLICT;
    }

}
