package com.enterprise.eka.service.agents.general;

import com.enterprise.eka.service.agents.BaseAgent;
import com.enterprise.eka.api.common.AgentType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GeneralAssistantAgent extends BaseAgent {

    public GeneralAssistantAgent(ChatClient chatClient) {
        super(chatClient);
    }

    @Override
    public AgentType getType() {
        return AgentType.GENERAL_ASSISTANT;
    }

    @Override
    public Set<String> getAllowedRoles() {
        return Set.of("CEO", "HR", "MANAGER", "EMPLOYEE", "EXTERNAL");
    }

    @Override
    public boolean canHandle(String intent) {
        return Set.of("GREETING", "GENERAL_QUERY", "NAVIGATION_HELP").contains(intent);
    }

    @Override
    protected String getSystemPrompt() {
        return """
                You are the General Assistant for the Enterprise Knowledge Assistant.
                You handle greetings, general questions, and application navigation help.
                You are the fallback when no specialized agent matches the user's intent.
                Politely decline requests outside your scope. Never access restricted data.
                """;
    }
}
