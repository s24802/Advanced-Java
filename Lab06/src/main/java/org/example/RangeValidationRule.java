package org.example;

import org.example.annotations.Range;
import org.example.validation.ValidationResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class RangeValidationRule implements ICheckValidationRule {


    @Override
    public <T> void validate(ValidationResult<T> validationResult) {
        Class clazz = validationResult.getValidatedObject().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent((Range.class))) {
                field.setAccessible(true);
                var range = field.getAnnotation(Range.class);
                try {
                    var value = (Integer) field.get(validationResult.getValidatedObject());
                    if (value < range.min() || value > range.max()) {
                        validationResult.setValid(false);
                        validationResult.getNotValidFields().putIfAbsent(field.getName(), new ArrayList<>(List.of(range.message())));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            }
            }
    }




