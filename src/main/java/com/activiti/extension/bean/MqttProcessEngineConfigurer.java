package com.activiti.extension.bean;

import org.activiti.engine.ProcessEngine;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.activiti.api.engine.ProcessEngineConfigurationConfigurer;

public class MqttProcessEngineConfigurer implements ProcessEngineConfigurationConfigurer {

    protected static final Logger logger = LoggerFactory.getLogger(MqttProcessEngineConfigurer.class);

    @Autowired
    protected ProcessEngine processEngine;

    @Autowired
    protected Environment environment;
    
    @Autowired 
    protected BrokerListener brokerListener;
    
	@Override
	public void processEngineConfigurationInitialized(SpringProcessEngineConfiguration config) {
		// TODO Auto-generated method stub

	}

}
