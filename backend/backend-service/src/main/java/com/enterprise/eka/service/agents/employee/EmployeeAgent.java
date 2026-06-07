package com.enterprise.eka.service.agents.employee;

import com.enterprise.eka.service.agents.BaseAgent;
import com.enterprise.eka.api.common.AgentType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EmployeeAgent extends BaseAgent {

    public EmployeeAgent(ChatClient chatClient) {
        super(chatClient);
    }

    @Override
    public AgentType getType() {
        return AgentType.EMPLOYEE_AGENT;
    }

    @Override
    public Set<String> getAllowedRoles() {
        return Set.of("CEO", "EMPLOYEE");
    }

    @Override
    public boolean canHandle(String intent) {
        return Set.of("LEAVE_REQUEST", "PROJECT_QUERY", "POLICY_QUERY").contains(intent);
    }

    @Override
    protected String getSystemPrompt() {
        return """
                You are the Employee Agent for the Enterprise Knowledge Assistant.
                You help employees with personal info, leave balance, projects, achievements, and self-service.
                Access is limited to the authenticated employee's own data only.
                """;
    }
}
