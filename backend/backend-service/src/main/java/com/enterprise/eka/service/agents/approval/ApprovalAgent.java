package com.enterprise.eka.service.agents.approval;

import com.enterprise.eka.service.agents.BaseAgent;
import com.enterprise.eka.api.model.AgentContext;
import com.enterprise.eka.api.common.AgentType;
import com.enterprise.eka.api.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class ApprovalAgent extends BaseAgent {

    public ApprovalAgent(ChatClient chatClient) {
        super(chatClient);
    }

    @Override
    public AgentType getType() {
        return AgentType.APPROVAL_AGENT;
    }

    @Override
    public Set<String> getAllowedRoles() {
        return Set.of("CEO", "HR", "MANAGER");
    }

    @Override
    public boolean canHandle(String intent) {
        return "APPROVAL_ACTION".equals(intent);
    }

    @Override
    public ChatResponse process(AgentContext context) {
        ChatResponse response = super.process(context);
        response.setActionPayload(ChatResponse.ActionPayload.builder()
                .actionType("OPEN_COMPONENT")
                .componentName("ApprovalListComponent")
                .displayMode("SIDE_PANEL")
                .params(Map.of("status", "PENDING"))
                .build());
        return response;
    }

    @Override
    protected String getSystemPrompt() {
        return """
                You are the Approval Agent for the Enterprise Knowledge Assistant.
                You help managers and HR manage pending approvals for leave, permissions, and reimbursements.
                When listing approvals, trigger ApprovalListComponent in the UI.
                """;
    }
}
