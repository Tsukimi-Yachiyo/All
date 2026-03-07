package com.yachiyo.controller;

import com.yachiyo.dto.SpeakRequest;
import com.yachiyo.dto.TTSRequest;
import com.yachiyo.service.ChatService;
import com.yachiyo.service.SpeakService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
@Validated
public class AIChatController {

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private SpeakService speakService;

    @Autowired
    private ChatService chatService;

    @PostMapping("/chat")
    public String Chat(@RequestBody String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @PostMapping("/speak")
    public byte[] Speak(@RequestBody SpeakRequest speakRequest){
        return speakService.textToSpeech(speakRequest);
    }
}
