package org.SRS.user;


import org.SRS.user.validator.GenderEnum;
import org.SRS.user.validator.ValidEmailDomain;
import org.SRS.user.validator.ValidPassword;
import org.SRS.user.validator.ValidUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@ValidPassword
public class CreateUserDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be 3-20 characters")
    @ValidUsername
    private String username;


    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")

    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;


    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @ValidEmailDomain
    private String email;

    @NotNull(message = "Role is required")
    private Integer roleId;

    @NotNull(message = "Gender is required")
    private Integer gender;

    @NotNull(message = "Birthday is required")
    private LocalDate birthday;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String username, String password, String confirmPassword,
                         String email, Integer roleId, int gender, LocalDate birthday) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.roleId = roleId;
        this.gender = gender;
        this.birthday = birthday;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


}
