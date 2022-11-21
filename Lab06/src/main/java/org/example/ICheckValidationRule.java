package org.example;

import org.example.validation.ValidationResult;

public interface ICheckValidationRule  {
    public <T> void validate(ValidationResult<T> validationResult) throws IllegalAccessException;
}
