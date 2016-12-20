package com.activiti.extension.bean;

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

        try {
            MqttClient sampleClient = new MqttClient(brokerUrl, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            //System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            //System.out.println("Connected");
            //System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage("oh hai".getBytes());
            message.setQos(2);
            sampleClient.publish("test topic", message);
            //System.out.println("Message published");
            sampleClient.disconnect();
            //System.out.println("Disconnected");
            return true;
        } catch(MqttException me) {
            //System.out.println("reason "+me.getReasonCode());
            //System.out.println("msg "+me.getMessage());
            //System.out.println("loc "+me.getLocalizedMessage());
            //System.out.println("cause "+me.getCause());
            //System.out.println("excep "+me);
            me.printStackTrace();
            return false;
        }
	}
    
}
