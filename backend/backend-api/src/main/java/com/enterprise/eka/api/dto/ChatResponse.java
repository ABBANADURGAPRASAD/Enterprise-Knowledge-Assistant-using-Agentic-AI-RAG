package com.enterprise.eka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private Long conversationId;
    private String message;
    private String agentUsed;
    private double confidence;
    private ActionPayload actionPayload;
    private List<Citation> citations;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActionPayload {
        private String actionType;
        private String componentName;
        private Map<String, Object> params;
        private String displayMode;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Citation {
        private Long documentId;
        private String documentTitle;
        private String snippet;
        private double relevanceScore;
    }
}
