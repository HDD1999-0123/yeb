package com.hdd.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * @author hedd
 * @create 2021/6/15 16:27
 * @Desc 消息类
 */
@Data
@EqualsAndHashCode
@Accessors
public class CharMsg {
    private String from;
    private String to;
    private String content;
    private LocalDateTime date;
    private String fromNickName;
}
