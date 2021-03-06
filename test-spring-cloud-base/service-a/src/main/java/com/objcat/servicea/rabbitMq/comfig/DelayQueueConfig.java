package com.objcat.servicea.rabbitMq.comfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayQueueConfig {

    /**
     * 延时队列交换机
     * 注意这里的交换机类型：CustomExchange
     * @return
     */
    @Bean
    public CustomExchange delayExchange(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delay_exchange","x-delayed-message",true, false,args);
    }

    /**
     * 延时队列
     * @return
     */
    @Bean
    public Queue delayQueue(){
        return new Queue("delay_queue",true);
    }

    /**
     * 给延时队列绑定交换机
     * @return
     */
    @Bean
    public Binding cfgDelayBinding(Queue cfgDelayQueue,CustomExchange cfgUserDelayExchange){
        return BindingBuilder.bind(cfgDelayQueue).to(cfgUserDelayExchange).with("delay_key").noargs();
    }


}
