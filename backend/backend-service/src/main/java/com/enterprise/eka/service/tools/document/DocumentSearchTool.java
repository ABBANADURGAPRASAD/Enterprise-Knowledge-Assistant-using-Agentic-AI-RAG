package com.enterprise.eka.service.tools.document;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DocumentSearchTool {

    @JsonClassDescription("Search company documents and knowledge base")
    public record Request(
            @JsonProperty(required = true)
            @JsonPropertyDescription("Search query for documents")
            String query,
            @JsonPropertyDescription("Document type filter: HR, POLICY, TECHNICAL, SOP")
            String documentType
    ) {}

    public record Response(List<DocumentResult> results) {}

    public record DocumentResult(Long id, String title, String snippet, double score) {}

    @Bean
    @Description("Search company documents and knowledge base by query")
    public Function<Request, Response> documentSearch() {
        return request -> {
            log.info("Tool call: documentSearch query={}, type={}", request.query(), request.documentType());
            return new Response(List.of(
                    new DocumentResult(1L, "Employee Handbook", "Leave policy section...", 0.92)
            ));
        };
    }
}
