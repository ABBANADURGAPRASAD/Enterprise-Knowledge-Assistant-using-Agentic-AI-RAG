package com.enterprise.eka.service.chat;

import com.enterprise.eka.api.model.AgentContext;
import com.enterprise.eka.service.agents.router.AgentRouterService;
import com.enterprise.eka.api.dto.ChatRequest;
import com.enterprise.eka.api.dto.ChatResponse;
import com.enterprise.eka.service.entity.Conversation;
import com.enterprise.eka.service.entity.Message;
import com.enterprise.eka.service.entity.User;
import com.enterprise.eka.service.repository.ConversationRepository;
import com.enterprise.eka.service.repository.MessageRepository;
import com.enterprise.eka.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final AgentRouterService agentRouter;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatResponse processMessage(String username, ChatRequest request) {
        User user = userRepository.findByUsername(username).orElseThrow();

        Conversation conversation = request.getConversationId() != null
                ? conversationRepository.findById(request.getConversationId()).orElseGet(() -> createConversation(user))
                : createConversation(user);

        saveMessage(conversation.getId(), "user", request.getMessage(), null);

        AgentContext context = AgentContext.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()))
                .message(request.getMessage())
                .conversationId(conversation.getId())
                .build();

        ChatResponse response = agentRouter.route(context);
        response.setConversationId(conversation.getId());

        saveMessage(conversation.getId(), "assistant", response.getMessage(),
                response.getAgentUsed());

        return response;
    }

    private Conversation createConversation(User user) {
        return conversationRepository.save(Conversation.builder()
                .userId(user.getId())
                .title("New Conversation")
                .status("ACTIVE")
                .build());
    }

    private void saveMessage(Long conversationId, String role, String content, String agentUsed) {
        messageRepository.save(Message.builder()
                .conversationId(conversationId)
                .role(role)
                .content(content)
                .agentUsed(agentUsed)
                .build());
    }
}
