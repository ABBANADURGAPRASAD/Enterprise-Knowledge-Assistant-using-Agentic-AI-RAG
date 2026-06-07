package com.enterprise.eka.service.rag.ingestion;

import com.enterprise.eka.service.entity.Document;
import com.enterprise.eka.service.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentIngestionService {

    private final DocumentRepository documentRepository;
    private final VectorStore vectorStore;
    private final TokenTextSplitter textSplitter = new TokenTextSplitter(1000, 200, 5, 10000, true);

    @Transactional
    public Document ingest(byte[] content, String fileName, String contentType, long fileSize,
                           String documentType, String classification, Long userId) {
        Document entity = Document.builder()
                .title(fileName)
                .fileName(fileName)
                .fileType(contentType)
                .fileSize(fileSize)
                .documentType(documentType)
                .uploadedBy(userId)
                .status("PROCESSING")
                .build();

        entity = documentRepository.save(entity);
        final Long docId = entity.getId();
        final String title = entity.getTitle();

        try {
            TikaDocumentReader reader = new TikaDocumentReader(new ByteArrayResource(content));
            List<org.springframework.ai.document.Document> parsed = reader.get();

            List<org.springframework.ai.document.Document> chunks = textSplitter.apply(parsed);

            chunks.forEach(chunk -> {
                Map<String, Object> metadata = new HashMap<>(chunk.getMetadata());
                metadata.put("documentId", docId.toString());
                metadata.put("title", title);
                metadata.put("documentType", documentType);
                metadata.put("classification", classification);
                chunk.getMetadata().putAll(metadata);
            });

            vectorStore.add(chunks);

            entity.setChunkCount(chunks.size());
            entity.setStatus("INDEXED");
            documentRepository.save(entity);

            log.info("Ingested document {} with {} chunks", entity.getId(), chunks.size());
        } catch (Exception e) {
            entity.setStatus("FAILED");
            documentRepository.save(entity);
            log.error("Failed to ingest document {}", entity.getId(), e);
            throw new RuntimeException("Document ingestion failed", e);
        }

        return entity;
    }
}
