package com.hdd.mail;


import com.hdd.server.pojo.data.MailConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
//去掉相应数据源配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MailApplication {
    public static void main(String[] args) {

        SpringApplication.run(MailApplication.class,args);
    }
    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }
}
