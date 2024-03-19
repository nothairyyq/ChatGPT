package com.priska.chatgptsdk.session.defaults;


import com.priska.chatgptsdk.common.Constants;
import com.priska.chatgptsdk.domain.chat.ChatCompletionRequest;
import com.priska.chatgptsdk.domain.chat.ChatCompletionResponse;
import com.priska.chatgptsdk.domain.chat.Message;
import com.priska.chatgptsdk.domain.qa.QACompletionRequest;
import com.priska.chatgptsdk.domain.qa.QACompletionResponse;
import com.priska.chatgptsdk.session.Configuration;
import com.priska.chatgptsdk.IOpenAiApi;
import com.priska.chatgptsdk.session.OpenAiSession;
import io.reactivex.Single;
import java.util.Collections;


public class DefaultOpenAiSession implements OpenAiSession {
    private IOpenAiApi openAiApi;

    public DefaultOpenAiSession(IOpenAiApi openAiApi) {
        this.openAiApi = openAiApi;
    }

    @Override
    public QACompletionResponse completions(QACompletionRequest qaCompletionRequest) {
        return this.openAiApi.completions(qaCompletionRequest).blockingGet();
    }

    @Override
    public QACompletionResponse completions(String question) {
        QACompletionRequest request = QACompletionRequest
                .builder()
                .prompt(question)
                .build();
        Single<QACompletionResponse> completions = this.openAiApi.completions(request);
        return completions.blockingGet();
    }

    @Override
    public ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest) {
        return this.openAiApi.completions(chatCompletionRequest).blockingGet();
    }

}
