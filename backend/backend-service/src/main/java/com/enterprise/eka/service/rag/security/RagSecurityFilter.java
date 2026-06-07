package com.enterprise.eka.service.rag.security;

import com.enterprise.eka.api.common.DocumentClassification;
import com.enterprise.eka.api.common.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * RAG Security Filter — ensures documents are filtered by role before reaching the LLM.
 */
@Slf4j
@Component
public class RagSecurityFilter {

    private static final Map<RoleType, Set<DocumentClassification>> ROLE_CLASSIFICATION_MAP = Map.of(
            RoleType.CEO, EnumSet.allOf(DocumentClassification.class),
            RoleType.HR, EnumSet.of(DocumentClassification.PUBLIC, DocumentClassification.INTERNAL, DocumentClassification.CONFIDENTIAL),
            RoleType.MANAGER, EnumSet.of(DocumentClassification.PUBLIC, DocumentClassification.INTERNAL),
            RoleType.EMPLOYEE, EnumSet.of(DocumentClassification.PUBLIC, DocumentClassification.INTERNAL),
            RoleType.EXTERNAL, EnumSet.of(DocumentClassification.PUBLIC)
    );

    public List<DocumentClassification> getAllowedClassifications(List<String> roles) {
        return roles.stream()
                .map(role -> ROLE_CLASSIFICATION_MAP.getOrDefault(
                        RoleType.valueOf(role), EnumSet.of(DocumentClassification.PUBLIC)))
                .flatMap(Set::stream)
                .distinct()
                .toList();
    }

    public boolean isDocumentAccessible(Document document, List<DocumentClassification> allowed) {
        String classification = document.getMetadata()
                .getOrDefault("classification", "INTERNAL")
                .toString();

        try {
            DocumentClassification docClass = DocumentClassification.valueOf(classification);
            boolean accessible = allowed.contains(docClass);
            if (!accessible) {
                log.warn("Blocked document access: classification={}, allowed={}", classification, allowed);
            }
            return accessible;
        } catch (IllegalArgumentException e) {
            log.warn("Unknown classification: {}", classification);
            return false;
        }
    }
}
