package org.SRS.error;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorDetails {
    private String message;
    private String path;

    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern="dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private HttpStatus statusCode;

    public ErrorDetails(){
        this.timestamp =new Date();
    }

    public ErrorDetails(String m,String p,HttpStatus statusCode){
        this();
        this.message=m;
        this.path=p;
        this.statusCode=statusCode;

    }
    public void setMessage(String m){
        this.message=m;
    }
    public void setPath(String p){
        this.path=p;
    }public void setTimestamp(Date t){
        this.timestamp=t;
    }
    public String getMessage(){
        return this.message;
    }public String getPath(){
        return this.path;
    }public Date getTimestamp(){
        return this.timestamp;
    }

    public void setStatusCode(HttpStatus s){
        this.statusCode=s;
    }public HttpStatus getStatuscode(){
        return this.statusCode;
    }
}

