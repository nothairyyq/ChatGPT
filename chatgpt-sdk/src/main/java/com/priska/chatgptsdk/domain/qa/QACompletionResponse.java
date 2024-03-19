package com.priska.chatgptsdk.domain.qa;

import com.priska.chatgptsdk.domain.other.Usage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QACompletionResponse {
    /** ID */
    private String id;
    /** 对象 */
    private String object;
    /** 模型 */
    private String model;
    /** 对话 */
    private QAChoice[] choices;
    /** 创建 */
    private long created;
    /** 耗材 */
    private Usage usage;
}
