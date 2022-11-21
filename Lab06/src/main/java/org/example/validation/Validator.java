package org.example.validation;

import org.example.ICheckValidationRule;
import java.util.ArrayList;
import java.util.List;


public class Validator {

    private List<ICheckValidationRule> ruleList = new ArrayList<>();

    public Validator addRule(ICheckValidationRule rule) {
        ruleList.add(rule);
        return this;
    }

    public <T> ValidationResult<T> validate(T ValidatonResult) {
        ValidationResult<T> result = new ValidationResult<>();
        result.setValidatedObject(ValidatonResult);
        result.setValid(true);
        for (ICheckValidationRule rule : ruleList) {
            try {
                rule.validate(result);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
