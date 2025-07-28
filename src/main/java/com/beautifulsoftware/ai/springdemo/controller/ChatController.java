package com.beautifulsoftware.ai.springdemo.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Calling Openai Chat model
 * <pre>
 *     GET: http://localhost:8080/chat?message="Tell me a dad joke"
 * </pre>
 *
 * @since July 2025
 */
@RestController
public class ChatController {

    private final ChatModel chatModel;

    public ChatController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/chat")
    public Map<String, String> generate(@RequestParam(value = "message",
            defaultValue = "Tell me a dad joke") String message) {
        return Map.of(
                "question", message,
                "answer", this.chatModel.call(message));
    }
}