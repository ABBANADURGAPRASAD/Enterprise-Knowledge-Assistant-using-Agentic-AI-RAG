package com.enterprise.eka.service.agents.hr;

import com.enterprise.eka.service.agents.BaseAgent;
import com.enterprise.eka.api.common.AgentType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HrAgent extends BaseAgent {

    public HrAgent(ChatClient chatClient) {
        super(chatClient);
    }

    @Override
    public AgentType getType() {
        return AgentType.HR_AGENT;
    }

    @Override
    public Set<String> getAllowedRoles() {
        return Set.of("CEO", "HR");
    }

    @Override
    public boolean canHandle(String intent) {
        return Set.of("HR_QUERY", "EMPLOYEE_LOOKUP", "POLICY_QUERY", "COMPLAINT")
                .contains(intent);
    }

    @Override
    protected String getSystemPrompt() {
        return """
                You are the HR Agent for the Enterprise Knowledge Assistant.
                You help with employee management, HR policies, complaints, payroll queries, and onboarding.
                You have access to HR documents and employee data tools.
                Always protect employee privacy and follow HR compliance guidelines.
                If asked to perform an action, include an action payload for the UI.
                """;
    }
}
