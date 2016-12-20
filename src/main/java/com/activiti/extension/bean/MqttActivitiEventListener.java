package com.activiti.extension.bean;

import com.activiti.service.reporting.eventhandler.ActivitiEventHandler;
import com.activiti.service.runtime.events.RuntimeEventListener;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nathan McMinn
 */
@Component
public class MqttActivitiEventListener implements RuntimeEventListener {

    protected static final Logger logger = LoggerFactory.getLogger(MqttActivitiEventListener.class);

    @Autowired
    protected ProcessEngine processEngine;

    @Autowired
    protected Environment environment;

    protected Map<ActivitiEventType, List<ActivitiEventHandler>> eventHandlers = new HashMap<>();

    @PostConstruct
    public void init() {
    	
    	// set up event handlers for events that MQTT needs
        instantiateHandlers();
    }

    protected void instantiateHandlers() {
        if (!isEnabled()) {
            logger.info("MqttActivitiEventListener is disabled");
            return;
        }

        logger.info("Initializing MqttActivitiEventListener");

        String mqttUrl = environment.getProperty("event.forwarding.mqtt.url");
        
        // add event handlers here for event types that we need to know about
        //addEventHandler(EVE, new handler goes here);

    }

    public void addEventHandler(ActivitiEventType eventType, ActivitiEventHandler eventHandler) {
        if (!eventHandlers.containsKey(eventType)) {
            eventHandlers.put(eventType, new ArrayList<ActivitiEventHandler>(1));
        }
        List<ActivitiEventHandler> handlers = eventHandlers.get(eventType);
        handlers.add(eventHandler);
    }

    @Override
    public void onEvent(ActivitiEvent activitiEvent) {
        List<ActivitiEventHandler> handlers = eventHandlers.get(activitiEvent.getType());
        if (handlers != null) {
            for (ActivitiEventHandler eventHandler : handlers) {
                eventHandler.handleEvent(activitiEvent, processEngine);
            }
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return environment.getProperty("event.forwarding.mqtt.enabled", Boolean.class, Boolean.FALSE);
    }
}
