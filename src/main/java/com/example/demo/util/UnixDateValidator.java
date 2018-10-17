package com.example.demo.util;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnixDateValidator implements ConstraintValidator<InUnixDate, String> {

    @Override
    public void initialize(InUnixDate unixDateTime) {
    }

    @Override
    public boolean isValid(String target, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.hasText(target)) {
            return true;
        }

        if (!target.matches("(((19|20)([2468][048]|[13579][26]|0[48])|2000)[/-]02[/-]29|((19|20)[0-9]{2}[/-](0[4678]|1[02])[/-](0[1-9]|[12][0-9]|30)|(19|20)[0-9]{2}[/-](0[1359]|11)[/-](0[1-9]|[12][0-9]|3[01])|(19|20)[0-9]{2}[/-]02[/-](0[1-9]|1[0-9]|2[0-8])))")) {
            return false;
        }

        return DateTimeUtils.parseDateFromUtcFromat(target) != null;
    }
}
