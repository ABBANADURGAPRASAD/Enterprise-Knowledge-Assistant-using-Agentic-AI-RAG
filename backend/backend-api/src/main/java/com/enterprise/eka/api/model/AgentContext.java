package com.enterprise.eka.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class AgentContext {
    private Long userId;
    private String username;
    private List<String> roles;
    private String message;
    private Long conversationId;
    private String detectedIntent;
    private Map<String, Object> metadata;
}
