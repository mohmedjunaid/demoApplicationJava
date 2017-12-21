package com.example.demo.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Kishan Maheshwary on 2/20/2017.
 */
@Documented
@Constraint(validatedBy = UnixDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InUnixDate {

    String message() default "{invalid.date}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
