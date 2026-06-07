package com.enterprise.eka.controller;

import com.enterprise.eka.api.common.WorkflowType;
import com.enterprise.eka.api.dto.ApiResponse;
import com.enterprise.eka.api.dto.WorkflowInstanceDto;
import com.enterprise.eka.service.workflows.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/workflows")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowService workflowService;

    @PostMapping
    @PreAuthorize("hasAnyRole('CEO', 'HR', 'MANAGER', 'EMPLOYEE')")
    public ResponseEntity<ApiResponse<WorkflowInstanceDto>> createWorkflow(
            @AuthenticationPrincipal UserDetails user,
            @RequestBody Map<String, Object> request
    ) {
        WorkflowType type = WorkflowType.valueOf((String) request.get("workflowType"));
        WorkflowInstanceDto instance = workflowService.createInstance(type, user.getUsername(), request);
        return ResponseEntity.ok(ApiResponse.success(instance));
    }

    @GetMapping("/pending")
    @PreAuthorize("hasAnyRole('CEO', 'HR', 'MANAGER')")
    public ResponseEntity<ApiResponse<List<WorkflowInstanceDto>>> getPending(
            @AuthenticationPrincipal UserDetails user
    ) {
        return ResponseEntity.ok(ApiResponse.success(workflowService.getPendingForUser(user.getUsername())));
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('CEO', 'HR', 'MANAGER')")
    public ResponseEntity<ApiResponse<WorkflowInstanceDto>> approve(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails user,
            @RequestBody(required = false) Map<String, String> body
    ) {
        String comment = body != null ? body.get("comment") : null;
        return ResponseEntity.ok(ApiResponse.success(workflowService.approve(id, user.getUsername(), comment)));
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('CEO', 'HR', 'MANAGER')")
    public ResponseEntity<ApiResponse<WorkflowInstanceDto>> reject(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails user,
            @RequestBody(required = false) Map<String, String> body
    ) {
        String comment = body != null ? body.get("comment") : null;
        return ResponseEntity.ok(ApiResponse.success(workflowService.reject(id, user.getUsername(), comment)));
    }
}
