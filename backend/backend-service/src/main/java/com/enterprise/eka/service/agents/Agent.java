package com.enterprise.eka.service.agents;

import com.enterprise.eka.api.model.AgentContext;

import com.enterprise.eka.api.common.AgentType;
import com.enterprise.eka.api.dto.ChatResponse;

import java.util.List;
import java.util.Set;

/**
 * Base contract for all specialized AI agents in the EKA system.
 */
public interface Agent {

    AgentType getType();

    Set<String> getAllowedRoles();

    boolean canHandle(String intent);

    ChatResponse process(AgentContext context);
}
