package com.priska.chatgptsdk.domain.qa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QAChoice {
    private long index;
    private String text;
    private Object logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;

}
