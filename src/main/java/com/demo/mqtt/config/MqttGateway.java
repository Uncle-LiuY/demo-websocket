package com.demo.mqtt.config;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @Author: LiuYong
 * @Date:2020/04/27 11:51
 * @Description: TODO 描述 消息推送接口类
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    /**
     * 向指定主题发送消息
     * @param data  发送的消息内容，
     * @param topic 主题,如果不指定，则使用默认配置的主题。
     */
    void sendToMqtt(String data,@Header(MqttHeaders.TOPIC) String topic);
}
