package com.enterprise.eka.service.agents.router;

import com.enterprise.eka.service.agents.Agent;
import com.enterprise.eka.api.model.AgentContext;
import com.enterprise.eka.api.common.AgentType;
import com.enterprise.eka.api.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Central Agent Router — classifies intent, validates roles, selects agent, orchestrates response.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AgentRouterService {

    private final List<Agent> agents;
    private final ChatClient chatClient;

    public ChatResponse route(AgentContext context) {
        long startTime = System.currentTimeMillis();

        String intent = classifyIntent(context.getMessage());
        context.setDetectedIntent(intent);

        Agent selectedAgent = selectAgent(intent, context.getRoles());
        if (selectedAgent == null) {
            selectedAgent = agents.stream()
                    .filter(a -> a.getType() == AgentType.GENERAL_ASSISTANT)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No fallback agent available"));
        }

        validateAgentAccess(selectedAgent, context.getRoles());

        ChatResponse response = selectedAgent.process(context);
        response.setAgentUsed(selectedAgent.getType().name());

        long elapsed = System.currentTimeMillis() - startTime;
        log.info("Routed to {} in {}ms (intent: {}, user: {})",
                selectedAgent.getType(), elapsed, intent, context.getUsername());

        return response;
    }

    private String classifyIntent(String message) {
        String classification = chatClient.prompt()
                .system("""
                        Classify the user message into ONE intent category:
                        HR_QUERY, LEAVE_REQUEST, EMPLOYEE_LOOKUP, TEAM_MANAGEMENT,
                        APPROVAL_ACTION, RECRUITMENT, JOB_APPLICATION, KNOWLEDGE_SEARCH,
                        POLICY_QUERY, ANALYTICS_QUERY, COMPLAINT, PROJECT_QUERY,
                        GREETING, GENERAL_QUERY, NAVIGATION_HELP
                        Respond with ONLY the intent category name.
                        """)
                .user(message)
                .call()
                .content();

        return classification != null ? classification.trim().toUpperCase() : "GENERAL_QUERY";
    }

    private Agent selectAgent(String intent, List<String> roles) {
        return agents.stream()
                .filter(agent -> agent.canHandle(intent))
                .filter(agent -> hasRoleAccess(agent.getAllowedRoles(), roles))
                .max(Comparator.comparingInt(agent -> agent.canHandle(intent) ? 1 : 0))
                .orElse(null);
    }

    private void validateAgentAccess(Agent agent, List<String> userRoles) {
        if (!hasRoleAccess(agent.getAllowedRoles(), userRoles)) {
            throw new SecurityException("User not authorized for agent: " + agent.getType());
        }
    }

    private boolean hasRoleAccess(Set<String> allowedRoles, List<String> userRoles) {
        return userRoles.stream().anyMatch(allowedRoles::contains);
    }
}
