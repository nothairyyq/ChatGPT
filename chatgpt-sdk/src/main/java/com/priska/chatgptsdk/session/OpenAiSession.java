package com.priska.chatgptsdk.session;

import com.priska.chatgptsdk.domain.chat.ChatCompletionRequest;
import com.priska.chatgptsdk.domain.chat.ChatCompletionResponse;
import com.priska.chatgptsdk.domain.qa.QACompletionRequest;
import com.priska.chatgptsdk.domain.qa.QACompletionResponse;

//绘画接口
public interface OpenAiSession {
    /**
     * 文本问答
     * @param qaCompletionRequest 请求信息
     * @return                    返回结果
     */
    @Deprecated
    QACompletionResponse completions(QACompletionRequest qaCompletionRequest);

    /**
     * 文本问答；简单请求
     * @param question 请求信息
     * @return         返回结果
     */
    @Deprecated
    QACompletionResponse completions(String question);

    /**
     * 默认 GPT-3.5 问答模型
     * @param chatCompletionRequest 请求信息
     * @return                      返回结果
     */
    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);
}
