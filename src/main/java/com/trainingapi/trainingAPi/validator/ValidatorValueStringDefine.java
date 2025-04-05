package com.trainingapi.trainingAPi.validator;

import com.trainingapi.trainingAPi.annotations.ValidValueStringDefine;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ValidatorValueStringDefine implements ConstraintValidator<ValidValueStringDefine,String> {
    private String[] values;
    @Override
    public void initialize(ValidValueStringDefine constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> list = Arrays.asList(values);

        return list.contains(s);
    }
}
