package com.activiti.extension.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ActivitiMessageListener implements IMqttMessageListener {

	@Override
	public void messageArrived(String topic, MqttMessage msg) throws Exception {
		
		// do we care about this topic?
		
		// Are we starting a process or affecting an existing process?

	}

}
