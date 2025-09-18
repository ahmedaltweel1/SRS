package org.SRS.user.validator;

import org.SRS.error.OtherException;
import org.SRS.user.CreateUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<ValidPassword, CreateUserDTO> {

    @Override
    public boolean isValid(CreateUserDTO dto, ConstraintValidatorContext context){
        if(dto.getPassword()==null || dto.getPassword().length()<8 || dto.getConfirmPassword()==null) throw new OtherException("Password must be greater than eigtht characters");
        if (!dto.getPassword().equals(dto.getConfirmPassword())) throw new OtherException("The password and ConfirmPassword not match");
        if (!dto.getPassword().matches(".*[a-z].*")) throw new OtherException("Weak PASSWORD,The password must contains a small letter");
        if (!dto.getPassword().matches(".*[A-Z].*")) throw new OtherException("Weak PASSWORD,,The password must contains Capital letter");
        if (!dto.getPassword().matches(".*\\d.*")) throw new OtherException("Weak PASSWORD,,The password must contains Number");
        if (!dto.getPassword().matches(".*[!@#$%^&*()_+].*")) throw new OtherException("Weak PASSWORD,The password must contains a [!@#$%^&*()_+] ");
        return true;
    }

}
