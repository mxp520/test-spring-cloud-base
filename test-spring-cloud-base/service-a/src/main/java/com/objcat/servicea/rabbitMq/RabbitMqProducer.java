package com.objcat.servicea.rabbitMq;

import com.objcat.servicea.entity.StudentEntity;
import com.objcat.servicea.rabbitMq.comfig.DelayQueueConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RabbitMqProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object studentEntity,int ttl) {

        //这里的消息可以是任意对象，无需额外配置，直接传即可
        System.out.println("===============延时队列生产消息====================");
        System.out.println("发送时间:{},发送内容:{}"+":"+ LocalDateTime.now()+":"+ studentEntity.toString());
        this.rabbitTemplate.convertAndSend(
                "delay_exchange",
                "delay_key",
                studentEntity,
                message -> {
                    //注意这里时间可以使long，而且是设置header
                    message.getMessageProperties().setHeader("x-delay",(ttl*60)*1000);
                    return message;
                });
    }

}
