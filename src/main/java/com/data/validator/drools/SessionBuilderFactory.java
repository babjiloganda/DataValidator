package com.data.validator.drools;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import com.data.validator.rule.Rule;

public class SessionBuilderFactory {

    public static StatelessKieSession initSession(final Collection<Rule> rules) {
        InternalKnowledgeBase knowledgeBase = buildKnowledgeBase(rules);
        return knowledgeBase.newStatelessKieSession();
    }

    private static InternalKnowledgeBase buildKnowledgeBase(final Collection<Rule> rules) {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        for (final Rule rule : rules) {
            knowledgeBuilder.add(ResourceFactory.newByteArrayResource(rule.getRawRule().getBytes(StandardCharsets.UTF_8)),
                    ResourceType.DRL);
            if (knowledgeBuilder.hasErrors()) {
                throw new RuntimeException("Loading rules failed - " + rule.getName() + rule.getRawRule() + knowledgeBuilder.getErrors());
            }
        }

        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addPackages(knowledgeBuilder.getKnowledgePackages());
        return kbase;
    }
}
