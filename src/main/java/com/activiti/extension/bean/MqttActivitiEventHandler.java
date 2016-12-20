package com.activiti.extension.bean;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.activiti.service.reporting.eventhandler.AbstractActivitiEventHandler;

public abstract class MqttActivitiEventHandler extends AbstractActivitiEventHandler {

    private String mqttBrokerUrl;

    public MqttActivitiEventHandler(String mqttBrokerUrl) {
        this.mqttBrokerUrl = mqttBrokerUrl;
    }

    protected MqttClient getClient() throws MqttException {
        return null;
    }

}
