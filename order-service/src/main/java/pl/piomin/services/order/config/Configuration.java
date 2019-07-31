package pl.piomin.services.order.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

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

}
