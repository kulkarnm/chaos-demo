package com.chaosdemo.customer.config;

import com.chaosdemo.metrics.filter.MetricsFilter;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages ={"com.chaosdemo.metrics.impl","com.chaosdemo.metrics.filter","com.chaosdemo.customer"})
public class Configuration {
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        MongoTemplate mongoTemplate =
                new MongoTemplate(mongoClient,"chaosDB");
        return mongoTemplate;
    }

    @Bean
    public MongoClient mongoClient(@Value("${spring.datasource.host}") String host,@Value("${spring.datasource.port}") int port) {
        return new MongoClient(host, port);
    }

    @Bean
    MeterRegistryCustomizer meterRegistryCustomizer(){
        return registry->{
            registry.config().commonTags("application","chaos-demo-customer");
        };
    }

    @Bean
    public FilterRegistrationBean<MetricsFilter> metricsFilter(){
        FilterRegistrationBean<MetricsFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MetricsFilter());
        registrationBean.addUrlPatterns("/customers/*");

        return registrationBean;
    }
}
