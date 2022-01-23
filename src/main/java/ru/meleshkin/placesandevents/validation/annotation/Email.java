package ru.meleshkin.placesandevents.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
    String message() default "{email.notValid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
