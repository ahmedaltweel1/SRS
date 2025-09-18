package org.SRS.error;

import org.springframework.http.HttpStatus;

public class OtherException extends APIBaseException{

    public OtherException(String message){
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
