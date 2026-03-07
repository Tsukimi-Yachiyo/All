package com.yachiyo.service;

import com.yachiyo.dto.ChatRequest;
import com.yachiyo.result.Result;
import io.swagger.v3.oas.annotations.media.Schema;

public interface ChatService {

    /**
     * 聊天
     * @param chatRequest 聊天请求
     * @return 回复
     */
    @Schema(description = "聊天请求")
    public Result<String> Chat(ChatRequest chatRequest);

     /**
     * 创建会话
     * @return 会话ID
     */
    @Schema(description = "创建会话")
    public Result<String> Create();
}
