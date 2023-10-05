package com.data.validator.rule;

public class ValidatorContext {
    private String ruleSetName;
    private RuleFetcher ruleFetcher;

    public String getRuleSetName() {
        return ruleSetName;
    }

    public void setRuleSetName(final String ruleSetName) {
        this.ruleSetName = ruleSetName;
    }

    public RuleFetcher getRuleFetcher() {
        return ruleFetcher;
    }

    public void setRuleFetcher(final RuleFetcher ruleFetcher) {
        this.ruleFetcher = ruleFetcher;
    }
}
