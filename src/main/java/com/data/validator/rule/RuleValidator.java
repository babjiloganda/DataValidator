package com.data.validator.rule;

import com.data.validator.response.Response;

public interface RuleValidator {
    <T> boolean isValid(final T object);
    <T> Response validate(final T object);
    ValidatorContext getContext();
}
