package org.SRS.user.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthdayValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBirthday {
    String message() default "Invalid birthday";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
