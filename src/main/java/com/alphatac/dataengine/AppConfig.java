package com.alphatac.dataengine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@PropertySource(value = {"classpath:mongo.properties","classpath:datasource.properties"})
@EnableScheduling
public class AppConfig {
    private @Value("${mongo.dbname}") String dbName;
    private @Value("${mongo.host}") String host;
    private @Value("${mongo.port}") Integer port;
    private @Value("${mongo.username}") String username;
    private @Value("${mongo.password}") String password;
    private @Value("${mongo.connectionsPerHost}") Integer connectionsPerHost;
    @Value("${mongo.threadsAllowedToBlockForConnectionMultiplier}")
    private Integer threadsAllowedToBlockForConnectionMultiplier;
    private @Value("${mongo.connectTimeout}") Integer connectTimeout;
    private @Value("${mongo.maxWaitTime}") Integer maxWaitTime;
    private @Value("${mongo.socketTimeout}") Integer socketTimeout;
    @Bean
    public MongoClientFactoryBean mongo() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(connectionsPerHost)
                .threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
                .connectTimeout(connectTimeout)
                .maxWaitTime(maxWaitTime)
                .socketTimeout(socketTimeout);
        MongoCredential credential = MongoCredential
                .createCredential(username,dbName,password.toCharArray());
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost(host);
        mongo.setPort(port);
        mongo.setMongoClientOptions(builder.build());
        mongo.setCredentials(new MongoCredential[]{credential});
        return mongo;
    }


    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongo){
        return new MongoTemplate(mongo,dbName);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }


    @Value("${datasource.opendata.retry}")
    private Integer RETRY_TIMES;
    @Value("${datasource.opendata.retryInterval}")
    private Integer RETRY_INTERVAL;
    @Bean
    public RetryTemplate retryTemplate(){
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(RETRY_TIMES);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(RETRY_INTERVAL);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);

        return template;
    }


    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(2000);
        executor.setWaitForTasksToCompleteOnShutdown(false);
        return executor;
    }
}
