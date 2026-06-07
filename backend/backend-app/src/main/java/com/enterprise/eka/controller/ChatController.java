package com.enterprise.eka.controller;

import com.enterprise.eka.api.dto.ApiResponse;
import com.enterprise.eka.api.dto.ChatRequest;
import com.enterprise.eka.api.dto.ChatResponse;
import com.enterprise.eka.service.chat.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/message")
    public ResponseEntity<ApiResponse<ChatResponse>> sendMessage(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ChatRequest request
    ) {
        ChatResponse response = chatService.processMessage(userDetails.getUsername(), request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
