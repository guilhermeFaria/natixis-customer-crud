package pt.com.aubay.challenge.natixis.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint( validatedBy = AdultValidator.class )
@Target({ ElementType.FIELD })
@Retention( RetentionPolicy.RUNTIME )
public @interface Adult {
    String message() default "Must be at least 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}