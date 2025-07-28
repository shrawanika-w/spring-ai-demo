package com.beautifulsoftware.ai.springdemo.controller;

import com.beautifulsoftware.ai.springdemo.model.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Handles asynchronous responses from AI chat models
 * <pre>
 *     POST: http://localhost:8080/chat-stream
 *     BODY:
 *     {
 *          "message":"Tell me Harry Potter 1 summary in 50 words"
 *     }
 * </pre>
 *
 * @since July 2025
 */
@RestController
@RequiredArgsConstructor
public class ChatStreamController {

    private final ChatModel chatModel;

    @PostMapping(value = "/chat-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestBody ChatRequest chatRequest) {
        Prompt prompt = new Prompt(new UserMessage(chatRequest.message()));
        return chatModel.stream(prompt)
                .map(response -> response.getResults().get(0).getOutput().getText()); // Do size check for prod grade code
    }
}