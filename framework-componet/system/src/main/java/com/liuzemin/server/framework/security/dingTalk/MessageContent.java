package com.liuzemin.server.framework.security.dingTalk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：文本内容类
 * @author bgy
 * @date 2023-12-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageContent {
    /**
     * 文本内容
     */
    private String content;
}
