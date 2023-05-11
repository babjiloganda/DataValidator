package com.locala.data.validator.drools;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import com.locala.data.validator.rule.Rule;

public class SessionBuilderFactory {

    public static StatelessKieSession initSession(final Collection<Rule> rules) {
        InternalKnowledgeBase knowledgeBase = buildKnowledgeBase(rules);
        return knowledgeBase.newStatelessKieSession();
    }

    private static InternalKnowledgeBase buildKnowledgeBase(final Collection<Rule> rules) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        for (final Rule rule : rules) {
            kbuilder.add(ResourceFactory.newByteArrayResource(rule.getRawRule().getBytes(StandardCharsets.UTF_8)),
                    ResourceType.DRL);
            if (kbuilder.hasErrors()) {
                throw new RuntimeException("Loading rules failed - " + rule.getName() + rule.getRawRule() + kbuilder.getErrors());
            }
        }

        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addPackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
}
