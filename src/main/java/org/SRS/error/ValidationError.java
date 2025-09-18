package org.SRS.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError {

    private List<String> errors;
    private String path;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ValidationError(){
        this.timestamp=new Date();
        this.errors=new ArrayList<>();
    }

public void addError(String error){
        this.errors.add(error);
}

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public void setErrors(List<String> errors){
        this.errors=errors;
    }

    public String getPath() {
        return path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors(){
        return this.errors;
    }
}
