package net.sparkminds.log.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
class KafkaConsumerConfig {

    @Value(value = "${kafka.server_endpoint}")
    private String kafkaServerEndpoint;

    @Value(value = "${kafka.group_id}")
    private String kafkaGroupId;

    @Bean
    ConsumerFactory<String, String> consumerFactory() {

        Map<String, Object> map = new HashMap<>();

        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(map);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListner() {

        ConcurrentKafkaListenerContainerFactory<String, String> obj = new ConcurrentKafkaListenerContainerFactory<>();
        obj.setConsumerFactory(consumerFactory());
        return obj;
    }
}