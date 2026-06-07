package com.enterprise.eka.service.workflows;

import com.enterprise.eka.api.common.WorkflowType;
import com.enterprise.eka.api.dto.WorkflowInstanceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkflowService {

    public WorkflowInstanceDto createInstance(WorkflowType type, String username, Map<String, Object> payload) {
        log.info("Creating workflow {} for user {}", type, username);
        return WorkflowInstanceDto.builder()
                .id(System.currentTimeMillis())
                .workflowType(type.name())
                .currentState("PENDING_APPROVAL")
                .payload(payload)
                .priority("NORMAL")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public List<WorkflowInstanceDto> getPendingForUser(String username) {
        return List.of(
                WorkflowInstanceDto.builder()
                        .id(1L)
                        .workflowType(WorkflowType.LEAVE_APPROVAL.name())
                        .currentState("PENDING_APPROVAL")
                        .payload(Map.of("requester", "Jane Smith", "days", 3))
                        .createdAt(LocalDateTime.now().minusDays(1))
                        .build()
        );
    }

    public WorkflowInstanceDto approve(Long id, String username, String comment) {
        log.info("Workflow {} approved by {}: {}", id, username, comment);
        return WorkflowInstanceDto.builder()
                .id(id)
                .currentState("APPROVED")
                .build();
    }

    public WorkflowInstanceDto reject(Long id, String username, String comment) {
        log.info("Workflow {} rejected by {}: {}", id, username, comment);
        return WorkflowInstanceDto.builder()
                .id(id)
                .currentState("REJECTED")
                .build();
    }
}
