package pt.com.aubay.challenge.natixis.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AdultValidator implements ConstraintValidator< Adult, LocalDate > {

    @Override
    public boolean isValid( LocalDate birthDate, ConstraintValidatorContext context ) {
        if( birthDate == null ) {
            return true;
        }
        return Period.between( birthDate, LocalDate.now() ).getYears() >= 18;
    }
}
