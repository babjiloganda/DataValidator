package com.locala.data.validator.rule;

import com.locala.data.validator.response.Response;
public interface RuleValidator {
    <T> boolean isValid(final T object);
    <T> Response validate(final T object);
    ValidatorContext getContext();
}
