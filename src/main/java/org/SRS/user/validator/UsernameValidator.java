package org.SRS.user.validator;

import org.SRS.error.OtherException;
import org.SRS.error.UserOrEmailAlreadyExistsException;
import org.SRS.user.UserRepository;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    private final UserRepository userRepository;

    public UsernameValidator(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username ==null) return false;
        if (!username.matches("^[A-Za-z0-9._]+$")) throw new OtherException("The Username contains invalid characters ,Please verfiy {A-Z,a-z,0-9,.,_} ");
        if(userRepository.existsByUsername(username)) throw new UserOrEmailAlreadyExistsException("User already exist");
        if (username.startsWith(".") || username.startsWith("_")) throw new OtherException("The Username Not start with . or _");
        return true;
    }
}
