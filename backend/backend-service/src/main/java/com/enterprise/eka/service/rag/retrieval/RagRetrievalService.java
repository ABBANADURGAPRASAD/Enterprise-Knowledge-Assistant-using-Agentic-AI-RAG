package com.enterprise.eka.service.rag.retrieval;

import com.enterprise.eka.api.common.DocumentClassification;
import com.enterprise.eka.api.model.RetrievedDocument;
import com.enterprise.eka.service.rag.security.RagSecurityFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RagRetrievalService {

    private final VectorStore vectorStore;
    private final RagSecurityFilter ragSecurityFilter;

    @Value("${eka.rag.top-k:5}")
    private int topK;

    public List<RetrievedDocument> retrieve(String query, List<String> userRoles) {
        List<DocumentClassification> allowedClassifications =
                ragSecurityFilter.getAllowedClassifications(userRoles);

        SearchRequest searchRequest = SearchRequest.query(query).withTopK(topK);

        List<Document> results = vectorStore.similaritySearch(searchRequest);

        return results.stream()
                .filter(doc -> ragSecurityFilter.isDocumentAccessible(doc, allowedClassifications))
                .map(doc -> RetrievedDocument.builder()
                        .documentId(Long.parseLong(doc.getMetadata().getOrDefault("documentId", "0").toString()))
                        .documentTitle(doc.getMetadata().getOrDefault("title", "Unknown").toString())
                        .content(doc.getContent())
                        .score(doc.getMetadata().containsKey("distance")
                                ? 1.0 - Double.parseDouble(doc.getMetadata().get("distance").toString())
                                : 0.8)
                        .classification(doc.getMetadata().getOrDefault("classification", "INTERNAL").toString())
                        .documentType(doc.getMetadata().getOrDefault("documentType", "").toString())
                        .build())
                .collect(Collectors.toList());
    }
}
