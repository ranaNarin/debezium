package org.alphamoney.debezium.kafka;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.alphamoney.debezium.DebeziumApplication;
import org.alphamoney.debezium.deserializer.ProductMessageDeserializer;
import org.alphamoney.debezium.model.Product;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Product> kafkaListenerContainerFactory(
            ConsumerFactory<String, Product> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, Product> factory=new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Product> consumerFactory(){
        Map<String, Object> props =new HashMap<>();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("key.deserializer", StringDeserializer.class.getName());
        //props.put("value.deserializer", JsonDeserialize.class.getName());
        props.put("value.deserializer", ProductMessageDeserializer.class.getName());
        //props.put(JsonDeserializer.VALUE_DEf)
        props.put("auto.offset.reset", "earliest");
        return new DefaultKafkaConsumerFactory<>(props);

    }


}
