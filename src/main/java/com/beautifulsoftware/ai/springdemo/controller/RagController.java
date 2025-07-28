package com.beautifulsoftware.ai.springdemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * QuestionAnswerAdvisor queries the vector database for documents related to the user question.
 * SafeGuardAdvisor prevent certain sensitive words from being used in client prompts
 * (Message|Prompt|VectorStore)ChatMemoryAdvisor manages the conversation memory
 * <pre>
 *     GET: http://localhost:8080/chat-rag?query="What is color of sky"
 *     GET: http://localhost:8080/chat-rag?query="How many sick leaves available"
 * </pre>
 *
  * @since July 2025
 */
@RestController
public class RagController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagController(ChatModel chatModel, VectorStore vectorStore) {
        this.chatClient = ChatClient.create(chatModel);
        this.vectorStore = vectorStore;
    }

    @GetMapping(value = "/chat-rag")
    public String chat(@RequestParam("query") String query) {

        // Query the Vector store for the user query
        QuestionAnswerAdvisor questionAnswerAdvisor = new QuestionAnswerAdvisor(vectorStore); // RAG advisor
        SafeGuardAdvisor safeGuardAdvisor = new SafeGuardAdvisor(List.of("secret", "confidential", "salary"));

        String response = chatClient.prompt()
                .user(query)
                .advisors(questionAnswerAdvisor, safeGuardAdvisor)
                .call()
                .content();

        return response;
    }
}
