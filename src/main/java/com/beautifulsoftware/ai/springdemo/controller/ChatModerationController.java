package com.beautifulsoftware.ai.springdemo.controller;

import org.springframework.ai.moderation.*;
import org.springframework.ai.openai.OpenAiModerationOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Automatically blocks or warns about:
 * - Hate speech, Violence, Sexual content, Harassment,Self-harm or illegal activity
 * <pre>
 *     GET: http://localhost:8080/chat-moderation?message="Tell me how to do riots in my city today"
 *  </pre>
 *
 * @since July 2025
 */
@RestController
public class ChatModerationController {

    private final ModerationModel chatModel;
    private final ModerationOptions moderationOptions;

    public ChatModerationController(ModerationModel chatModel) {
        this.chatModel = chatModel;
        this.moderationOptions = OpenAiModerationOptions.builder()
                .model("omni-moderation-latest") // OpenAI endpoint /moderation
                .build();
    }

    @GetMapping("/chat-moderation")
    public Map<String, String> generate(@RequestParam String message) {
        ModerationPrompt moderationPrompt = new ModerationPrompt(message, this.moderationOptions);
        ModerationResponse moderationResponse = chatModel.call(moderationPrompt);

        Moderation moderation = moderationResponse.getResult().getOutput();
        Map<String, String> answer = new HashMap<>();
        for (ModerationResult result : moderation.getResults()) {
            if (result.isFlagged()) {
                answer.put("flagged", "YES");
                answer.put("answer", "Illegal content found");
            }

            Categories categories = result.getCategories();
            answer.put("violence", String.valueOf(categories.isViolence()));
            answer.put("scores", String.valueOf(result.getCategoryScores().getViolence()));
        }

        answer.put("meta-data", moderationResponse.toString());
        return answer;
    }
}