package com.activiti.extension.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.activiti.extension.iot.BrokerListener;

public class MqttActivitiBrokerListener implements BrokerListener {
	protected static final Logger logger = LoggerFactory.getLogger(MqttActivitiBrokerListener.class);
	
	public void init() {
		
		// connect to the MQTT broker by getting a client from the factory
		
		// subscribe to the required topics, and register listeners for each
		
	}
}
