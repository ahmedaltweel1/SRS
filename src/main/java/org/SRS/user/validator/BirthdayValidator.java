package org.SRS.user.validator;


import org.SRS.error.OtherException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class BirthdayValidator implements ConstraintValidator<ValidBirthday, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthday, ConstraintValidatorContext context) {
        if (birthday == null) throw new OtherException("Invalid birthday");
        LocalDate today = LocalDate.now();
        if (!birthday.isBefore(today)) throw new OtherException("Invalid birthday");
        int age = Period.between(birthday, today).getYears();
       if( age <= 18) throw new OtherException("You smaller 18");
       return true;
    }
}
