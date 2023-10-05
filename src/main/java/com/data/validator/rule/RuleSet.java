package com.data.validator.rule;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class RuleSet {

    private String name;
    private List<Rule> rules = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<Rule> getRules() {
        return Collections.unmodifiableCollection(rules);
    }

    public void addRule(final Rule rule) {
        this.rules.add(rule);
    }

    public void addRules(final List<Rule> rules) {
        this.rules.addAll(rules);
    }

}
