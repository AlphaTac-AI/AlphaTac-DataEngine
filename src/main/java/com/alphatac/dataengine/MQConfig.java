package com.alphatac.dataengine;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;


@Configuration
public class MQConfig {
    @Bean
    public ActiveMQConnectionFactory getConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return connectionFactory;
    }
    @Bean(initMethod = "start", destroyMethod = "stop")
    public PooledConnectionFactory getPooledConnectionFactory() {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setMaxConnections(5);
        pooledConnectionFactory.setConnectionFactory(getConnectionFactory());
        return pooledConnectionFactory;
    }

    @Bean
    public JmsTemplate getJmsTemplate(PooledConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
}
