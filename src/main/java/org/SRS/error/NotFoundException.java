package org.SRS.error;

import org.springframework.http.HttpStatus;

public class NotFoundException extends APIBaseException{

    public NotFoundException(String message){
        super(message);

    }
@Override
public HttpStatus getHttpStatus(){
return HttpStatus.NOT_FOUND;
}
}
