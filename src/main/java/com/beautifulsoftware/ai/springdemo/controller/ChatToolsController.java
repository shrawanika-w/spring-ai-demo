package com.beautifulsoftware.ai.springdemo.controller;

import com.beautifulsoftware.ai.springdemo.tools.DateTimeTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example of Agent tools
 * <pre>
 *    GET: http://localhost:8080/chat-tools?message="Can you set an alarm 10 minutes from now?"
 * </pre>
 *
 * @since July 2025
 */
@RestController
@RequiredArgsConstructor
public class ChatToolsController {

    private final ChatModel chatModel;
    private final DateTimeTools dateTimeTools;

    @GetMapping("/chat-tools")
    public String execute(@RequestParam String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        String response = ChatClient.create(chatModel)
                .prompt(prompt)
                .tools(dateTimeTools)
                .call()
                .content();

        return response;
    }
}
