package com.priska.chatgptsdk;

import com.priska.chatgptsdk.domain.chat.ChatCompletionRequest;
import com.priska.chatgptsdk.domain.chat.ChatCompletionResponse;
import com.priska.chatgptsdk.domain.qa.QACompletionRequest;
import com.priska.chatgptsdk.domain.qa.QACompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IOpenAiApi {

    /*
    * 文本问答
    * @param qaCompletionRequest 请求信息
    * @return                   返回结果
    * */
    @POST("v1/completions")
    Single<QACompletionResponse> completions(@Body QACompletionRequest qaCompletionRequest);

    /*
    * 默认GPT-3.5 问答模型
    * @param chatCompletionRequest 请求信息
    *
    * */
    @POST("v1/chat/completions")
    Single<ChatCompletionResponse> completions(@Body ChatCompletionRequest chatCompletionRequest);
}
