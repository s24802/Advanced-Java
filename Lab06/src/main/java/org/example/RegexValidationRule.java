package org.example;

import org.example.annotations.Regex;
import org.example.validation.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RegexValidationRule implements ICheckValidationRule {
    @Override
    public <T> void validate(ValidationResult<T> result) {
        Class clazz = result.getValidatedObject().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Regex.class)) {
                f.setAccessible(true);
                var regex = f.getAnnotation(Regex.class);
                try {
                    var value = (String) f.get(result.getValidatedObject());
                    if (!value.matches(regex.pattern())) {
                        result.setValid(false);
                        result.getNotValidFields().putIfAbsent(f.getName(),new ArrayList<> (List.of(regex.message())));
                        result.getNotValidFields().get(f.getName()).add(regex.message());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
