package com.enterprise.eka.service.agents.knowledge;

import com.enterprise.eka.service.agents.BaseAgent;
import com.enterprise.eka.api.model.AgentContext;
import com.enterprise.eka.api.common.AgentType;
import com.enterprise.eka.api.dto.ChatResponse;
import com.enterprise.eka.service.rag.retrieval.RagRetrievalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class KnowledgeAgent extends BaseAgent {

    private final RagRetrievalService ragRetrievalService;

    public KnowledgeAgent(ChatClient chatClient, RagRetrievalService ragRetrievalService) {
        super(chatClient);
        this.ragRetrievalService = ragRetrievalService;
    }

    @Override
    public AgentType getType() {
        return AgentType.KNOWLEDGE_AGENT;
    }

    @Override
    public Set<String> getAllowedRoles() {
        return Set.of("CEO", "HR", "MANAGER", "EMPLOYEE");
    }

    @Override
    public boolean canHandle(String intent) {
        return "KNOWLEDGE_SEARCH".equals(intent);
    }

    @Override
    public ChatResponse process(AgentContext context) {
        var retrievedDocs = ragRetrievalService.retrieve(context.getMessage(), context.getRoles());

        String ragContext = retrievedDocs.stream()
                .map(doc -> "[Source: " + doc.getDocumentTitle() + "]\n" + doc.getContent())
                .collect(Collectors.joining("\n\n---\n\n"));

        String response = chatClient.prompt()
                .system(getSystemPrompt() + "\n\nRelevant documents:\n" + ragContext)
                .user(context.getMessage())
                .call()
                .content();

        var citations = retrievedDocs.stream()
                .map(doc -> ChatResponse.Citation.builder()
                        .documentId(doc.getDocumentId())
                        .documentTitle(doc.getDocumentTitle())
                        .snippet(doc.getContent().substring(0, Math.min(200, doc.getContent().length())))
                        .relevanceScore(doc.getScore())
                        .build())
                .collect(Collectors.toList());

        return ChatResponse.builder()
                .conversationId(context.getConversationId())
                .message(response)
                .agentUsed(getType().name())
                .confidence(0.9)
                .citations(citations)
                .build();
    }

    @Override
    protected String getSystemPrompt() {
        return """
                You are the Knowledge Agent for the Enterprise Knowledge Assistant.
                You answer questions using technical docs, SOPs, and knowledge base documents.
                Always cite your sources. Never fabricate information not in the provided context.
                """;
    }
}
