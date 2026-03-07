package com.yachiyo.service;

import com.yachiyo.dto.PromptRequest;
import com.yachiyo.result.Result;

import java.util.List;

public interface HistoryService {

    /**
     * 获取会话记忆
     * @param conservationId 会话ID
     * @return Result<List<PromptRequest>>
     */
    Result<List<PromptRequest>> getHistory(String conservationId);

    /**
     * 获取会话列表
     * @return Result<List<Integer>>
     */
    Result<List<Integer>> getConservationIds();


}
