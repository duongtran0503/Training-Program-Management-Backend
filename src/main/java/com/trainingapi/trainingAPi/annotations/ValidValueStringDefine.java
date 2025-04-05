package com.trainingapi.trainingAPi.annotations;

import com.trainingapi.trainingAPi.validator.ValidatorValueStringDefine;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Constraint(validatedBy = {ValidatorValueStringDefine.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidValueStringDefine {
  String message() default "Lỗi dữ liệu!";
  String[] values();
  Class<?>[] groups () default {};

  Class<? extends Payload>[] payload() default  {};
}
