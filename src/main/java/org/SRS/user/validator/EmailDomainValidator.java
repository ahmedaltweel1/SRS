package org.SRS.user.validator;

import org.SRS.error.OtherException;
import org.SRS.user.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class EmailDomainValidator implements ConstraintValidator<ValidEmailDomain, String> {

    private static final Set<String> ALLOWED_TLDS = Set.of("com","net","org","edu","gov","io","ai");
    private static final Set<String> ALLOWED_DOMAINS = Set.of("gmail","yahoo","outlook","hotmail");
    private final UserRepository userRepository;

    public EmailDomainValidator(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(userRepository.existsByEmail(email)) throw new OtherException("Invalid Email Format");
        if (email == null || !email.contains("@")) throw new OtherException("Invalid Email Format");
        String[] parts = email.split("@");
        String local = parts[0];
        String domainPart = parts[1];
        String[] domainParts = domainPart.split("\\.");
        if (domainParts.length < 2) throw new OtherException("Invalid Email Format");
        String domain = domainParts[0];
        String tld = domainParts[domainParts.length-1];
        if(!ALLOWED_DOMAINS.contains(domain) || !ALLOWED_TLDS.contains(tld)) throw new OtherException("Invalid Email Format");
        return true;
    }
}
