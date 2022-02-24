package com.hdd.server.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hdd.server.mapper.MailLogMapper;
import com.hdd.server.pojo.MailLog;
import com.hdd.server.pojo.data.MailConstants;
import com.hdd.server.service.IMailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hedd
 * @create 2021/6/11 14:26
 * @Desc TODO
 */
@Configuration
public class RabbitMQConfig {
    //连接工厂
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private IMailLogService mailLogService;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);
    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        /**
         * 消息确认回调
         * data:消息唯一标识
         * ack:确认结果
         * cause:失败原因
         */
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack){

                mailLogService.update(new UpdateWrapper<MailLog>().set("status",1).eq("msgId",msgId));
                logger.info("{}======>消息发送成功",msgId);
            }else {
                logger.info("{}====>发送失败",msgId);
            }
        });
        /**
         * 消息失败回调，比如router不到queue时回调
         * msg：消息主题
         * repCode：响应码
         * repTExt：相应描述
         * exchange：交换机
         * routingKey：路由键
         */
        rabbitTemplate.setReturnCallback((msg,repCode,repText,exchange,routingkey)->{
            logger.error("{}=============消息发送queue时失败",msg.getBody());
        });
        return rabbitTemplate;
    }
    /**
     * 队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    /**
     * 队列和交换机进行相应的绑定
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }
}
