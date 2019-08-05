package com.chaosdemo.product.config;

import com.cheosdemo.metrics.filter.MetricsFilter;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
@org.springframework.context.annotation.Configuration
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
    public FilterRegistrationBean<MetricsFilter> metricsFilter(){
        FilterRegistrationBean<MetricsFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MetricsFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

}
