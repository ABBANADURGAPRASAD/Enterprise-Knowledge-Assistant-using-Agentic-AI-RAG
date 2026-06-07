package com.enterprise.eka.service.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAiConfig {

    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem("""
                        You are the Enterprise Knowledge Assistant (EKA).
                        You help employees with HR, projects, policies, and general company information.
                        Always be professional, accurate, and concise.
                        Never reveal sensitive information beyond the user's authorized access level.
                        """)
                .build();
    }
}
