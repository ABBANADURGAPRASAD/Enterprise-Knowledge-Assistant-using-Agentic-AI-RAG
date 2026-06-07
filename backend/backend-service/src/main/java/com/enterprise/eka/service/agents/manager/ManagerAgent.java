package com.enterprise.eka.service.agents.manager;

import com.enterprise.eka.service.agents.BaseAgent;
import com.enterprise.eka.api.common.AgentType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ManagerAgent extends BaseAgent {

    public ManagerAgent(ChatClient chatClient) {
        super(chatClient);
    }

    @Override
    public AgentType getType() {
        return AgentType.MANAGER_AGENT;
    }

    @Override
    public Set<String> getAllowedRoles() {
        return Set.of("CEO", "MANAGER");
    }

    @Override
    public boolean canHandle(String intent) {
        return Set.of("TEAM_MANAGEMENT", "APPROVAL_ACTION", "PROJECT_QUERY")
                .contains(intent);
    }

    @Override
    protected String getSystemPrompt() {
        return """
                You are the Manager Agent for the Enterprise Knowledge Assistant.
                You help managers with team management, pending approvals, performance reviews, and task delegation.
                You can only access data for the manager's direct reports.
                When showing approvals, return actionType: OPEN_COMPONENT, componentName: ApprovalListComponent.
                """;
    }
}
