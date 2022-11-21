package org.example;

import org.example.annotations.NotNull;
import org.example.validation.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NotNullValidationRule implements ICheckValidationRule {
    @Override
    public <T> void validate(ValidationResult<T> result)  {
        Class clazz = result.getValidatedObject().getClass();
           Field[] fields = clazz.getDeclaredFields();
              for (Field f : fields) {
                if (f.isAnnotationPresent(NotNull.class)) {
                    f.setAccessible(true);
                    var notNull = f.getAnnotation(NotNull.class);
                    try {
                        if (f.get(result.getValidatedObject()) == null) {
                            result.setValid(false);
                            result.getNotValidFields().putIfAbsent(f.getName(), new ArrayList<>(List.of(notNull.message())));
                            result.getNotValidFields().get(f.getName()).add(notNull.message());
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
           }
    }
}

