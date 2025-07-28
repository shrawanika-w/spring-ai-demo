package com.beautifulsoftware.ai.springdemo.tools;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Loads a pdf file into a vector json
 *
 * @see com.beautifulsoftware.ai.springdemo.controller.RagController
 * @since July 2025
 */
@Component
@RequiredArgsConstructor
public class PDFReader {

    private static final String VECTOR_STORE_FILE = "vector_store.json";
    private final VectorStore vectorStore;
    @Value("classpath:nova-leave-policy.pdf")
    private Resource pdfResource;

    @PostConstruct
    public void load() {
        File vectorFile = new File(VECTOR_STORE_FILE);

        if (vectorFile.exists()) {
            System.out.println("Loading vector store from file...");
            ((SimpleVectorStore) vectorStore).load(vectorFile); // Vector store in JSON
        } else {
            // 1. Load PDF document in Vector store
            System.out.println("Processing PDF and saving vectors...");
            var config = PdfDocumentReaderConfig.builder()
                    .withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder().build())
                    .build();

            var pdfReader = new PagePdfDocumentReader(pdfResource, config);
            var textSplitter = new TokenTextSplitter();
            vectorStore.accept(textSplitter.apply(pdfReader.get()));
            ((SimpleVectorStore) vectorStore).save(vectorFile);

            // 2. Load random fact texts in Vector store (added for demo)
            Document document = new Document("The sky is green");
            List<Document> documents = new TokenTextSplitter().apply(List.of(document));
            vectorStore.add(documents);
        }
    }
}