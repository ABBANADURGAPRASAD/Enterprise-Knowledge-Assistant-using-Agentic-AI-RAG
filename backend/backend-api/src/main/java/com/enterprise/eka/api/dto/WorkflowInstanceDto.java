package com.enterprise.eka.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class WorkflowInstanceDto {
    private Long id;
    private String workflowType;
    private String currentState;
    private Long initiatorId;
    private Long assigneeId;
    private Map<String, Object> payload;
    private String priority;
    private LocalDateTime createdAt;
}
