package com.hdd.server.pojo.data;

import org.springframework.amqp.core.Queue;

/**
 * @author hedd
 * @create 2021/6/11 13:55
 * @Desc TODO
 */
public class MailConstants {
    //消息投递中
    public static final Integer DELIVERING = 0;
    //消息投递成功
    public static final Integer SUCCESS = 1;
    //消息投递失败
    public static final Integer FAILURE = 2;
    //最大重试次数
    public static final Integer MAX_TRY_COUNT = 3;
    //消息超时时间
    public static final Integer MSG_TIMEOUT = 1;
    //队列
    public static final String MAIL_QUEUE_NAME = "mail.welcome";
    //交换机
    public static final String MAIL_EXCHANGE_NAME = "mail.exchange";
    //路由器
    public static final String MAIL_ROUTING_KEY_NAME = "mail.roution.key";
}
