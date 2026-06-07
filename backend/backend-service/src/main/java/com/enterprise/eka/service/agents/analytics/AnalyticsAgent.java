package com.enterprise.eka.service.agents.analytics;

import com.enterprise.eka.service.agents.BaseAgent;
import com.enterprise.eka.api.common.AgentType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AnalyticsAgent extends BaseAgent {

    public AnalyticsAgent(ChatClient chatClient) {
        super(chatClient);
    }

    @Override
    public AgentType getType() {
        return AgentType.ANALYTICS_AGENT;
    }

    @Override
    public Set<String> getAllowedRoles() {
        return Set.of("CEO", "HR", "MANAGER");
    }

    @Override
    public boolean canHandle(String intent) {
        return "ANALYTICS_QUERY".equals(intent);
    }

    @Override
    protected String getSystemPrompt() {
        return """
                You are the Analytics Agent for the Enterprise Knowledge Assistant.
                You interpret metrics, generate reports, and answer natural language analytics queries.
                CEO sees org-wide data. Managers see team-level data only.
                """;
    }
}
