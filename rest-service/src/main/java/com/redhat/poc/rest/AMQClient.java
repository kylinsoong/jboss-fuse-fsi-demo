package com.redhat.poc.rest;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AMQClient {
	
	private static final String BROKER_URL = "tcp://localhost:61616"; 
	private static final String BROKER_USERNAME = "admin"; 
	private static final String BROKER_PASSWORD = "admin";
	
	public static ActiveMQConnectionFactory factory = null;
	
	public static ActiveMQConnectionFactory getFactory() {
		if(null == factory) {
			factory = new ActiveMQConnectionFactory();
			factory.setBrokerURL(BROKER_URL);
			factory.setUserName(BROKER_USERNAME);
            factory.setPassword(BROKER_PASSWORD);
		}
		return factory;
	}
	
	

}
