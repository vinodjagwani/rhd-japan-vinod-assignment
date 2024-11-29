/**
 * Author: Vinod Jagwani
 */
package com.user.auth.validator;

import com.user.auth.validator.annotation.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;


public class EnumValueValidator implements ConstraintValidator<Enum, String> {

    private Enum annotation;


    @Override
    public void initialize(final Enum annotation) {
        this.annotation = annotation;
    }


    @Override
    public boolean isValid(final String object, final ConstraintValidatorContext context) {
        if (Objects.isNull(object)) {
            return true;
        }
        boolean result = false;
        final Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (Objects.nonNull(enumValues)) {
            result = Arrays.stream(enumValues).map(Object::toString)
                    .anyMatch(enumValueAsString -> object.equals(enumValueAsString) || (this.annotation.ignoreCase() && object.equalsIgnoreCase(enumValueAsString)));
        }
        return result;
    }
}
