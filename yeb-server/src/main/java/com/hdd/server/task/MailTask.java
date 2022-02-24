package com.hdd.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hdd.server.pojo.Employee;
import com.hdd.server.pojo.MailLog;
import com.hdd.server.pojo.data.MailConstants;
import com.hdd.server.service.IAdminRoleService;
import com.hdd.server.service.IAdminService;
import com.hdd.server.service.IEmployeeService;
import com.hdd.server.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.connection.RabbitResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.activation.MailcapCommandMap;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hedd
 * @create 2021/6/11 15:05
 * @Desc 邮件发送定时任务
 */
@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 邮件发送定时
     * 10秒发送一次
     */
   // @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask(){
        //查询出状态为0，重试时间小于当前时间的消息信息
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>()
                .eq("status", 0)
                .lt("tryTime", LocalDateTime.now()));
        list.forEach(mailLog -> {
            //如果投递次数超过3次，更新状态为投递失败，不再重试
            if (3<=mailLog.getCount()){
                mailLogService.update(new UpdateWrapper<MailLog>()
                        .set("status",2)
                        .eq("msgId",mailLog.getMsgId()));
            }
            mailLogService.update(new UpdateWrapper<MailLog>()
                    .set("count",mailLog.getCount()+1)
                    .set("updateTime",LocalDateTime.now())
                    .set("tryTime",LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT)));
            Employee employee = employeeService.getEmployee(mailLog.getEid()).get(0);
            //发送消息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                    MailConstants.MAIL_ROUTING_KEY_NAME,
                    employee,
                    new CorrelationData(mailLog.getMsgId()));

        });
    }

}
