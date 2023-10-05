package com.data.validator.drools;

import java.util.Arrays;

import com.data.validator.response.Response;
import com.data.validator.rule.RuleSet;
import com.data.validator.rule.RuleValidator;
import org.kie.api.runtime.StatelessKieSession;
import com.data.validator.rule.ValidatorContext;
public class DroolsValidator implements RuleValidator {
    private StatelessKieSession kSession;
    private ValidatorContext context;

    public DroolsValidator(final ValidatorContext context) {
        this.context = context;
        final RuleSet ruleSet = context.getRuleFetcher().getRuleSet(context.getRuleSetName());
        kSession = SessionBuilderFactory.initSession(ruleSet.getRules());
    }

    @Override
    public <T> boolean isValid(final T object) {
        final Response response = validate(object);
        return !response.hasErrors();
    }

    @Override
    public <T> Response validate(final T object) {
        Response response = new Response();
        kSession.execute(Arrays.asList(object, response));
        return response;
    }

    @Override
    public ValidatorContext getContext() {
        return context;
    }
}
