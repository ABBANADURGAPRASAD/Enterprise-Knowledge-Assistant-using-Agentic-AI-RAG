package com.enterprise.eka.service.agents.recruitment;

import com.enterprise.eka.service.agents.BaseAgent;
import com.enterprise.eka.api.common.AgentType;
import com.enterprise.eka.api.model.AgentContext;
import com.enterprise.eka.api.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class RecruitmentAgent extends BaseAgent {

    public RecruitmentAgent(ChatClient chatClient) {
        super(chatClient);
    }

    @Override
    public AgentType getType() {
        return AgentType.RECRUITMENT_AGENT;
    }

    @Override
    public Set<String> getAllowedRoles() {
        return Set.of("CEO", "HR", "EXTERNAL");
    }

    @Override
    public boolean canHandle(String intent) {
        return Set.of("RECRUITMENT", "JOB_APPLICATION").contains(intent);
    }

    @Override
    public ChatResponse process(AgentContext context) {
        ChatResponse response = super.process(context);

        if ("JOB_APPLICATION".equals(context.getDetectedIntent())) {
            response.setActionPayload(ChatResponse.ActionPayload.builder()
                    .actionType("OPEN_COMPONENT")
                    .componentName("JobApplicationComponent")
                    .displayMode("SIDE_PANEL")
                    .params(Map.of("jobTitle", extractJobTitle(context.getMessage())))
                    .build());
        }

        return response;
    }

    private String extractJobTitle(String message) {
        return message.replaceAll("(?i)(apply for|application for|job)\\s*", "").trim();
    }

    @Override
    protected String getSystemPrompt() {
        return """
                You are the Recruitment Agent for the Enterprise Knowledge Assistant.
                You help with job postings, applications, candidate screening, and interview scheduling.
                External users can search jobs and apply. HR can manage the full recruitment pipeline.
                When user wants to apply, trigger JobApplicationComponent via action payload.
                """;
    }
}
