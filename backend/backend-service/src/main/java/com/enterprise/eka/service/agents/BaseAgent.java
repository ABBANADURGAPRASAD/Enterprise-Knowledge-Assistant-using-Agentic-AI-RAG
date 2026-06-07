package com.enterprise.eka.service.agents;

import com.enterprise.eka.api.model.AgentContext;

import com.enterprise.eka.api.common.AgentType;
import com.enterprise.eka.api.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public abstract class BaseAgent implements Agent {

    protected final ChatClient chatClient;

    @Override
    public abstract AgentType getType();

    @Override
    public abstract Set<String> getAllowedRoles();

    @Override
    public abstract boolean canHandle(String intent);

    protected String getSystemPrompt() {
        return "You are a specialized enterprise assistant agent.";
    }

    @Override
    public ChatResponse process(AgentContext context) {
        String response = chatClient.prompt()
                .system(getSystemPrompt())
                .user(buildUserPrompt(context))
                .call()
                .content();

        return ChatResponse.builder()
                .conversationId(context.getConversationId())
                .message(response)
                .agentUsed(getType().name())
                .confidence(0.85)
                .build();
    }

    protected String buildUserPrompt(AgentContext context) {
        return String.format("User (%s, roles: %s): %s",
                context.getUsername(),
                context.getRoles(),
                context.getMessage());
    }
}
