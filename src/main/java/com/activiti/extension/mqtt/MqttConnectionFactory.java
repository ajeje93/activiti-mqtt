package com.activiti.extension.mqtt;

import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class MqttConnectionFactory {

	protected static final Logger logger = LoggerFactory.getLogger(MqttConnectionFactory.class);
	
	@Autowired
    protected Environment environment;
	
	MemoryPersistence persistence = new MemoryPersistence();
	
	String brokerUrl;
    String clientId;
    
	@PostConstruct
    public void init() {
		
		brokerUrl = environment.getProperty("event.forwarding.mqtt.broker.url");
	    clientId = environment.getProperty("event.forwarding.mqtt.clientId");
	    
		// test connection to the MQTT server
	    if(testConnection()) {
	    	// our test connection worked, we can start producing clients
	    }else {
	    	// our test connection failed, log the error
	    }
    }
	
	/**
	 * Returns a functional MQTT Client used for publishing and subscribing. 
	 * 
	 * @return a client connected to the configured broker
	 */
	public MqttClient getClient() {
		return null;
	}
	
	/**
	 * Tests the connection to the broker using a simple test message and topic
	 * 
	 * @return
	 */
	private boolean testConnection() {

		String content = "oh hai";
		String topic = "testtopic";
		int QoS = 2;
		
        try {
            MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            logger.debug("Connecting to broker: " + brokerUrl);
            
            sampleClient.connect(connOpts);
            logger.debug("Connected");
            
            logger.debug("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(QoS);
            sampleClient.publish(topic, message);
            logger.debug("Message published");
            
            sampleClient.disconnect();
            logger.debug("Disconnected");
            
            return true;
        } catch(MqttException me) {
        	logger.debug("reason " + me.getReasonCode());
        	logger.debug("msg " + me.getMessage());
        	logger.debug("loc " + me.getLocalizedMessage());
        	logger.debug("cause " + me.getCause());
        	logger.debug("excep " + me);
            me.printStackTrace();
            return false;
        }
	}
    
}
