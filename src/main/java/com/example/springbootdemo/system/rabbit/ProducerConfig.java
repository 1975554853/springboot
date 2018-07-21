package com.example.springbootdemo.system.rabbit;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

/**
 * @author fly
 * 生产者配置
 */

@Configuration
public class ProducerConfig {

    @Bean
    public RabbitMessagingTemplate getRabbitMessagingTemplate(RabbitTemplate rabbitTemplate){
        RabbitMessagingTemplate rabbitMessagingTemplate =
                new RabbitMessagingTemplate();
        //指定json转换方式 ，使用默认的 fastjson.jar 包
        rabbitMessagingTemplate.setMessageConverter(new MappingJackson2MessageConverter());
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }

}