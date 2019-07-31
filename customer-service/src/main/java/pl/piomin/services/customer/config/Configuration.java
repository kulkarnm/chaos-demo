package pl.piomin.services.customer.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

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
