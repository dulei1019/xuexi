package com.share.device.emqx;

import cn.hutool.core.thread.ThreadUtil;
import com.share.device.emqx.config.EmqxProperties;
import com.share.device.emqx.callback.OnMessageCallback;
import com.share.device.emqx.constant.EmqxConstants;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmqxClientWrapper {

    @Autowired
    private EmqxProperties emqxProperties;

    private MqttClient client;

    @Autowired
    private OnMessageCallback onMessageCallback;

    @PostConstruct
    private void init() {
        MqttClientPersistence mqttClientPersistence = new MemoryPersistence();
        try {
            // 新建客户端 参数：MQTT服务的地址，客户端名称，持久化
            client = new MqttClient(emqxProperties.getServerURI(), emqxProperties.getClientId(), mqttClientPersistence);

            // 设置回调
            client.setCallback(onMessageCallback);
            log.info("回调设置成功");

            // 建立连接
            connect();

        } catch (MqttException e) {
            log.error("MqttClient创建失败", e);
            throw new RuntimeException(e);
        }
    }

    public Boolean connect() {
        // 设置连接的配置
        try {
            client.connect(mqttConnectOptions());
            log.info("连接成功");

            // 订阅
            String[] topics = {EmqxConstants.TOPIC_POWERBANK_CONNECTED, EmqxConstants.TOPIC_POWERBANK_UNLOCK, EmqxConstants.TOPIC_PROPERTY_POST};
            for (String topic : topics) {
                client.subscribe(topic);
                log.info("订阅主题: {}", topic);
            }
            return true;
        } catch (MqttException e) {
            log.error("连接失败", e);
            e.printStackTrace();
        }
        return false;
    }

    /* 创建MQTT配置类 */
    private MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(emqxProperties.getUsername());
        options.setPassword(emqxProperties.getPassword().toCharArray());
        options.setAutomaticReconnect(true); // 是否自动重新连接
        options.setCleanSession(true); // 是否清除之前的连接信息
        options.setConnectionTimeout(emqxProperties.getConnectionTimeout()); // 连接超时时间
        options.setKeepAliveInterval(emqxProperties.getKeepAliveInterval()); // 心跳
        return options;
    }

    /**
     * 发布消息
     *
     * @param topic 主题
     * @param data  数据
     */
    public void publish(String topic, String data) {
        try {
            MqttMessage message = new MqttMessage(data.getBytes());
            message.setQos(2);
            log.info("发布消息：{}", data);
            client.publish(topic, message);
        } catch (MqttException e) {
            log.error("消息发布失败", e);
            e.printStackTrace();
        }
    }
}
