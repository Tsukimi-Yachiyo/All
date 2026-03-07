package com.yachiyo.service.Impl;

import com.yachiyo.dto.ChatRequest;
import com.yachiyo.result.Result;
import com.yachiyo.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatClient chatClient;

    @Override
    public Result<String> Chat(ChatRequest chatRequest) {
        return Result.success(chatClient.prompt()
                .user(chatRequest.getMessage())
                .call()
                .content());
    }
}
