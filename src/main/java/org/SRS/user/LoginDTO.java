package org.SRS.user;

public class LoginDTO {
    private String username;
    private String Password;


    public LoginDTO(String username,String password){
        this.username=username;
        this.Password=password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
